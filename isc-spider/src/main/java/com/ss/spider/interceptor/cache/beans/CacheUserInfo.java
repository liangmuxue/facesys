package com.ss.spider.interceptor.cache.beans;

import java.io.Serializable;


public class CacheUserInfo implements Serializable {

    private static final long serialVersionUID = 3650335300147222760L;
    private String userId;
    private String departId;
    private String orgId;
    private String orgName;
    private String workCode;
    private String loginName;
    private String name;
    private Integer sex;
    private String brithday;
    private Integer appOnOff;
    private Integer status;
    private String position;
    private String email;
    private String phoneNumber;
    private String pictureUrl;
    private String remark;
    private Integer initFlag;
    private boolean isSuperRole;

    public boolean getIsSuperRole() {
        return this.isSuperRole;
    }


    public void setIsSuperRole(boolean isSuperRole) {
        this.isSuperRole = isSuperRole;
    }


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


    public String getOrgName() {
        return this.orgName;
    }


    public void setOrgName(String orgName) {
        this.orgName = orgName;
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


    public String getBrithday() {
        return this.brithday;
    }


    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }


    public Integer getAppOnOff() {
        return this.appOnOff;
    }


    public void setAppOnOff(Integer appOnOff) {
        this.appOnOff = appOnOff;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getPosition() {
        return this.position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPictureUrl() {
        return this.pictureUrl;
    }


    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getInitFlag() {
        return this.initFlag;
    }


    public void setInitFlag(Integer initFlag) {
        this.initFlag = initFlag;
    }

}
