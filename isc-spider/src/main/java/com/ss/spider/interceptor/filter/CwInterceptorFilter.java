package com.ss.spider.interceptor.filter;

import com.ss.enums.CommonEnumClass;
import com.ss.response.ResponseEntity;
import com.ss.spider.interceptor.cache.beans.CacheUserInfo;
import com.ss.spider.interceptor.config.properties.CwResourceFilterProperties;
import com.ss.spider.interceptor.requestwrapper.CwHttpServletRequestWrapper;
import com.ss.spider.interceptor.service.UserInfoCacheService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


public class CwInterceptorFilter implements Filter {

    @Autowired
    private UserInfoCacheService userInfoCacheService;
    @Autowired
    private CwResourceFilterProperties cwResourceFilterProperties;
    @Value("${cw.security.jwt.tokenSigningKey}")
    private String tokenSigningKey;
    @Value("${cw.security.jwt.tokenExpirationTime}")
    private String expTime;
    @Autowired
    private ObjectMapper mapper;
    private Logger logger = LoggerFactory.getLogger(getClass());


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String resourcePath = request.getServletPath();

        try {
            if (!this.cwResourceFilterProperties.isEnable()) {
                setCacheUserInfo("1000", true);
                chain.doFilter(new CwHttpServletRequestWrapper(request, "1000", true), servletResponse);

                return;
            }

            if (!StringUtils.isEmpty(this.cwResourceFilterProperties.getUrl())) {
                List<String> filterResources = Arrays.asList(this.cwResourceFilterProperties.getUrl().split(","));
                if (filterResources.contains(resourcePath)) {
                    setCacheUserInfo("1000", true);
                    chain.doFilter(new CwHttpServletRequestWrapper(request, "1000", true), servletResponse);

                    return;
                }
            }

            if ("/auth/token".equals(resourcePath)) {
                chain.doFilter(request, servletResponse);

                return;
            }
            String tokenPayload = request.getHeader("X-Authorization");
            if (StringUtils.isEmpty(tokenPayload)) {
                returnErrorResult(servletResponse);
                return;
            }
            String token = tokenPayload.substring("Bearer ".length(), tokenPayload.length());


            Map<String, Object> mapJwts = (Map) Jwts.parser().setSigningKey(new SecretKeySpec(DatatypeConverter.parseBase64Binary(this.tokenSigningKey), SignatureAlgorithm.HS512.getJcaName())).parseClaimsJws(token).getBody();

            String userName = (String) mapJwts.get("sub");


            String userId = this.userInfoCacheService.getCacheUserId("USERNAME_" + userName);

            if (StringUtils.isEmpty(userId)) {
                returnNoLoginResult(servletResponse);

                return;
            }
            List<String> roles = this.userInfoCacheService.getCacheRoles("ROLE_" + userName);

            if (!CollectionUtils.isEmpty(roles) && roles.contains("1")) {
                setCacheUserInfo(userId, true);
                chain.doFilter(new CwHttpServletRequestWrapper(request, userId, true), servletResponse);

                return;
            }
            boolean hasRoles = true;
            if (roles == null || roles.isEmpty()) {
                hasRoles = false;
                roles = this.userInfoCacheService.getPersistenceRoles(userName);
                if (roles != null && !roles.isEmpty()) {
                    hasRoles = true;
                    this.userInfoCacheService.setCacheRoles("ROLE_" + userName, roles, (this.expTime == null) ? 30 : Integer.valueOf(this.expTime).intValue());
                }
            }
            if (!hasRoles) {
                returnErrorResult(servletResponse);

                return;
            }
            setCacheUserInfo(userId, false);


            boolean hasResource = true;
            List<String> resources = this.userInfoCacheService.getCacheResources("RESOURCE_" + userName);
            if (resources == null || resources.isEmpty()) {
                hasResource = false;
                resources = this.userInfoCacheService.getPersistenceResources(userName);
                if (resources != null && !resources.isEmpty()) {
                    hasResource = true;
                    List<String> tagetResources = new ArrayList<String>(resources.size());

                    for (String resource : resources) {
                        tagetResources.addAll(Arrays.asList(resource.split(",")));
                    }

                    if (!StringUtils.isEmpty(this.cwResourceFilterProperties.getPublicUrl())) {
                        tagetResources.addAll(Arrays.asList(this.cwResourceFilterProperties.getPublicUrl().split(",")));
                    }
                    resources = tagetResources;

                    this.userInfoCacheService.setCacheResources("RESOURCE_" + userName, resources, (this.expTime == null) ? 30 : Integer.valueOf(this.expTime).intValue());
                }
            }
            if (hasResource) {

                if (resources.contains(resourcePath)) {
                    chain.doFilter(new CwHttpServletRequestWrapper(request, userId, false), servletResponse);
                    return;
                }
                returnErrorResult(servletResponse);

                return;
            }
            returnErrorResult(servletResponse);

            return;
        } catch (MyBatisSystemException e) {
            returnPersistenceErrorResult(servletResponse);
            this.logger.error("权限验证数据库操作异常", e);
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            if (e instanceof org.springframework.dao.DataAccessResourceFailureException && e.getClass().getName().contains("Redis")) {
                returnRedisErrorResult(servletResponse);
            } else {
                returnUnknownResult(servletResponse);
            }
            this.logger.error("权限验证异常", e);
        }
    }

    private void returnPersistenceErrorResult(ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        httpResponse.setHeader("Content-type", "application/json;charset=UTF-8");

        ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
        servletOutputStream.write(this.mapper.writeValueAsString(new ResponseEntity(CommonEnumClass.CommonInterfaceEnum.DATABASE_ERROR.getKey(), CommonEnumClass.CommonInterfaceEnum.DATABASE_ERROR.getValue())).getBytes("UTF-8"));
        servletOutputStream.flush();
        servletOutputStream.close();
    }


    private void returnUnknownResult(ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        httpResponse.setHeader("Content-type", "application/json;charset=UTF-8");

        ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
        servletOutputStream.write(this.mapper.writeValueAsString(new ResponseEntity(CommonEnumClass.CommonInterfaceEnum.UNKNOWN_FAIL.getKey(), CommonEnumClass.CommonInterfaceEnum.UNKNOWN_FAIL.getValue())).getBytes("UTF-8"));
        servletOutputStream.flush();
        servletOutputStream.close();
    }


    private void returnRedisErrorResult(ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        httpResponse.setHeader("Content-type", "application/json;charset=UTF-8");

        ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
        servletOutputStream.write(this.mapper.writeValueAsString(new ResponseEntity(CommonEnumClass.CommonInterfaceEnum.DATA_ACCESS_RESOURCE_FAILURE.getKey(), CommonEnumClass.CommonInterfaceEnum.DATA_ACCESS_RESOURCE_FAILURE.getValue())).getBytes("UTF-8"));
        servletOutputStream.flush();
        servletOutputStream.close();
    }


    private void returnNoLoginResult(ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        httpResponse.setHeader("Content-type", "application/json;charset=UTF-8");

        ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
        servletOutputStream.write(this.mapper.writeValueAsString(new ResponseEntity(CommonEnumClass.CommonInterfaceEnum.TOKEN_EXPIRES.getKey(), CommonEnumClass.CommonInterfaceEnum.TOKEN_EXPIRES.getValue())).getBytes("UTF-8"));
        servletOutputStream.flush();
        servletOutputStream.close();
    }


    private void returnErrorResult(ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        httpResponse.setHeader("Content-type", "application/json;charset=UTF-8");

        ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
        servletOutputStream.write(this.mapper.writeValueAsString(new ResponseEntity(CommonEnumClass.CommonInterfaceEnum.UNAUTHORIZED_ACCESS.getKey(), CommonEnumClass.CommonInterfaceEnum.UNAUTHORIZED_ACCESS.getValue())).getBytes("UTF-8"));
        servletOutputStream.flush();
        servletOutputStream.close();
    }


    private void setCacheUserInfo(String userId, boolean isSuperRole) {
        CacheUserInfo cacheUserInfo = this.userInfoCacheService.getCacheUserInfo("USERINFO_" + userId);
        if (null == cacheUserInfo) {
            cacheUserInfo = this.userInfoCacheService.getPersistenceUserInfo(userId);
            cacheUserInfo.setIsSuperRole(isSuperRole);
            this.userInfoCacheService.setCacheUserInfo("USERINFO_" + userId, cacheUserInfo, (this.expTime == null) ? 30 : Integer.valueOf(this.expTime).intValue());
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

}
