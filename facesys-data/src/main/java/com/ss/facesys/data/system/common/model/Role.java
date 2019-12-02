package com.ss.facesys.data.system.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;





















































@Table(name = "cw_ge_role")
public class Role
  implements Serializable
{
  private static final long serialVersionUID = 8289085421555788631L;
  @Column(name = "ROLE_ID")
  private String roleId;
  @Column(name = "ROLE_CNAME")
  private String roleCName;
  @Column(name = "ROLE_ENAME")
  private String roleEName;
  @Column(name = "STATUS")
  private BigDecimal status;
  @Column(name = "REMARK")
  private String remark;
  @Column(name = "INIT_FLAG")
  private BigDecimal initFlag;
  @Column(name = "CREATED_TIME")
  private BigDecimal createdTime;
  @Column(name = "CREATED_USERID")
  private String createdUserId;
  @Column(name = "UPDATED_TIME")
  private BigDecimal updatedTime;
  @Column(name = "UPDATED_USERID")
  private String updatedUserId;
  @Column(name = "DELETED_TIME")
  private BigDecimal deletedTime;
  @Column(name = "DELETED_USERID")
  private String deletedUserId;
  @Transient
  private String userId;
  
  public String getRoleId() { return this.roleId; }

  
  public void setRoleId(String roleId) { this.roleId = roleId; }

  
  public String getRoleCName() { return this.roleCName; }

  
  public void setRoleCName(String roleCName) { this.roleCName = roleCName; }

  
  public String getRoleEName() { return this.roleEName; }

  
  public void setRoleEName(String roleEName) { this.roleEName = roleEName; }

  
  public BigDecimal getStatus() { return this.status; }

  
  public void setStatus(BigDecimal status) { this.status = status; }

  
  public String getRemark() { return this.remark; }

  
  public void setRemark(String remark) { this.remark = remark; }

  
  public BigDecimal getInitFlag() { return this.initFlag; }

  
  public void setInitFlag(BigDecimal initFlag) { this.initFlag = initFlag; }

  
  public BigDecimal getCreatedTime() { return this.createdTime; }

  
  public void setCreatedTime(BigDecimal createdTime) { this.createdTime = createdTime; }

  
  public String getCreatedUserId() { return this.createdUserId; }

  
  public void setCreatedUserId(String createdUserId) { this.createdUserId = createdUserId; }

  
  public BigDecimal getUpdatedTime() { return this.updatedTime; }

  
  public void setUpdatedTime(BigDecimal updatedTime) { this.updatedTime = updatedTime; }

  
  public String getUpdatedUserId() { return this.updatedUserId; }

  
  public void setUpdatedUserId(String updatedUserId) { this.updatedUserId = updatedUserId; }

  
  public BigDecimal getDeletedTime() { return this.deletedTime; }

  
  public void setDeletedTime(BigDecimal deletedTime) { this.deletedTime = deletedTime; }

  
  public String getDeletedUserId() { return this.deletedUserId; }

  
  public void setDeletedUserId(String deletedUserId) { this.deletedUserId = deletedUserId; }

  
  public String getUserId() { return this.userId; }

  
  public void setUserId(String userId) { this.userId = userId; }
}
