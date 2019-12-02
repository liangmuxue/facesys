package com.ss.spider.system.resource.form;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


public class ResourceForm implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{resource.ids.empty}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{resource.ids.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String resourceId;
    @NotBlank(message = "{app.ids.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{app.ids.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String appId;
    @NotBlank(message = "{resource.code.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{resource.code.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String resCode;
    @NotBlank(message = "{resource.cname.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 30, message = "{resource.cname.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String resCanme;
    @Length(max = 30, message = "{resource.ename.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String resEname;
    @NotBlank(message = "{resource.ids.empty}", groups = {com.ss.valide.APIDeltGroup.class})
    private String resourceIds;
    @NotNull(message = "{resource.status.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private Integer status;
    @Length(max = 120, message = "{resource.remark.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String remark;
    @NotNull(message = "{resource.type.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private Integer resType;
    @NotNull(message = "{resource.order.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Range(min = 1L, max = 9999L, message = "resource.order.length", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private Integer resOrder;
    @Length(max = 512, message = "{resource.url.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String url;
    @Length(max = 512, message = "{resource.ico.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String resIco;
    private String parentId;
    @NotBlank(message = "{deleted.user.empty}", groups = {com.ss.valide.APIDeltGroup.class})
    @Length(min = 1, max = 32, message = "{deleted.user.length}", groups = {com.ss.valide.APIDeltGroup.class})
    private String deletedUserid;
    @NotBlank(message = "{created.user.empty}", groups = {com.ss.valide.APIAddGroup.class})
    @Length(min = 1, max = 32, message = "{created.user.length}", groups = {com.ss.valide.APIAddGroup.class})
    private String createdUserid;
    @NotBlank(message = "{updated.user.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{updated.user.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String updatedUserid;
    private String userId;
    private Integer thorough = Integer.valueOf(0);


    public Integer getThorough() {
        return this.thorough;
    }


    public void setThorough(Integer thorough) {
        this.thorough = thorough;
    }


    public String getResourceIds() {
        return this.resourceIds;
    }


    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }


    public String getDeletedUserid() {
        return this.deletedUserid;
    }


    public void setDeletedUserid(String deletedUserid) {
        this.deletedUserid = deletedUserid;
    }


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


    public String getCreatedUserid() {
        return this.createdUserid;
    }


    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid;
    }


    public String getUpdatedUserid() {
        return this.updatedUserid;
    }


    public void setUpdatedUserid(String updatedUserid) {
        this.updatedUserid = updatedUserid;
    }


    public String getParentId() {
        return this.parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

}
