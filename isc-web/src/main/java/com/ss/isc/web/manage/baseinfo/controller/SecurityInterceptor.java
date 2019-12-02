package com.ss.isc.web.manage.baseinfo.controller;

import com.ss.isc.data.baseinfo.common.model.UserAuthority;
import com.ss.isc.data.baseinfo.mapper.AuthorityMapper;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.response.ResponseEntity;
import com.ss.spider.security.UserTokenService;
import com.ss.spider.security.auth.jwt.extractor.TokenExtractor;
import com.ss.spider.security.config.JwtProperties;
import com.ss.spider.security.entity.UserAuthEntity;
import com.ss.spider.security.model.token.RawAccessJwtToken;
import com.ss.spider.security.model.token.RefreshToken;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
* 拦截器-权限查询
* @author chao
* @create 2019/10/8
* @email lishuangchao@ss-cas.com
**/
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);


    @Autowired
    private AuthorityMapper authorityMapper;


    @Autowired
    private JwtProperties jwtSettings;


    @Autowired
    private UserTokenService useTokenrService;


    @Resource
    private JedisUtil jedisUtil;


    @Autowired
    @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean authority = false;
        String userId = getUserId(request);
        if (CommonConstant.ADMIN_USER_ID.equals(userId)) {
            authority = true;
        } else {

            String url = request.getRequestURI();

            String contextPath = request.getContextPath();

            if (StringUtils.isNotBlank(userId)) {
                String resourceId = this.authorityMapper.findAuthority(url.replace(contextPath, ""), userId);
                if (StringUtils.isNotBlank(resourceId)) {
                    authority = true;
                }
            } else {

                LOGGER.error(url + "请求连接未读取到用户编号");
            }
            if (!authority) {
                LOGGER.error("编号为" + userId + "操作员无" + url + "权限");
                returnJson(response);
            }
        }
        return authority;
    }


    private void returnJson(HttpServletResponse response) throws Exception {
        ResponseEntity<Object> resp = new ResponseEntity<Object>();
        resp.setCode("70806001");
        resp.setMessage("您无权访问此功能，请联系管理员！");
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            writer = response.getWriter();
            writer.print(JsonUtils.getFastjsonFromObject(resp));
        } catch (IOException e) {
            LOGGER.error("response error", e);
        } finally {

            if (writer != null) {
                writer.close();
            }
        }
    }


    public static String getBodyMsg(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        String str = null;
        try {
            br = request.getReader();

            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


    public String getUserId(HttpServletRequest request) {
        List<UserAuthority> userAuthorities = new ArrayList<UserAuthority>();
        String userId = "";
        userId = request.getParameter("userIds");
        Object object = this.jedisUtil.get("USER_AUTHORITY");
        if (object == null) {
            userAuthorities = this.authorityMapper.findUserAuthority();
            this.jedisUtil.set("USER_AUTHORITY", userAuthorities);
        } else {

            userAuthorities = (List) object;
        }
        String loginName = "";

        String token = request.getHeader("X-Authorization");
        String tokenPayload = this.tokenExtractor.extract(token);
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        Optional<?> refreshToken = RefreshToken.create(rawToken, this.jwtSettings.getTokenSigningKey());
        String subject = ((RefreshToken) refreshToken.get()).getSubject();
        loginName = subject;

        for (UserAuthority userAuthority : userAuthorities) {
            if (loginName.equals(userAuthority.getLoginName())) {
                userId = userAuthority.getUserId();
                break;
            }
        }
        if (StringUtils.isBlank(userId)) {
            UserAuthEntity user = this.useTokenrService.getUserByLoginName(loginName);
            userId = user.getUserId();
            this.jedisUtil.del(new String[]{"USER_AUTHORITY"});
            userAuthorities = this.authorityMapper.findUserAuthority();
            this.jedisUtil.set("USER_AUTHORITY", userAuthorities);

            if (StringUtils.isBlank(userId)) {
                userId = request.getParameter("userIds");
                if (StringUtils.isBlank(userId)) {
                    RequestWrapper requestWrapper = new RequestWrapper(request);
                    String body = requestWrapper.getBody();
                    JSONObject jsonObject = JSONObject.parseObject(body);
                    userId = jsonObject.getString("userIds");
                }
            }
        }

        return userId;
    }

}
