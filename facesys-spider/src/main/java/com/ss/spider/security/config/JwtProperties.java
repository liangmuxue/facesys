package com.ss.spider.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "cw.security.jwt")
public class JwtProperties {

    private static final int TOKEN_EXPIRATION_TIME = 30;
    private static final int REFRESH_TOKEN_EXPTIME = 60;
    private static final String ISSUER = "http://www.clouwalk.cn";
    private static final String TOKEN_SIGN_KEY = "xm8EV6Hy5RMFK4EEACIDAwQus";
    private Integer tokenExpirationTime = Integer.valueOf(30);


    private String tokenIssuer = "http://www.clouwalk.cn";


    private String tokenSigningKey = "xm8EV6Hy5RMFK4EEACIDAwQus";


    private Integer refreshTokenExpTime = Integer.valueOf(60);


    private String anonUrls;


    public Integer getRefreshTokenExpTime() {
        return this.refreshTokenExpTime;
    }


    public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime;
    }


    public Integer getTokenExpirationTime() {
        return this.tokenExpirationTime;
    }


    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }


    public String getTokenIssuer() {
        return this.tokenIssuer;
    }


    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }


    public String getTokenSigningKey() {
        return this.tokenSigningKey;
    }


    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }


    public String getAnonUrls() {
        return this.anonUrls;
    }


    public void setAnonUrls(String anonUrls) {
        this.anonUrls = anonUrls;
    }

}
