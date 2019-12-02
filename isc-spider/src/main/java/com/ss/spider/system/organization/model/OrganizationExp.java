package com.ss.spider.system.organization.model;


import java.io.Serializable;

public class OrganizationExp implements Serializable {
    private String orgId;

    private String orgCode;

    private String orgCname;

    private String orgEname;

    private String address;

    private String zipCode;

    private String telephone;

    private String fax;

    private Short status;

    private String parentId;

    private String linkManName;

    private String linkManDept;

    private String linkManPos;

    private String linkManTel;

    private String linkManFax;

    private String linkManEmail;

    private String fromSystem;

    private Short isLinkage;

    private String remark;

    private Long createdTime;

    private String createdUserid;

    private Long updatedTime;

    private String updatedUserid;

    private Long deletedTime;

    private String deletedUserid;

    private String departh;

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgCname() {
        return orgCname;
    }

    public void setOrgCname(String orgCname) {
        this.orgCname = orgCname == null ? null : orgCname.trim();
    }

    public String getOrgEname() {
        return orgEname;
    }

    public void setOrgEname(String orgEname) {
        this.orgEname = orgEname == null ? null : orgEname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getLinkManName() {
        return linkManName;
    }

    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName == null ? null : linkManName.trim();
    }

    public String getLinkManDept() {
        return linkManDept;
    }

    public void setLinkManDept(String linkManDept) {
        this.linkManDept = linkManDept == null ? null : linkManDept.trim();
    }

    public String getLinkManPos() {
        return linkManPos;
    }

    public void setLinkManPos(String linkManPos) {
        this.linkManPos = linkManPos == null ? null : linkManPos.trim();
    }

    public String getLinkManTel() {
        return linkManTel;
    }

    public void setLinkManTel(String linkManTel) {
        this.linkManTel = linkManTel == null ? null : linkManTel.trim();
    }

    public String getLinkManFax() {
        return linkManFax;
    }

    public void setLinkManFax(String linkManFax) {
        this.linkManFax = linkManFax == null ? null : linkManFax.trim();
    }

    public String getLinkManEmail() {
        return linkManEmail;
    }

    public void setLinkManEmail(String linkManEmail) {
        this.linkManEmail = linkManEmail == null ? null : linkManEmail.trim();
    }

    public String getFromSystem() {
        return fromSystem;
    }

    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem == null ? null : fromSystem.trim();
    }

    public Short getIsLinkage() {
        return isLinkage;
    }

    public void setIsLinkage(Short isLinkage) {
        this.isLinkage = isLinkage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUserid() {
        return createdUserid;
    }

    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid == null ? null : createdUserid.trim();
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedUserid() {
        return updatedUserid;
    }

    public void setUpdatedUserid(String updatedUserid) {
        this.updatedUserid = updatedUserid == null ? null : updatedUserid.trim();
    }

    public Long getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Long deletedTime) {
        this.deletedTime = deletedTime;
    }

    public String getDeletedUserid() {
        return deletedUserid;
    }

    public void setDeletedUserid(String deletedUserid) {
        this.deletedUserid = deletedUserid == null ? null : deletedUserid.trim();
    }

    public String getDeparth() {
        return departh;
    }

    public void setDeparth(String departh) {
        this.departh = departh == null ? null : departh.trim();
    }
}
