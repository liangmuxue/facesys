package com.ss.spider.system.user.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;


@Table(name = "CW_GE_USER_ROLE_REF")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 8332251437393223060L;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "ROLE_ID")
    private String roleId;

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


    public String toString() {
        return "UserRole [userId=" + this.userId + ", roleId=" + this.roleId + "]";
    }

}
