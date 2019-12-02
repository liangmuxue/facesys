package com.ss.spider.security.model;


public enum Scopes {

    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + name();
    }

}
