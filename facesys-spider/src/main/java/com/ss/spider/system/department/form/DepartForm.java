package com.ss.spider.system.department.form;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * DepartForm
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public class DepartForm implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{depart.ids.empty}", groups = {APIDeltGroup.class})
    private String departIds;
    @NotBlank(message = "{depart.id.empty}", groups = {APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{depart.id.length}", groups = {APIEditGroup.class})
    private String departId;
    @NotBlank(message = "{depart.orgId.empty}", groups = {APIAddGroup.class})
    @Length(min = 1, max = 32, message = "{depart.orgId.length}", groups = {APIAddGroup.class})
    private String orgId;
    @NotBlank(message = "{depart.code.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    @Length(min = 1, max = 20, message = "{depart.code.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String departCode;
    @NotBlank(message = "{depart.cname.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    @Length(min = 1, max = 30, message = "{depart.cname.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String departCname;
    @NotBlank(message = "{depart.user.empty}", groups = {APIAddGroup.class, APIEditGroup.class, APIDeltGroup.class})
    @Length(min = 1, max = 32, message = "{depart.user.length}", groups = {APIAddGroup.class, APIEditGroup.class, APIDeltGroup.class})
    private String userId;
    @Length(max = 50, message = "{depart.ename.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String departEname;
    @Length(max = 120, message = "{depart.remark.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String remark;
    @Length(max = 30, message = "{depart.linkManName.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String linkManName;
    @Length(max = 20, message = "{depart.linkManTel.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String linkManTel;
    @Length(max = 20, message = "{depart.linkManFax.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String linkManFax;
    private int thorough = 0;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDepartIds() {
        return departIds;
    }

    public void setDepartIds(String departIds) {
        this.departIds = departIds;
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

    public int getThorough() {
        return thorough;
    }

    public void setThorough(int thorough) {
        this.thorough = thorough;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
