package com.ss.spider.system.user.form;

import com.ss.request.Pagination;
import org.hibernate.validator.constraints.NotBlank;

/**
* 用户
* @author chao
* @create 2019/12/3
* @email lishuangchao@ss-cas.com
**/
public class UserQuery extends Pagination {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{user.ids.empty}", groups = {com.ss.valide.APIGetsGroup.class})
    private String userId;
    private String opUserId;
    private String departId;
    private String orgId;
    private String workCode;
    private String loginName;
    private String name;
    private Integer status;
    private String position;
    private String email;
    private String phoneNumber;
    private String roleName;
    private String searchKey;
    private Short dataType;

    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


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


    public String getWorkCode() {
        return this.workCode;
    }


    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }


    public String getLoginName() {
        return this.loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getPosition() {
        return this.position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getRoleName() {
        return this.roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public String getSearchKey() {
        return this.searchKey;
    }


    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }


    public Short getDataType() {
        return this.dataType;
    }


    public void setDataType(Short dataType) {
        this.dataType = dataType;
    }


    public String getOpUserId() {
        return this.opUserId;
    }


    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

}
