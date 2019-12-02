package com.ss.spider.system.department.model;

import com.ss.entity.DTEntity;
import com.ss.tools.ArraysUtils;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;


@Table(name = "CW_GE_DEPARTMENT")
public class Department extends DTEntity {

    private static final long serialVersionUID = -5974369274765005182L;
    @Id
    @Column(name = "DEPART_ID")
    private String departId;
    @Column(name = "ORG_ID")
    private String orgId;
    @Column(name = "DEPART_CODE")
    private String departCode;
    @Column(name = "DEPART_CNAME")
    private String departCname;
    @Column(name = "DEPART_ENAME")
    private String departEname;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "LINK_MAN_NAME")
    private String linkManName;
    @Column(name = "LINK_MAN_TEL")
    private String linkManTel;
    @Column(name = "LINK_MAN_FAX")
    private String linkManFax;
    @Transient
    private List<String> orgIdList;

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


    public String getDepartCode() {
        return this.departCode;
    }


    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }


    public String getDepartCname() {
        return this.departCname;
    }


    public void setDepartCname(String departCname) {
        this.departCname = departCname;
    }


    public String getDepartEname() {
        return this.departEname;
    }


    public void setDepartEname(String departEname) {
        this.departEname = departEname;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getLinkManName() {
        return this.linkManName;
    }


    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
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


    public List<String> getOrgIdList() {
        return this.orgIdList;
    }


    public void setOrgIdList(String orgIds) {
        if (StringUtils.hasText(orgIds)) {
            this.orgIdList = ArraysUtils.asList(orgIds);
        }
    }


    public void setOrgIdList(List<String> orgIdList) {
        this.orgIdList = orgIdList;
    }


    public String toString() {
        return "Department [departId=" + this.departId + ", orgId=" + this.orgId + ", departCode=" + this.departCode + ", departCname=" + this.departCname + ", departEname=" + this.departEname + ", remark=" + this.remark + ", status=" + this.status + ", linkManName=" + this.linkManName + ", linkManTel=" + this.linkManTel + ", linkManFax=" + this.linkManFax + ", orgIdList=" + this.orgIdList + "]";
    }

}
