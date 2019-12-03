package com.ss.spider.system.organization.form;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 单位表单参数对象
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public class OrgForm implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{org.ids.empty}", groups = {APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{org.ids.length}", groups = {APIEditGroup.class})
    private String orgId;
    @NotBlank(message = "{org.code.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    @Length(min = 1, max = 20, message = "{org.code.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String orgCode;
    @NotBlank(message = "{org.cname.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    @Length(min = 1, max = 30, message = "{org.cname.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String orgCname;
    @Length(max = 50, message = "{org.ename.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String orgEname;
    @NotBlank(message = "{org.address.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    @Length(max = 100, message = "{org.address.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String address;
    @Length(max = 6, message = "{org.zipCode.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String zipCode;
    @Length(max = 20, message = "{org.telephone.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String telephone;
    @Length(max = 20, message = "{org.fax.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String fax;
    @Length(max = 120, message = "{org.remark.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String remark;
    @Length(max = 30, message = "{org.linkManName.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String linkManName;
    @Length(max = 30, message = "{org.linkManDept.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String linkManDept;
    @Length(max = 30, message = "{org.linkManPos.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String linkManPos;
    @Length(max = 20, message = "{org.linkManTel.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String linkManTel;
    @Length(max = 20, message = "{org.linkManFax.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String linkManFax;
    @Email(message = "{email.format.error}", groups = {APIAddGroup.class, APIEditGroup.class})
    @Length(max = 50, message = "{org.linkManEmail.length}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String linkManEmail;
    @NotBlank(message = "{org.parentId.empty}", groups = {APIAddGroup.class})
    @Length(max = 32, message = "{org.parentId.length}", groups = {APIAddGroup.class})
    private String parentId;
    @NotBlank(message = "{org.ids.empty}", groups = {APIDeltGroup.class})
    private String orgIds;
    @NotBlank(message = "{org.user.empty}", groups = {APIAddGroup.class, APIEditGroup.class, APIDeltGroup.class})
    @Length(min = 1, max = 32, message = "{org.user.length}", groups = {APIAddGroup.class, APIEditGroup.class, APIDeltGroup.class})
    private String userId;
    private int thorough = 0;

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

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrgIds() {
        return this.orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
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