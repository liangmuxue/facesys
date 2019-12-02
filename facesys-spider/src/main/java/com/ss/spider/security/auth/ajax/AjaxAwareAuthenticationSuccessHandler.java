package com.ss.spider.security.auth.ajax;

import com.ss.response.GeneratorResult;
import com.ss.response.ResponseEntity;
import com.ss.spider.security.UserTokenService;
import com.ss.spider.security.model.UserContext;
import com.ss.spider.security.model.token.AccessJwtToken;
import com.ss.spider.security.model.token.JwtToken;
import com.ss.spider.security.model.token.JwtTokenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper;
    private final JwtTokenFactory tokenFactory;
    @Autowired
    private UserTokenService useTokenrService;
    private Logger logger;

    @Autowired
    public AjaxAwareAuthenticationSuccessHandler(ObjectMapper mapper, JwtTokenFactory tokenFactory) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        try {
            UserContext userContext = (UserContext) authentication.getPrincipal();
            AccessJwtToken accessJwtToken = this.tokenFactory.createAccessJwtToken(userContext);
            JwtToken refreshToken = this.tokenFactory.createRefreshToken(userContext);


            Map<String, String> tokenMap = new HashMap<String, String>(3);
            String userId = this.useTokenrService.getUserByLoginName(userContext.getUsername()).getUserId();
            tokenMap.put("token", accessJwtToken.getToken());
            tokenMap.put("refreshToken", refreshToken.getToken());
            tokenMap.put("userId", userId);

            ResponseEntity<Map<String, String>> resp = GeneratorResult.genSuccessResult();
            resp.setData(tokenMap);

            try {
                this.useTokenrService.cacheUserToken("TOKEN_" + userContext.getUsername(), accessJwtToken.getToken(), this.tokenFactory.getJwtProperties().getTokenExpirationTime());
                this.useTokenrService.cacheUserToken("REFRESH_TOKEN_" + userContext.getUsername(), refreshToken.getToken(), this.tokenFactory.getJwtProperties().getRefreshTokenExpTime());

                this.useTokenrService.cacheUserId("USERNAME_" + userContext.getUsername(), userId, this.tokenFactory.getJwtProperties().getTokenExpirationTime());

                this.useTokenrService.cleanCacheByKey("ROLE_".concat(userContext.getUsername()));

                this.useTokenrService.cleanCacheByKey("RESOURCE_".concat(userContext.getUsername()));

                this.useTokenrService.cleanCacheByKey("USERINFO_".concat(userId));
            } catch (DataAccessResourceFailureException e) {
                resp = GeneratorResult.genErrorResult();
                resp.setCode("00000011");
                resp.setMessage("访问数据资源故障");
                this.logger.error("处理用户信息缓存异常", e);
            }

            this.mapper.writeValue(response.getWriter(), resp);


            clearAuthenticationAttributes(request);
        } catch (MyBatisSystemException e) {
            this.logger.error("登录数据库操作异常", e);
            ResponseEntity<Map<String, String>> resp = GeneratorResult.genErrorResult();
            resp.setCode("00000002");
            resp.setMessage("数据库操作失败");
            this.mapper.writeValue(response.getWriter(), resp);
        } catch (Exception e) {
            this.logger.error("登录操作异常", e);
            ResponseEntity<Map<String, String>> resp = GeneratorResult.genErrorResult();
            resp.setCode("00000001");
            resp.setMessage("未知错误");
            this.mapper.writeValue(response.getWriter(), resp);
        }
    }


    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }

        session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
    }

}
