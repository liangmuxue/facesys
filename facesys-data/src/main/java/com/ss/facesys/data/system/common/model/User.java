package com.ss.facesys.data.system.common.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
* 账户实体类
* @author chao
* @create 2019/12/3
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_ge_user")
public class User implements Serializable {
  private static final long serialVersionUID = 6035225092199121479L;
  @Column(name = "user_id")
  private String userId;
  @Column(name = "depart_id")
  private String departId;
  @Column(name = "org_id")
  private String orgId;
  @Column(name = "work_code")
  private String workCode;
  @Column(name = "login_name")
  private String loginName;
  @Column(name = "password")
  private String password;
  @Column(name = "salt")
  private String salt;
  @Column(name = "name")
  private String name;
  @Column(name = "sex")
  private Double sex;
  @Column(name = "brithday")
  private String birthday;
  @Column(name = "app_on_off")
  private Double appOnOff;
  @Column(name = "status")
  private Double status;
  @Column(name = "position")
  private String position;
  @Column(name = "email")
  private String email;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "picture_url")
  private String pictureUrl;
  @Column(name = "remark")
  private String remark;
  @Column(name = "init_flag")
  private Double initFlag;
  @Column(name = "ip")
  private String ip;
  @Column(name = "create_time")
  private Double createTime;
  @Column(name = "create_user_id")
  private String createUserId;
  @Column(name = "update_time")
  private Double updateTime;
  @Column(name = "update_user_id")
  private String updateUserId;
  @Column(name = "delete_time")
  private Double deleteTime;
  @Column(name = "delete_user_id")
  private String deleteUserId;
  @Transient
  private String orgName;
  @Transient
  private String roleName;
  @Transient
  private String roleId;
  @Transient
  private Integer currentPage;
  @Transient
  private Integer pageSize;
  @Transient
  private String createdName;
  @Transient
  private List<Role> rList;
  @Transient
  private String condition;
  @Transient
  private String opUserId;
  @Transient
  private String departCName;
  @Transient
  private String statusName;
  @Transient
  private List<String> orgIds;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getDepartId() {
    return departId;
  }

  public void setDepartId(String departId) {
    this.departId = departId;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getWorkCode() {
    return workCode;
  }

  public void setWorkCode(String workCode) {
    this.workCode = workCode;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getSex() {
    return sex;
  }

  public void setSex(Double sex) {
    this.sex = sex;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public Double getAppOnOff() {
    return appOnOff;
  }

  public void setAppOnOff(Double appOnOff) {
    this.appOnOff = appOnOff;
  }

  public Double getStatus() {
    return status;
  }

  public void setStatus(Double status) {
    this.status = status;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Double getInitFlag() {
    return initFlag;
  }

  public void setInitFlag(Double initFlag) {
    this.initFlag = initFlag;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Double getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Double createTime) {
    this.createTime = createTime;
  }

  public String getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
  }

  public Double getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Double updateTime) {
    this.updateTime = updateTime;
  }

  public String getUpdateUserId() {
    return updateUserId;
  }

  public void setUpdateUserId(String updateUserId) {
    this.updateUserId = updateUserId;
  }

  public Double getDeleteTime() {
    return deleteTime;
  }

  public void setDeleteTime(Double deleteTime) {
    this.deleteTime = deleteTime;
  }

  public String getDeleteUserId() {
    return deleteUserId;
  }

  public void setDeleteUserId(String deleteUserId) {
    this.deleteUserId = deleteUserId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public String getCreatedName() {
    return createdName;
  }

  public void setCreatedName(String createdName) {
    this.createdName = createdName;
  }

  public List<Role> getrList() {
    return rList;
  }

  public void setrList(List<Role> rList) {
    this.rList = rList;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getOpUserId() {
    return opUserId;
  }

  public void setOpUserId(String opUserId) {
    this.opUserId = opUserId;
  }

  public String getDepartCName() {
    return departCName;
  }

  public void setDepartCName(String departCName) {
    this.departCName = departCName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public List<String> getOrgIds() {
    return orgIds;
  }

  public void setOrgIds(List<String> orgIds) {
    this.orgIds = orgIds;
  }
}
