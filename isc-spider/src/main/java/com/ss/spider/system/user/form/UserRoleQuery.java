package com.ss.spider.system.user.form;

import java.io.Serializable;


public class UserRoleQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String opUserId;
    private String roleId;

    public String getOpUserId() {
        return this.opUserId;
    }


    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getRoleId() {
        return this.roleId;
    }


    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
