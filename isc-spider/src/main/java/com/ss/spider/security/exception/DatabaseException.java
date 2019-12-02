package com.ss.spider.security.exception;

import org.springframework.security.core.AuthenticationException;

public class DatabaseException extends AuthenticationException {

    public DatabaseException(String msg, Throwable t) {
        super(msg, t);
    }


    public DatabaseException(String msg) {
        super(msg);
    }

}
