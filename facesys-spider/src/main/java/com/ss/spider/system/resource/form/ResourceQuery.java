package com.ss.spider.system.resource.form;

import com.ss.request.Pagination;
import org.hibernate.validator.constraints.NotBlank;


public class ResourceQuery extends Pagination {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{resource.ids.empty}", groups = {com.ss.valide.APIGetsGroup.class})
    private String resourceId;
    private String appId;
    private String resCode;
    private String resCanme;
    private String resEname;
    private String parentId;
    private Integer status;
    private Integer resType;
    private String userId;

    public String getResourceId() {
        return this.resourceId;
    }


    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }


    public String getParentId() {
        return this.parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
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


    public Integer getResType() {
        return this.resType;
    }


    public void setResType(Integer resType) {
        this.resType = resType;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

}
