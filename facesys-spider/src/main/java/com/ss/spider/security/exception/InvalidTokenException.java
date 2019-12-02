package com.ss.spider.security.exception;


public class InvalidTokenException extends RuntimeException {

    private static final long serialVersionUID = -294671188037098603L;

    public InvalidTokenException() {
    }

    public InvalidTokenException(String msg) {
        super(msg);
    }


    public InvalidTokenException(String msg, Throwable t) {
        super(msg, t);
    }

}
