package com.ss.tools;

import com.ss.exception.ServiceException;
import org.slf4j.Logger;


public class ServiceExceptionUtils {

    private static final String ERROR_MESSAGE_TPL = "错误码: {},错误描述：{}";
    private static final String EMPTY_STR = "";

    public static void doThrow(IResultCode resultCode) throws ServiceException {
        throw new ServiceException(resultCode);
    }


    public static void doThrow(IResultCode resultCode, Object... args) throws ServiceException {
        throw new ServiceException(resultCode, args);
    }


    public static void doThrow(String desc, IResultCode resultCode, Object[] args, Throwable e) throws ServiceException {
        throw new ServiceException(resultCode, args, e);
    }


    public static void handle(Logger logger, String desc, IResultCode resultCode) throws ServiceException {
        logger.error(getDesc(desc).concat("错误码: {},错误描述：{}"), resultCode.getKey(), resultCode.getValue());
        doThrow(resultCode);
    }


    public static void handle(Logger logger, IResultCode resultCode) throws ServiceException {
        logger.error("错误码: {},错误描述：{}", resultCode.getKey(), resultCode.getValue());
        doThrow(resultCode);
    }


    public static void handle(Logger logger, IResultCode resultCode, Object... args) throws ServiceException {
        try {
            logger.error("错误码: {},错误描述：{}", resultCode.getKey(), String.format(resultCode.getValue(), args));
        } catch (Exception e) {
            logger.error("错误码: {},错误描述：{}", resultCode.getKey(), resultCode.getValue());
        }
        doThrow(resultCode, args);
    }


    public static void handle(Logger logger, String desc, IResultCode resultCode, Object... args) throws ServiceException {
        try {
            logger.error(getDesc(desc).concat("错误码: {},错误描述：{}"), resultCode.getKey(), String.format(resultCode.getValue(), args));
        } catch (Exception e) {
            logger.error(getDesc(desc).concat("错误码: {},错误描述：{}"), resultCode.getKey(), resultCode.getValue());
        }
        doThrow(resultCode, args);
    }


    public static void handle(Logger logger, String desc, String errorCode, String errorMsg) throws ServiceException {
        logger.error(getDesc(desc).concat("错误码: {},错误描述：{}"), errorCode, errorMsg);
        throw new ServiceException(errorCode, errorMsg);
    }


    public static void handle(Logger logger, String errorCode, String errorMsg) throws ServiceException {
        logger.error("错误码: {},错误描述：{}", errorCode, errorMsg);
        throw new ServiceException(errorCode, errorMsg);
    }


    public static void handle(Logger logger, Integer errorCode, String errorMsg) throws ServiceException {
        if (errorCode == null) {
            handle(logger, "", errorMsg);
        } else {
            handle(logger, String.valueOf(errorCode), errorMsg);
        }
    }


    public static void handle(Logger logger, String desc, Integer errorCode, String errorMsg) throws ServiceException {
        if (errorCode == null) {
            handle(logger, desc, "", errorMsg);
        } else {
            handle(logger, desc, String.valueOf(errorCode), errorMsg);
        }
    }


    private static String getDesc(String desc) {
        if (desc == null) {
            return "";
        }
        return desc;
    }

}
