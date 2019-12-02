package com.ss.isc.data.system.common.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
* 用户实体类
* @author chao
* @create 2019/10/10
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_ge_user")
public class User implements Serializable {
  private static final long serialVersionUID = 6035225092199121479L;
  @Column(name = "USER_ID")
  private String userId;
  @Column(name = "DEPART_ID")
  private String departId;
  @Column(name = "ORG_ID")
  private String orgId;
  @Column(name = "WORK_CODE")
  private String workCode;
  @Column(name = "LOGIN_NAME")
  private String loginName;
  @Column(name = "PASSWORD")
  private String password;
  @Column(name = "SALT")
  private String salt;
  @Column(name = "NAME")
  private String name;
  @Column(name = "SEX")
  private Double sex;
  @Column(name = "BRITHDAY")
  private String birthday;
  @Column(name = "APP_ON_OFF")
  private Double appOnOff;
  @Column(name = "STATUS")
  private Double status;
  @Column(name = "POSITION")
  private String position;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;
  @Column(name = "PICTURE_URL")
  private String pictureUrl;
  @Column(name = "REMARK")
  private String remark;
  @Column(name = "INIT_FLAG")
  private Double initFlag;
  @Column(name = "CREATED_TIME")
  private Double createdTime;
  @Column(name = "CREATED_USERID")
  private String createdUserid;
  @Column(name = "UPDATED_TIME")
  private Double updatedTime;
  @Column(name = "UPDATED_USERID")
  private String updatedUserId;
  @Column(name = "DELETED_TIME")
  private Double deletedTime;
  @Column(name = "DELETED_USERID")
  private String deletedUserId;
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
  
  public String getUserId() { return this.userId; }

  
  public void setUserId(String userId) { this.userId = userId; }

  
  public String getDepartId() { return this.departId; }

  
  public void setDepartId(String departId) { this.departId = departId; }

  
  public String getOrgId() { return this.orgId; }

  
  public void setOrgId(String orgId) { this.orgId = orgId; }

  
  public String getWorkCode() { return this.workCode; }

  
  public void setWorkCode(String workCode) { this.workCode = workCode; }

  
  public String getLoginName() { return this.loginName; }

  
  public void setLoginName(String loginName) { this.loginName = loginName; }

  
  public String getPassword() { return this.password; }

  
  public void setPassword(String password) { this.password = password; }

  
  public String getSalt() { return this.salt; }

  
  public void setSalt(String salt) { this.salt = salt; }

  
  public String getName() { return this.name; }

  
  public void setName(String name) { this.name = name; }

  
  public Double getSex() { return this.sex; }

  
  public void setSex(Double sex) { this.sex = sex; }

  
  public String getBirthday() { return this.birthday; }

  
  public void setBirthday(String birthday) { this.birthday = birthday; }

  
  public Double getAppOnOff() { return this.appOnOff; }

  
  public void setAppOnOff(Double appOnOff) { this.appOnOff = appOnOff; }

  
  public Double getStatus() { return this.status; }

  
  public void setStatus(Double status) { this.status = status; }

  
  public String getPosition() { return this.position; }

  
  public void setPosition(String position) { this.position = position; }

  
  public String getEmail() { return this.email; }

  
  public void setEmail(String email) { this.email = email; }

  
  public String getPhoneNumber() { return this.phoneNumber; }

  
  public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

  
  public String getPictureUrl() { return this.pictureUrl; }

  
  public void setPictureUrl(String pictureUrl) { this.pictureUrl = pictureUrl; }

  
  public String getRemark() { return this.remark; }

  
  public void setRemark(String remark) { this.remark = remark; }

  
  public Double getInitFlag() { return this.initFlag; }

  
  public void setInitFlag(Double initFlag) { this.initFlag = initFlag; }

  
  public Double getCreatedTime() { return this.createdTime; }

  
  public void setCreatedTime(Double createdTime) { this.createdTime = createdTime; }

  
  public String getCreatedUserid() { return this.createdUserid; }

  
  public void setCreatedUserid(String createdUserid) { this.createdUserid = createdUserid; }

  
  public Double getUpdatedTime() { return this.updatedTime; }

  
  public void setUpdatedTime(Double updatedTime) { this.updatedTime = updatedTime; }

  
  public Double getDeletedTime() { return this.deletedTime; }

  
  public void setDeletedTime(Double deletedTime) { this.deletedTime = deletedTime; }

  
  public String getDeletedUserId() { return this.deletedUserId; }

  
  public void setDeletedUserId(String deletedUserId) { this.deletedUserId = deletedUserId; }

  
  public String getOrgName() { return this.orgName; }

  
  public void setOrgName(String orgName) { this.orgName = orgName; }

  
  public String getRoleName() { return this.roleName; }

  
  public void setRoleName(String roleName) { this.roleName = roleName; }

  
  public Integer getCurrentPage() { return this.currentPage; }

  
  public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }

  
  public Integer getPageSize() { return this.pageSize; }

  
  public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }

  
  public String getRoleId() { return this.roleId; }

  
  public void setRoleId(String roleId) { this.roleId = roleId; }

  
  public String getUpdatedUserId() { return this.updatedUserId; }

  
  public void setUpdatedUserId(String updatedUserId) { this.updatedUserId = updatedUserId; }

  
  public String getCreatedName() { return this.createdName; }

  
  public void setCreatedName(String createdName) { this.createdName = createdName; }

  
  public List<Role> getrList() { return this.rList; }

  
  public void setrList(List<Role> rList) { this.rList = rList; }

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
}
