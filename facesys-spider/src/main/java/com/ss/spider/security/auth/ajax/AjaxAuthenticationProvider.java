package com.ss.spider.security.auth.ajax;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.spider.security.UserTokenService;
import com.ss.spider.security.entity.UserAuthEntity;
import com.ss.spider.security.exception.DatabaseException;
import com.ss.spider.security.model.UserContext;

import java.util.ArrayList;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserTokenService userService;

    @OpLog(model = "70005", desc = "登录", type = OperaTypeEnum.SELECT, logType = OpLog.LogType.LOGIN)
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");


        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserAuthEntity user = null;

        try {
            user = this.userService.getUserByLoginName(username);
        } catch (MyBatisSystemException e) {
            throw new DatabaseException("database error", e);
        }
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }


        if (StatusEnum.INVALID.getCode() == user.getStatus().intValue()) {
            throw new DisabledException("User is Disabled.");
        }


        if (!this.userService.isAuthSuccess(username, password)) {
            throw new BadCredentialsException("Username or Password not valid.");
        }


        UserContext userContext = UserContext.create(user.getUsername(), new ArrayList());
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }


    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
