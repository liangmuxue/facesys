package com.ss.spider.security.auth.ajax;

import com.ss.enums.CommonEnumClass;
import com.ss.response.GeneratorResult;
import com.ss.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final Logger logger;
    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    public AjaxAwareAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.logger = LoggerFactory.getLogger(getClass());


        this.objectMapper = objectMapper;
    }


    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");


        ResponseEntity<String> resp = GeneratorResult.genResult(HttpStatus.UNAUTHORIZED.value(), "Authentication failed");
        if (e instanceof org.springframework.security.core.userdetails.UsernameNotFoundException) {
            response.setStatus(HttpStatus.OK.value());
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.INVALID_ACCOUNT_PASSWORD.getCode(), CommonEnumClass.CommonInterfaceEnum.INVALID_ACCOUNT_PASSWORD.getDesc());
        } else if (e instanceof org.springframework.security.authentication.BadCredentialsException) {
            response.setStatus(HttpStatus.OK.value());
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.INVALID_ACCOUNT_PASSWORD.getCode(), CommonEnumClass.CommonInterfaceEnum.INVALID_ACCOUNT_PASSWORD.getDesc());
        } else if (e instanceof com.ss.spider.security.exception.ExpiredTokenException) {
            response.setStatus(HttpStatus.OK.value());
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.TOKEN_EXPIRES.getCode(), CommonEnumClass.CommonInterfaceEnum.TOKEN_EXPIRES.getDesc());
        } else if (e instanceof com.ss.spider.security.exception.RefreshTokenException) {
            response.setStatus(HttpStatus.OK.value());
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.REFRESH_TOKEN_EXPIRES.getCode(), CommonEnumClass.CommonInterfaceEnum.REFRESH_TOKEN_EXPIRES.getDesc());
        } else if (e instanceof org.springframework.security.authentication.DisabledException) {
            response.setStatus(HttpStatus.OK.value());
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.USER_IS_DISABLED.getCode(), CommonEnumClass.CommonInterfaceEnum.USER_IS_DISABLED.getDesc());
        } else if (e instanceof com.ss.spider.security.exception.NotSupportedException) {
            resp.setMessage(e.getMessage());
        } else if (e instanceof com.ss.spider.security.exception.DatabaseException) {
            response.setStatus(HttpStatus.OK.value());
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.DATABASE_ERROR.getCode(), CommonEnumClass.CommonInterfaceEnum.DATABASE_ERROR.getDesc());
        } else if (e instanceof org.springframework.security.authentication.AuthenticationServiceException) {
            response.setStatus(HttpStatus.OK.value());
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.REFRESH_TOKEN_EXPIRES.getCode(), CommonEnumClass.CommonInterfaceEnum.REFRESH_TOKEN_EXPIRES.getDesc());
        } else {
            this.logger.error("未知的鉴权异常", e);
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.ACCESS_DENIED.getCode(), CommonEnumClass.CommonInterfaceEnum.ACCESS_DENIED.getDesc());
        }
        this.objectMapper.writeValue(response.getWriter(), resp);
    }

}
