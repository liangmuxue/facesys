package com.ss.isc.web.manage.baseinfo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebIscSecurityConfig extends WebMvcConfigurerAdapter {

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }


    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        addInterceptor.excludePathPatterns("/subscribed/**");
        addInterceptor.excludePathPatterns("/auth/login");
        addInterceptor.excludePathPatterns("/auth/token");
        addInterceptor.excludePathPatterns("/region/list");
        addInterceptor.excludePathPatterns("/mediaInfo/uploadImage");
        addInterceptor.excludePathPatterns("/region/treeData");
        addInterceptor.excludePathPatterns("/region/regionTree");
        addInterceptor.excludePathPatterns("/app/list");
        addInterceptor.excludePathPatterns("/baseinfo/enums/**");
        addInterceptor.excludePathPatterns("/sysparam/logout");
        addInterceptor.excludePathPatterns("/role/resource/list");
        addInterceptor.excludePathPatterns("/user/role/list");
        addInterceptor.excludePathPatterns("/user/get");
        addInterceptor.addPathPatterns("/**");
    }

}
