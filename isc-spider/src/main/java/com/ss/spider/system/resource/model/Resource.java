package com.ss.spider.system.resource.model;

import com.ss.entity.DTEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Table(name = "CW_GE_RESOURCE")
public class Resource extends DTEntity {

    private static final long serialVersionUID = 6902812274894039279L;
    @Id
    @Column(name = "RESOURCE_ID")
    private String resourceId;
    @Column(name = "APP_ID")
    private String appId;
    @Column(name = "RES_CODE")
    private String resCode;
    @Column(name = "RES_CANME")
    private String resCanme;
    @Column(name = "RES_ENAME")
    private String resEname;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "RES_TYPE")
    private Integer resType;
    @Column(name = "RES_ORDER")
    private Integer resOrder;
    @Column(name = "URL")
    private String url;
    @Column(name = "RES_ICO")
    private String resIco;
    @Column(name = "PARENT_ID")
    private String parentId;
    @Transient
    private Integer authority = 0;

    public String getResourceId() {
        return this.resourceId;
    }


    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }


    public String getAppId() {
        return this.appId;
    }


    public void setAppId(String appId) {
        this.appId = appId;
    }


    public String getResCode() {
        return this.resCode;
    }


    public void setResCode(String resCode) {
        this.resCode = resCode;
    }


    public String getResCanme() {
        return this.resCanme;
    }


    public void setResCanme(String resCanme) {
        this.resCanme = resCanme;
    }


    public String getResEname() {
        return this.resEname;
    }


    public void setResEname(String resEname) {
        this.resEname = resEname;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getResType() {
        return this.resType;
    }


    public void setResType(Integer resType) {
        this.resType = resType;
    }


    public Integer getResOrder() {
        return this.resOrder;
    }


    public void setResOrder(Integer resOrder) {
        this.resOrder = resOrder;
    }


    public String getUrl() {
        return this.url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getResIco() {
        return this.resIco;
    }


    public void setResIco(String resIco) {
        this.resIco = resIco;
    }


    public String getParentId() {
        return this.parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }
}
