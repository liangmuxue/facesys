package com.ss.spider.core.exception;

import com.ss.enums.CommonEnumClass;
import com.ss.exception.ServiceException;
import com.ss.response.ArgumentInvalidResult;
import com.ss.response.GeneratorResult;
import com.ss.response.ResponseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return GeneratorResult.genResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        this.logger.error("request body is bad!e：{}", e);
        return GeneratorResult.genResult(HttpStatus.BAD_REQUEST.value(), "request_body_is_bad");
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException e) {
        this.logger.error("请求地址不存在,e：{}", e);
        return GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.UNKNOWN_FAIL.getKey(), "not found request url:".concat(e.getRequestURL()));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResponseEntity<String> resp = GeneratorResult.genResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        FieldError error = e.getBindingResult().getFieldError();
        ArgumentInvalidResult argumentResult = new ArgumentInvalidResult();
        argumentResult.setField(error.getField());
        argumentResult.setDefaultMessage(error.getDefaultMessage());
        return resp;
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public ResponseEntity<String> handleBindException(BindException e) {
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<ArgumentInvalidResult>();
        Iterator iterator = e.getBindingResult().getFieldErrors().iterator();
        if (iterator.hasNext()) {
            FieldError error = (FieldError) iterator.next();
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArgument.setField(error.getField());
            invalidArguments.add(invalidArgument);
        }


        ResponseEntity<String> resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.PARAM_ERROR.getKey(), CommonEnumClass.CommonInterfaceEnum.PARAM_ERROR.getValue());
        resp.setError(invalidArguments);

        return resp;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> handleServiceException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = (ConstraintViolation) violations.iterator().next();

        return GeneratorResult.genResult(HttpStatus.BAD_REQUEST.value(), violation.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<String> handleValidationException(ValidationException e) {
        return GeneratorResult.genResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return GeneratorResult.genResult(HttpStatus.METHOD_NOT_ALLOWED.value(), "request_method_not_supported");
    }


    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({org.springframework.web.HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<String> handleHttpMediaTypeNotSupportedException(Exception e) {
        return GeneratorResult.genResult(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "content_type_not_supported");
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MyBatisSystemException.class})
    public ResponseEntity<String> handlePersistenceException(MyBatisSystemException e) {
        return GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.DATABASE_ERROR.getKey(), CommonEnumClass.CommonInterfaceEnum.DATABASE_ERROR.getValue());
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({DataAccessResourceFailureException.class})
    public ResponseEntity<String> handleDataAccessResourceFailureException(DataAccessResourceFailureException e) {
        return GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.DATA_ACCESS_RESOURCE_FAILURE.getKey(), CommonEnumClass.CommonInterfaceEnum.DATA_ACCESS_RESOURCE_FAILURE.getValue());
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<String> handleServiceException(ServiceException e) {
        Integer code = Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String errorCode = code.toString();
        if (e.getCode() != null && e.getCode().length() > 0) {
            errorCode = e.getCode();
        } else {
            return GeneratorResult.genResult(code.intValue(), e.getMessage());
        }
        String message = e.getMessage();
        try {
            message = this.messageSource.getMessage(errorCode, e.getArgs(), message, LocaleContextHolder.getLocale());
        } catch (Exception exception) {
        }

        return GeneratorResult.genResult(errorCode, message);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        this.logger.error("Unknown error : ", e);
        String errorCode = CommonEnumClass.CommonInterfaceEnum.UNKNOWN_FAIL.getKey();
        try {
            String message = this.messageSource.getMessage(errorCode, null, CommonEnumClass.CommonInterfaceEnum.UNKNOWN_FAIL.getValue(), LocaleContextHolder.getLocale());
            return GeneratorResult.genResult(errorCode, message);
        } catch (Exception exception) {

            return GeneratorResult.genResult(errorCode, "Unknown error");
        }
    }

}
