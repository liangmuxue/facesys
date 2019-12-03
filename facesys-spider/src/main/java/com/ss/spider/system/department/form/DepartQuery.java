package com.ss.spider.system.department.form;

import com.ss.request.Pagination;
import com.ss.valide.APIGetsGroup;
import org.hibernate.validator.constraints.NotBlank;


public class DepartQuery extends Pagination {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{depart.ids.empty}", groups = {APIGetsGroup.class})
    private String departId;
    private String orgIds;
    private String departCode;
    private String departCname;
    private String departEname;
    private String linkManName;
    private String linkManTel;
    private String linkManFax;
    private String userId;

    public String getDepartId() {
        return this.departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
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

    public String getOrgIds() {
        return this.orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
