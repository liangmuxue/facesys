package com.ss.spider.system.role.form;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
* 权限管理-添加角色
* @author chao
* @create 2019/10/8
* @email lishuangchao@ss-cas.com
**/
public class RoleForm implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{role.ids.empty}", groups = {com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 32, message = "{role.ids.length}", groups = {com.ss.valide.APIEditGroup.class})
    private String roleId;
    @Length(max = 50, message = "{role.ename.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String roleEname;
    @NotBlank(message = "{role.cname.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    @Length(min = 1, max = 30, message = "{role.cname.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String roleCname;
    @NotNull(message = "{role.status.empty}", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIOpStatusGroup.class})
    @Range(min = 0L, max = 1L, message = "{role.status.Range}", groups = {com.ss.valide.APIOpStatusGroup.class})
    private Integer status;
    @NotBlank(message = "{created.user.empty}", groups = {com.ss.valide.APIAddGroup.class})
    @Length(min = 1, max = 32, message = "{created.user.length}", groups = {com.ss.valide.APIAddGroup.class})
    private String createdUserid;
    @NotBlank(message = "{updated.user.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class, com.ss.valide.APIOpStatusGroup.class})
    @Length(min = 1, max = 32, message = "{updated.user.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class, com.ss.valide.APIOpStatusGroup.class})
    private String updatedUserid;
    @Length(max = 120, message = "{role.remark.length}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
    private String remark;
    @NotBlank(message = "{role.ids.empty}", groups = {com.ss.valide.APIDeltGroup.class, com.ss.valide.APIOpStatusGroup.class})
    @Length(min = 1, max = 2000, message = "{role.ids.length}", groups = {com.ss.valide.APIDeltGroup.class, com.ss.valide.APIOpStatusGroup.class})
    private String roleIds;
    @NotBlank(message = "{deleted.user.empty}", groups = {com.ss.valide.APIDeltGroup.class})
    @Length(min = 1, max = 32, message = "{deleted.user.length}", groups = {com.ss.valide.APIDeltGroup.class})
    private String deletedUserid;
    private String userId;
    private int thorough = 0;


    public String getRoleId() {
        return this.roleId;
    }


    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getRoleEname() {
        return this.roleEname;
    }


    public void setRoleEname(String roleEname) {
        this.roleEname = roleEname;
    }


    public Integer getStatus() {
        return this.status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getRoleCname() {
        return this.roleCname;
    }


    public void setRoleCname(String roleCname) {
        this.roleCname = roleCname;
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


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getRoleIds() {
        return this.roleIds;
    }


    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }


    public String getDeletedUserid() {
        return this.deletedUserid;
    }


    public void setDeletedUserid(String deletedUserid) {
        this.deletedUserid = deletedUserid;
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
