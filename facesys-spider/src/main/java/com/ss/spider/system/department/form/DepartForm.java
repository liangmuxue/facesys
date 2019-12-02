package com.ss.spider.system.department.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class DepartForm implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{depart.ids.empty}", groups = {com.ss.valide.APIDeltGroup.class})
    private String departIds;
    @NotBlank(message = "{depart.ids.empty}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{depart.ids.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String departId;
    @NotBlank(message = "{deleted.user.empty}", groups = {com.ss.valide.APIDeltGroup.class})
    @Length(min = 1, max = 32, message = "{deleted.user.length}", groups = {com.ss.valide.APIDeltGroup.class})
    private String deletedUserid;
    @NotBlank(message = "{org.ids.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{org.ids.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String orgId;
    @NotBlank(message = "{depart.code.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 20, message = "{depart.code.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String departCode;
    @NotBlank(message = "{depart.cname.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 30, message = "{depart.cname.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String departCname;
    @NotBlank(message = "{updated.user.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{updated.user.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String updatedUserid;
    @NotBlank(message = "{created.user.empty}", groups = {com.ss.valide.APIAddGroup.class})
    @Length(min = 1, max = 32, message = "{created.user.length}", groups = {com.ss.valide.APIAddGroup.class})
    private String createdUserid;
    @Length(max = 50, message = "{depart.ename.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String departEname;
    @Length(max = 120, message = "{depart.remark.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String remark;
    @Length(max = 30, message = "{depart.linkManName.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String linkManName;
    @Length(max = 20, message = "{depart.linkManTel.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String linkManTel;
    @Length(max = 20, message = "{depart.linkManFax.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String linkManFax;
    private int thorough = 0;


    private String userId;


    public String getDepartId() {
        return this.departId;
    }


    public void setDepartId(String departId) {
        this.departId = departId;
    }


    public String getDepartIds() {
        return this.departIds;
    }


    public void setDepartIds(String departIds) {
        this.departIds = departIds;
    }


    public String getDeletedUserid() {
        return this.deletedUserid;
    }


    public void setDeletedUserid(String deletedUserid) {
        this.deletedUserid = deletedUserid;
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


    public String getUpdatedUserid() {
        return this.updatedUserid;
    }


    public void setUpdatedUserid(String updatedUserid) {
        this.updatedUserid = updatedUserid;
    }


    public String getCreatedUserid() {
        return this.createdUserid;
    }


    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid;
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


    public int getThorough() {
        return this.thorough;
    }


    public void setThorough(int thorough) {
        this.thorough = thorough;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

}
