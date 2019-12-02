package com.ss.spider.system.role.form;

import java.io.Serializable;


public class RoleResourceQuery implements Serializable {

    private static final long serialVersionUID = -1L;
    private String roleId;
    private String resourceId;
    private String userId;

    public String getRoleId() {
        return this.roleId;
    }


    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getResourceId() {
        return this.resourceId;
    }


    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

}
