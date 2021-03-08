package com.ss.facesys.data.system.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Table(name = "cw_alarm_message")
public class AlarmMessage implements Serializable {

  private static final long serialVersionUID = 6035225092199121479L;

  @Id
  private Integer id;
  @Column(name = "alarm_type")
  private Integer alarmType;
  @Column(name = "alarm_grade")
  private Integer alarmGrade;
  @Column(name = "content")
  private String content;
  @Column(name = "status")
  private Integer status;
  @Column(name = "create_time")
  private Long createTime;
  @Column(name = "user_id")
  private String userId;
  @Column(name = "tenant_id")
  private String tenantId;
  @Column(name = "monitor_id")
  private String monitorId;

  @Transient
  private String alarmTypeName;
  @Transient
  private String alarmGradeName;
  @Transient
  private String monitorName;
  @Transient
  private String colorCode;
  @Transient
  private String peopleName;
  @Transient
  private String deviceName;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAlarmType() {
    return alarmType;
  }

  public void setAlarmType(Integer alarmType) {
    this.alarmType = alarmType;
  }

  public Integer getAlarmGrade() {
    return alarmGrade;
  }

  public void setAlarmGrade(Integer alarmGrade) {
    this.alarmGrade = alarmGrade;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getMonitorId() {
    return monitorId;
  }

  public void setMonitorId(String monitorId) {
    this.monitorId = monitorId;
  }

  public String getAlarmTypeName() {
    return alarmTypeName;
  }

  public void setAlarmTypeName(String alarmTypeName) {
    this.alarmTypeName = alarmTypeName;
  }

  public String getAlarmGradeName() {
    return alarmGradeName;
  }

  public void setAlarmGradeName(String alarmGradeName) {
    this.alarmGradeName = alarmGradeName;
  }

  public String getMonitorName() {
    return monitorName;
  }

  public void setMonitorName(String monitorName) {
    this.monitorName = monitorName;
  }

  public String getColorCode() {
    return colorCode;
  }

  public void setColorCode(String colorCode) {
    this.colorCode = colorCode;
  }

  public String getPeopleName() {
    return peopleName;
  }

  public void setPeopleName(String peopleName) {
    this.peopleName = peopleName;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }
}
