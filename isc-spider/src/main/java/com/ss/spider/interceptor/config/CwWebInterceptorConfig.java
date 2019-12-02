package com.ss.spider.interceptor.config;

import com.ss.spider.interceptor.filter.CwInterceptorFilter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CwWebInterceptorConfig {

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(CwInterceptorFilter());
        registration.addUrlPatterns(new String[]{"/*"});
        registration.setName("sessionFilter");
        return registration;
    }


    @Bean
    public Filter CwInterceptorFilter() {
        return new CwInterceptorFilter();
    }

}
