package com.ss.spider.system.role.model;

import com.ss.entity.DTEntity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* 权限管理-角色实体类
* @author chao
* @create 2019/10/8
* @email lishuangchao@ss-cas.com
**/
@Table(name = "CW_GE_ROLE")
public class Role extends DTEntity {

    private static final long serialVersionUID = 1613920438649631685L;
    @Id
    @Column(name = "ROLE_ID")
    private String roleId;
    @Column(name = "ROLE_CNAME")
    private String roleCname;
    @Column(name = "ROLE_ENAME")
    private String roleEname;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "INIT_FLAG")
    private Integer initFlag;

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


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getInitFlag() {
        return this.initFlag;
    }


    public void setInitFlag(Integer initFlag) {
        this.initFlag = initFlag;
    }


    public String toString() {
        return "Role [roleId=" + this.roleId + ", roleCname=" + this.roleCname + ", roleEname=" + this.roleEname + ", status=" + this.status + ", remark=" + this.remark + ", initFlag=" + this.initFlag + "]";
    }

}
