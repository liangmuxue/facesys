package com.ss.spider.system.user.service.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class UserFuzzyMatchVO implements Serializable {

    private String userId;
    private String departId;
    private String orgId;
    private String workCode;
    private String loginName;
    private String name;
    private Integer sex;

    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getDepartId() {
        return this.departId;
    }


    public void setDepartId(String departId) {
        this.departId = departId;
    }


    public String getOrgId() {
        return this.orgId;
    }


    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public String getWorkCode() {
        return this.workCode;
    }


    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }


    public String getLoginName() {
        return this.loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getSex() {
        return this.sex;
    }


    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String toString() {
        return (new ToStringBuilder(this))
                .append("userId", this.userId)
                .append("departId", this.departId)
                .append("orgId", this.orgId)
                .append("workCode", this.workCode)
                .append("loginName", this.loginName)
                .append("name", this.name)
                .append("sex", this.sex)
                .toString();
    }

}
