package com.ss.facesys.data.system.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "cw_ge_role")
public class Role implements Serializable {
  private static final long serialVersionUID = 8289085421555788631L;
  @Column(name = "role_id")
  private String roleId;
  @Column(name = "role_cname")
  private String roleCName;
  @Column(name = "role_ename")
  private String roleEName;
  @Column(name = "status")
  private BigDecimal status;
  @Column(name = "remark")
  private String remark;
  @Column(name = "init_flag")
  private BigDecimal initFlag;
  @Column(name = "create_time")
  private BigDecimal createTime;
  @Column(name = "create_user_id")
  private String createUserId;
  @Column(name = "update_time")
  private BigDecimal updatedTime;
  @Column(name = "update_user_id")
  private String updateUserId;
  @Column(name = "delete_time")
  private BigDecimal deleteTime;
  @Column(name = "delete_user_id")
  private String deleteUserId;
  @Transient
  private String userId;

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getRoleCName() {
    return roleCName;
  }

  public void setRoleCName(String roleCName) {
    this.roleCName = roleCName;
  }

  public String getRoleEName() {
    return roleEName;
  }

  public void setRoleEName(String roleEName) {
    this.roleEName = roleEName;
  }

  public BigDecimal getStatus() {
    return status;
  }

  public void setStatus(BigDecimal status) {
    this.status = status;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public BigDecimal getInitFlag() {
    return initFlag;
  }

  public void setInitFlag(BigDecimal initFlag) {
    this.initFlag = initFlag;
  }

  public BigDecimal getCreateTime() {
    return createTime;
  }

  public void setCreateTime(BigDecimal createTime) {
    this.createTime = createTime;
  }

  public String getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
  }

  public BigDecimal getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(BigDecimal updatedTime) {
    this.updatedTime = updatedTime;
  }

  public String getUpdateUserId() {
    return updateUserId;
  }

  public void setUpdateUserId(String updateUserId) {
    this.updateUserId = updateUserId;
  }

  public BigDecimal getDeleteTime() {
    return deleteTime;
  }

  public void setDeleteTime(BigDecimal deleteTime) {
    this.deleteTime = deleteTime;
  }

  public String getDeleteUserId() {
    return deleteUserId;
  }

  public void setDeleteUserId(String deleteUserId) {
    this.deleteUserId = deleteUserId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
