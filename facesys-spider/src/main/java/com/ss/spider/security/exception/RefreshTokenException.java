package com.ss.spider.security.exception;

import com.ss.spider.security.model.token.JwtToken;
import org.springframework.security.core.AuthenticationException;


public class RefreshTokenException extends AuthenticationException {

    private JwtToken token;

    public RefreshTokenException(String msg) {
        super(msg);
    }


    public RefreshTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }


    public String token() {
        return this.token.getToken();
    }

}
