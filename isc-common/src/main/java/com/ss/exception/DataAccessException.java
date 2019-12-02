package com.ss.exception;


public class DataAccessException extends Exception {

    private static final long serialVersionUID = -5380232830765794261L;
    private String code;

    public DataAccessException(String message) {
        super(message);
    }


    public DataAccessException(String code, String message) {
        super(message);
        setCode(code);
    }


    public DataAccessException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }


    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }


    public String getCode() {
        return this.code;
    }


    public void setCode(String code) {
        this.code = code;
    }

}
