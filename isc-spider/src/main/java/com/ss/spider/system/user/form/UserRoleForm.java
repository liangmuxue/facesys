package com.ss.spider.system.user.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class UserRoleForm implements Serializable {

    private static final long serialVersionUID = 8103700811644294173L;
    private String userId;
    @NotBlank(message = "{user.ids.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIDeltGroup.class})
    @Length(min = 1, max = 32, message = "{user.ids.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIDeltGroup.class})
    private String opUserId;
    @NotBlank(message = "{role.ids.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIDeltGroup.class})
    private String roleIds;
    @NotBlank(message = "{created.user.empty}", groups = {com.ss.valide.APIAddGroup.class})
    @Length(min = 1, max = 32, message = "{created.user.length}", groups = {com.ss.valide.APIAddGroup.class})
    private String createdUserid;
    @NotBlank(message = "{deleted.user.empty}", groups = {com.ss.valide.APIDeltGroup.class})
    @Length(min = 1, max = 32, message = "{deleted.user.length}", groups = {com.ss.valide.APIDeltGroup.class})
    private String deletedUserid;

    public String getOpUserId() {
        return this.opUserId;
    }


    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getRoleIds() {
        return this.roleIds;
    }


    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }


    public String getCreatedUserid() {
        return this.createdUserid;
    }


    public void setCreatedUserid(String createdUserid) {
        this.createdUserid = createdUserid;
    }


    public String getDeletedUserid() {
        return this.deletedUserid;
    }


    public void setDeletedUserid(String deletedUserid) {
        this.deletedUserid = deletedUserid;
    }

}
