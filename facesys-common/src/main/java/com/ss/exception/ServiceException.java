package com.ss.exception;

import com.ss.tools.IResultCode;

import java.text.MessageFormat;


public class ServiceException extends BaseException {

    private static final long serialVersionUID = 1L;
    private String code;
    private Object[] args;
    private static final String MSG_FORMAT_TAG = "{0}";

    public ServiceException(String message) {
        super(message);
    }


    public ServiceException(String code, String message) {
        super(message);
        setCode(code);
    }


    public ServiceException(String code, Object[] args, String message) {
        super(message);
        setCode(code);
        setArgs(args);
    }


    public ServiceException(Integer code, String message) {
        super(message);
        setCode(code + "");
    }


    public ServiceException(Integer code, Object[] args, String message) {
        super(message);
        setCode(code + "");
        setArgs(args);
    }


    public ServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }


    public ServiceException(String code, Object[] args, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
        setArgs(args);
    }


    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }


    public ServiceException(IResultCode resultCode) {
        super(resultCode.getDesc());
        setCode(resultCode.getCode());
    }


    public ServiceException(IResultCode resultCode, Throwable cause) {
        super(resultCode.getDesc(), cause);
        setCode(resultCode.getCode());
    }


    public ServiceException(IResultCode resultCode, Object[] args) {
        super(messageFormat(resultCode.getDesc(), args));
        setCode(resultCode.getCode());
        setArgs(args);
    }


    public ServiceException(IResultCode resultCode, Object[] args, Throwable cause) {
        super(messageFormat(resultCode.getDesc(), args), cause);
        setCode(resultCode.getCode());
        setArgs(args);
    }


    public String getCode() {
        return this.code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public Object[] getArgs() {
        return this.args;
    }


    public void setArgs(Object[] args) {
        this.args = args;
    }


    private static String messageFormat(String message, Object[] args) {
        if (args == null || args.length == 0) {
            return message;
        }
        if (message == null) {
            return message;
        }
        if (!message.contains("{0}")) {
            return message;
        }
        return MessageFormat.format(message, args);
    }

}
