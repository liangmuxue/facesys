package com.ss.spider.system.organization.form;

import com.ss.request.Pagination;
import com.ss.valide.APIGetsGroup;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 单位查询参数对象
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public class OrgQuery extends Pagination {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{org.orgId.empty}", groups = {APIGetsGroup.class})
    private String orgId;
    private String orgCode;
    private String orgCname;
    private String orgEname;
    private String address;
    private String zipCode;
    private String telephone;
    private String fax;
    private String parentId;
    private String parentName;
    private String linkManName;
    private String linkManDept;
    private String linkManPos;
    private String linkManTel;
    private String linkManFax;
    @Email(message = "{email.format.error}")
    private String linkManEmail;
    private Short isLinkage;
    private String userId;
    private Integer dataType = 1;

    public Integer getDataType() {
        return this.dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    public Short getIsLinkage() {
        return this.isLinkage;
    }

    public void setIsLinkage(Short isLinkage) {
        this.isLinkage = isLinkage;
    }

}