package com.ss.spider.security.exception;

import org.springframework.security.authentication.AuthenticationServiceException;


public class NotSupportedException extends AuthenticationServiceException {

    private static final long serialVersionUID = 3705043083010304496L;

    public NotSupportedException(String msg) {
        super(msg);
    }

}
