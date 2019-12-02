package com.ss.exception;


public class BaseException extends Exception {

    private static final long serialVersionUID = 1L;
    private String exceptionMessage;

    public BaseException() {
    }

    public BaseException(String msg) {
        this.exceptionMessage = msg;
    }


    public BaseException(String msg, Throwable e) {
        this.exceptionMessage = msg;
        initCause(e);
    }


    public String getMessage() {
        return this.exceptionMessage;
    }


    public void setCause(Throwable e) {
        initCause(e);
    }


    public String toString() {
        return getClass().getName() + ": " + this.exceptionMessage;
    }

}
