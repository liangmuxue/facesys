package com.ss.spider.security;

import java.util.Arrays;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


public class CustomCorsFilter extends CorsFilter {

    public CustomCorsFilter() {
        super(configurationSource());
    }


    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(Boolean.valueOf(true));
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setMaxAge(Long.valueOf(36000L));
        config.setAllowedMethods(
                Arrays.asList(new String[]{"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"}));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

}
