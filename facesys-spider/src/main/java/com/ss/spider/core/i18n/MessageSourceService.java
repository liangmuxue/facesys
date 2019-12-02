package com.ss.spider.core.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


@Component
public class MessageSourceService {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(int code) {
        return getMessage(code + "", new Object[0]);
    }


    public String getMessage(String code) {
        return getMessage(code, new Object[0]);
    }


    public String getMessage(String code, String defaultMessage) {
        return getMessage(code, null, defaultMessage);
    }


    public String getMessage(String code, String defaultMessage, Locale locale) {
        return getMessage(code, null, defaultMessage, locale);
    }


    public String getMessage(String code, Locale locale) {
        return getMessage(code, null, "", locale);
    }


    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }


    public String getMessage(String code, Object[] args, Locale locale) {
        return getMessage(code, args, "", locale);
    }


    public String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(code, args, defaultMessage, locale);
    }


    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return this.messageSource.getMessage(code, args, defaultMessage, locale);
    }

}
