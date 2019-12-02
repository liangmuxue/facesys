package com.ss.spider.system.user.form;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
* 用户
* @author chao
* @create 2019/10/10
* @email lishuangchao@ss-cas.com
**/
public class UserForm implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{user.ids.empty}", groups = {com.ss.valide.APIDeltGroup.class, com.ss.valide.APIOpStatusGroup.class})
    @Length(min = 1, max = 2000, message = "{user.ids.length}", groups = {com.ss.valide.APIDeltGroup.class, com.ss.valide.APIOpStatusGroup.class})
    private String userIds;
    private String userId;
    @NotBlank(message = "{user.ids.empty}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{user.ids.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String opUserId;
    @NotBlank(message = "{deleted.user.empty}", groups = {com.ss.valide.APIDeltGroup.class})
    @Length(min = 1, max = 32, message = "{deleted.user.length}", groups = {com.ss.valide.APIDeltGroup.class})
    private String deletedUserid;
    @Length(max = 32, message = "{depart.ids.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String departId;
    @NotBlank(message = "{org.ids.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{org.ids.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String orgId;
    @Length(max = 30, message = "{user.workcode.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String workCode;
    @NotBlank(message = "{user.account.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 30, message = "{user.account.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String loginName;
    @NotBlank(message = "{user.password.empty}", groups = {com.ss.valide.APIAddGroup.class})
    @Length(min = 1, max = 32, message = "{user.password.length}", groups = {com.ss.valide.APIAddGroup.class})
    private String password;
    @NotBlank(message = "{user.name.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 40, message = "{user.name.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String name;
    @Length(max = 40, message = "{user.position.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String position;
    @Email(message = "{email.format.error}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(max = 50, message = "{email.max.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String email;
    @NotBlank(message = "{user.telephone.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(max = 32, message = "{user.telephone.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String phoneNumber;
    private String pictureUrl;
    @Length(max = 120, message = "{user.remark.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String remark;
    @NotBlank(message = "{created.user.empty}", groups = {com.ss.valide.APIAddGroup.class})
    @Length(min = 1, max = 32, message = "{created.user.length}", groups = {com.ss.valide.APIAddGroup.class})
    private String createdUserid;
    @NotBlank(message = "{updated.user.empty}", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIOpStatusGroup.class})
    @Length(min = 1, max = 32, message = "{updated.user.length}", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIOpStatusGroup.class})
    private String updatedUserid;
    private int thorough = 0;


    @NotNull(groups = {com.ss.valide.APIOpStatusGroup.class}, message = "{user.status.empty}")
    @Range(min = 0L, max = 1L, message = "{user.status.Range}", groups = {com.ss.valide.APIOpStatusGroup.class})
    private Integer status;

    @NotNull(groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class}, message = "{user.sex.empty}")
    private Integer sex;


    private String brithday;


    private Integer appOnOff;


    @NotBlank(message = "{role.ids.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String roleIds;


    public String getOpUserId() {
        return this.opUserId;
    }


    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }


    public String getRoleIds() {
        return this.roleIds;
    }


    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Integer getAppOnOff() {
        return this.appOnOff;
    }


    public void setAppOnOff(Integer appOnOff) {
        this.appOnOff = appOnOff;
    }


    public Integer getSex() {
        return this.sex;
    }


    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getBrithday() {
        return this.brithday;
    }


    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }


    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getDeletedUserid() {
        return this.deletedUserid;
    }


    public void setDeletedUserid(String deletedUserid) {
        this.deletedUserid = deletedUserid;
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


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
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


    public String getPictureUrl() {
        return this.pictureUrl;
    }


    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getCreatedUserid() {
        return this.createdUserid;
    }


    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid;
    }


    public String getUpdatedUserid() {
        return this.updatedUserid;
    }


    public void setUpdatedUserid(String updatedUserid) {
        this.updatedUserid = updatedUserid;
    }


    public int getThorough() {
        return this.thorough;
    }


    public void setThorough(int thorough) {
        this.thorough = thorough;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

}
