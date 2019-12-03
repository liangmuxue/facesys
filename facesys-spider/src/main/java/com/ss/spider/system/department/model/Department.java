package com.ss.spider.system.department.model;

import com.ss.entity.DTEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.StringJoiner;

/**
 * 部门管理
 * 
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_ge_department")
public class Department extends DTEntity {

    private static final long serialVersionUID = -5974369274765005182L;
    @Id
    @Column(name = "depart_id")
    private String departId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "depart_code")
    private String departCode;
    @Column(name = "depart_cname")
    private String departCname;
    @Column(name = "depart_ename")
    private String departEname;
    @Column(name = "remark")
    private String remark;
    @Column(name = "status")
    private Integer status;
    @Column(name = "link_man_name")
    private String linkManName;
    @Column(name = "link_man_tel")
    private String linkManTel;
    @Column(name = "link_man_fax")
    private String linkManFax;

    @Transient
    private List<String> orgIdList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getDepartCname() {
        return departCname;
    }

    public void setDepartCname(String departCname) {
        this.departCname = departCname;
    }

    public String getDepartEname() {
        return departEname;
    }

    public void setDepartEname(String departEname) {
        this.departEname = departEname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLinkManName() {
        return linkManName;
    }

    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
    }

    public String getLinkManTel() {
        return linkManTel;
    }

    public void setLinkManTel(String linkManTel) {
        this.linkManTel = linkManTel;
    }

    public String getLinkManFax() {
        return linkManFax;
    }

    public void setLinkManFax(String linkManFax) {
        this.linkManFax = linkManFax;
    }

    public List<String> getOrgIdList() {
        return orgIdList;
    }

    public void setOrgIdList(List<String> orgIdList) {
        this.orgIdList = orgIdList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Department.class.getSimpleName() + "[", "]")
                .add("departId='" + departId + "'")
                .add("orgId='" + orgId + "'")
                .add("departCode='" + departCode + "'")
                .add("departCname='" + departCname + "'")
                .add("departEname='" + departEname + "'")
                .add("remark='" + remark + "'")
                .add("status=" + status)
                .add("linkManName='" + linkManName + "'")
                .add("linkManTel='" + linkManTel + "'")
                .add("linkManFax='" + linkManFax + "'")
                .add("orgIdList=" + orgIdList)
                .toString();
    }

}
