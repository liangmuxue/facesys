package com.ss.spider.system.role.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
* 角色权限
* @author chao
* @create 2019/10/8
* @email lishuangchao@ss-cas.com
**/
public class RoleResourceForm implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{role.ids.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIDeltGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{role.ids.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIDeltGroup.class, com.ss.valide.APIEditGroup.class})
    private String roleId;
    @NotBlank(message = "{resource.ids.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIDeltGroup.class, com.ss.valide.APIEditGroup.class})
    private String resourceIds;
    @NotBlank(message = "{created.user.empty}", groups = {com.ss.valide.APIAddGroup.class})
    @Length(min = 1, max = 32, message = "{created.user.length}", groups = {com.ss.valide.APIAddGroup.class})
    private String createdUserid;
    @NotBlank(message = "{deleted.user.empty}", groups = {com.ss.valide.APIDeltGroup.class})
    @Length(min = 1, max = 32, message = "{deleted.user.length}", groups = {com.ss.valide.APIDeltGroup.class})
    private String deletedUserid;
    private String userId;

    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getRoleId() {
        return this.roleId;
    }


    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getResourceIds() {
        return this.resourceIds;
    }


    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
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
