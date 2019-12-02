package com.ss.spider.system.role.form;

import com.ss.request.Pagination;
import org.hibernate.validator.constraints.NotBlank;

/**
* 权限管理-角色
* @author chao
* @create 2019/10/8
* @email lishuangchao@ss-cas.com
**/
public class RoleQuery extends Pagination {

    private static final long serialVersionUID = -1L;
    @NotBlank(message = "{role.ids.empty}", groups = {com.ss.valide.APIGetsGroup.class})
    private String roleId;
    private String roleCname;
    private String roleEname;
    private Integer status;
    private String userId;

    public String getRoleId() {
        return this.roleId;
    }


    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getRoleCname() {
        return this.roleCname;
    }


    public void setRoleCname(String roleCname) {
        this.roleCname = roleCname;
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


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

}
