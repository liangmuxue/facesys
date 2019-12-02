package com.ss.spider.system.organization.model;

import com.ss.entity.DTEntity;

import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "CW_GE_ORGANIZATION")
public class Organization extends DTEntity {

    private static final long serialVersionUID = -5245102590723486068L;
    @Id
    @Column(name = "ORG_ID")
    private String orgId;
    @Column(name = "ORG_CODE")
    private String orgCode;
    @Column(name = "ORG_CNAME")
    private String orgCname;
    @Column(name = "ORG_ENAME")
    private String orgEname;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "ZIP_CODE")
    private String zipCode;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "PARENT_ID")
    private String parentId;
    @Column(name = "DEPARTH")
    private String departh;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "LINK_MAN_NAME")
    private String linkManName;
    @Column(name = "LINK_MAN_DEPT")
    private String linkManDept;
    @Column(name = "LINK_MAN_POS")
    private String linkManPos;
    @Column(name = "LINK_MAN_TEL")
    private String linkManTel;
    @Column(name = "LINK_MAN_FAX")
    private String linkManFax;
    @Column(name = "LINK_MAN_EMAIL")
    private String linkManEmail;
    @Column(name = "FROM_SYSTEM")
    private String fromSystem;
    @Column(name = "IS_LINKAGE")
    private Short isLinkage;

    private String parentName;

    private String updatedUserid;

    private String deletedUserid;

    private String createdUserid;

    private Long createdTime;

    private Long updatedTime;

    private Long deletedTime;

    @Override
    public Long getCreatedTime() {
        return createdTime;
    }

    @Override
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public Long getUpdatedTime() {
        return updatedTime;
    }

    @Override
    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public Long getDeletedTime() {
        return deletedTime;
    }

    @Override
    public void setDeletedTime(Long deletedTime) {
        this.deletedTime = deletedTime;
    }

    @Override
    public String getUpdatedUserid() {
        return updatedUserid;
    }

    @Override
    public void setUpdatedUserid(String updatedUserid) {
        this.updatedUserid = updatedUserid;
    }

    @Override
    public String getDeletedUserid() {
        return deletedUserid;
    }

    @Override
    public void setDeletedUserid(String deletedUserid) {
        this.deletedUserid = deletedUserid;
    }

    @Override
    public String getCreatedUserid() {
        return createdUserid;
    }

    @Override
    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<Organization> children;

    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    public String getOrgId() {
        return this.orgId;
    }


    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public String getOrgCode() {
        return this.orgCode;
    }


    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }


    public String getOrgCname() {
        return this.orgCname;
    }


    public void setOrgCname(String orgCname) {
        this.orgCname = orgCname;
    }


    public String getOrgEname() {
        return this.orgEname;
    }


    public void setOrgEname(String orgEname) {
        this.orgEname = orgEname;
    }


    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getZipCode() {
        return this.zipCode;
    }


    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    public String getTelephone() {
        return this.telephone;
    }


    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getFax() {
        return this.fax;
    }


    public void setFax(String fax) {
        this.fax = fax;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getParentId() {
        return this.parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public String getDeparth() {
        return this.departh;
    }


    public void setDeparth(String departh) {
        this.departh = departh;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getLinkManName() {
        return this.linkManName;
    }


    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
    }


    public String getLinkManDept() {
        return this.linkManDept;
    }


    public void setLinkManDept(String linkManDept) {
        this.linkManDept = linkManDept;
    }


    public String getLinkManPos() {
        return this.linkManPos;
    }


    public void setLinkManPos(String linkManPos) {
        this.linkManPos = linkManPos;
    }


    public String getLinkManTel() {
        return this.linkManTel;
    }


    public void setLinkManTel(String linkManTel) {
        this.linkManTel = linkManTel;
    }


    public String getLinkManFax() {
        return this.linkManFax;
    }


    public void setLinkManFax(String linkManFax) {
        this.linkManFax = linkManFax;
    }


    public String getLinkManEmail() {
        return this.linkManEmail;
    }


    public void setLinkManEmail(String linkManEmail) {
        this.linkManEmail = linkManEmail;
    }


    public String getFromSystem() {
        return this.fromSystem;
    }


    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }


    public Short getIsLinkage() {
        return this.isLinkage;
    }


    public void setIsLinkage(Short isLinkage) {
        this.isLinkage = isLinkage;
    }


    public List<String> getDeparthList() {
        if (this.departh == null || "".equals(this.departh)) {
            return null;
        }
        return Arrays.asList(this.departh.split(","));
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("Organization:{");
        sb.append("\"orgId\":\"")
                .append(this.orgId).append('"');
        sb.append(",\"orgCode\":\"")
                .append(this.orgCode).append('"');
        sb.append(",\"orgCname\":\"")
                .append(this.orgCname).append('"');
        sb.append(",\"orgEname\":\"")
                .append(this.orgEname).append('"');
        sb.append(",\"address\":\"")
                .append(this.address).append('"');
        sb.append(",\"zipCode\":\"")
                .append(this.zipCode).append('"');
        sb.append(",\"telephone\":\"")
                .append(this.telephone).append('"');
        sb.append(",\"fax\":\"")
                .append(this.fax).append('"');
        sb.append(",\"status\":")
                .append(this.status);
        sb.append(",\"parentId\":\"")
                .append(this.parentId).append('"');
        sb.append(",\"departh\":\"")
                .append(this.departh).append('"');
        sb.append(",\"remark\":\"")
                .append(this.remark).append('"');
        sb.append(",\"linkManName\":\"")
                .append(this.linkManName).append('"');
        sb.append(",\"linkManDept\":\"")
                .append(this.linkManDept).append('"');
        sb.append(",\"linkManPos\":\"")
                .append(this.linkManPos).append('"');
        sb.append(",\"linkManTel\":\"")
                .append(this.linkManTel).append('"');
        sb.append(",\"linkManFax\":\"")
                .append(this.linkManFax).append('"');
        sb.append(",\"linkManEmail\":\"")
                .append(this.linkManEmail).append('"');
        sb.append(",\"fromSystem\":\"")
                .append(this.fromSystem).append('"');
        sb.append(",\"isLinkage\":")
                .append(this.isLinkage);
        sb.append('}');
        return sb.toString();
    }

}
