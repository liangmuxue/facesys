package com.ss.spider.system.resource.model;

import java.io.Serializable;
import java.util.List;

public class ResourceTree implements Serializable {

    private static final long serialVersionUID = 7409133586336222804L;
    private String resourceId;
    private String appId;
    private String resCode;
    private String resCanme;
    private String resEname;
    private Integer status;
    private String remark;
    private Integer resType;
    private Integer resOrder;
    private String url;
    private String resIco;
    private String parentId;
    private Integer authority = 0;
    private List<ResourceTree> children;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResCanme() {
        return resCanme;
    }

    public void setResCanme(String resCanme) {
        this.resCanme = resCanme;
    }

    public String getResEname() {
        return resEname;
    }

    public void setResEname(String resEname) {
        this.resEname = resEname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public Integer getResOrder() {
        return resOrder;
    }

    public void setResOrder(Integer resOrder) {
        this.resOrder = resOrder;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResIco() {
        return resIco;
    }

    public void setResIco(String resIco) {
        this.resIco = resIco;
    }

    public String getParentId() {
        return parentId;
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

    public List<ResourceTree> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceTree> children) {
        this.children = children;
    }
}
