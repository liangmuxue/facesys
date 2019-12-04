package com.ss.spider.system.role.model;

import com.ss.entity.DTEntity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* 角色管理
* @author chao
* @create 2019/12/4
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_ge_role")
public class Role extends DTEntity {

    private static final long serialVersionUID = 1613920438649631685L;
    @Id
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "role_cname")
    private String roleCname;
    @Column(name = "role_ename")
    private String roleEname;
    @Column(name = "status")
    private Integer status;
    @Column(name = "remark")
    private String remark;
    @Column(name = "init_flag")
    private Integer initFlag;
    @Transient
    private String statusName;

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


    @Override
    public String toString() {
        return "Role [roleId=" + this.roleId + ", roleCname=" + this.roleCname + ", roleEname=" + this.roleEname + ", status=" + this.status + ", remark=" + this.remark + ", initFlag=" + this.initFlag + "]";
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
