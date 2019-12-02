package com.ss.spider.system.application.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "CW_APP_SYSTEM")
public class AppSystem implements Serializable {

    private static final long serialVersionUID = -1L;
    @Id
    @Column(name = "APP_ID")
    private String appId;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "APP_ICO")
    private String appIco;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "VERSION")
    private String version;
    @Column(name = "CREATED_TIME")
    private Long createdTime;

    public String getAppId() {
        return this.appId;
    }


    public void setAppId(String appId) {
        this.appId = appId;
    }


    public String getCode() {
        return this.code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAppIco() {
        return this.appIco;
    }


    public void setAppIco(String appIco) {
        this.appIco = appIco;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getVersion() {
        return this.version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


    public Long getCreatedTime() {
        return this.createdTime;
    }


    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }


    public String toString() {
        return "AppSystem [appId=" + this.appId + ", code=" + this.code + ", name=" + this.name + ", appIco=" + this.appIco + ", remark=" + this.remark + ", version=" + this.version + ", createdTime=" + this.createdTime + "]";
    }

}
