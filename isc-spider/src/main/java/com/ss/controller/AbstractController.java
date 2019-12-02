package com.ss.controller;

import com.ss.request.Pagination;
import com.ss.response.GeneratorResult;
import com.ss.response.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

/**
 * AbstractController：controller通用抽象父类
 * @author FrancisYs
 * @date 2019/8/13
 * @email yaoshuai@ss-cas.com
 */
public abstract class AbstractController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MessageSource messageSource;

    /** 获取当前页码 */
    public int getPageIndex(Pagination page) {
        return (page.getCurrentPage() == null || page.getCurrentPage() <= 0) ? 1 : page.getCurrentPage();
    }

    /** 获取每页数量 */
    public int getPageSize(Pagination page) {
        return (page.getPageSize() == null || page.getPageSize() <= 0) ? 10 : page.getPageSize();
    }

    /** 创建通用请求成功响应对象 */
    public <T> ResponseEntity<T> createSuccResponse() {
        return GeneratorResult.genSuccessResult();
    }

    /** 创建通用请求失败响应对象 */
    public <T> ResponseEntity<T> createFailResponse() {
        return GeneratorResult.genErrorResult();
    }

    /**
     * 创建通用请求失败响应对象：包含自定义错误码及响应内容
     */
    public <T> ResponseEntity<T> createFailResponse(String code, String message) {
        ResponseEntity<T> baseFailResult = GeneratorResult.genErrorResult();
        baseFailResult.setCode(code);
        baseFailResult.setMessage(message);
        return baseFailResult;
    }

    /**  */
    protected void addBindingError(String field, String errorCode, Object[] args, BindingResult bindingResult) {
        bindingResult.rejectValue(field, errorCode, this.messageSource.getMessage(errorCode, args, errorCode, LocaleContextHolder.getLocale()));
    }

    /**  */
    protected void addBindingError(String field, String errorCode, BindingResult bindingResult) {
        addBindingError(field, errorCode, null, bindingResult);
    }

    /**  */
    public <T> ResponseEntity<T> validite(BindingResult br) throws BindException {
        if (br.hasErrors()) {
            throw new BindException(br);
        }
        return createSuccResponse();
    }

}
