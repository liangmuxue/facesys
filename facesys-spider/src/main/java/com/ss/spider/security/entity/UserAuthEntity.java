package com.ss.spider.security.entity;


public class UserAuthEntity {

    private String username;
    private String userId;
    private String password;
    private Integer status;

    public UserAuthEntity() {
    }

    public UserAuthEntity(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }


    public String getUsername() {
        return this.username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

}
