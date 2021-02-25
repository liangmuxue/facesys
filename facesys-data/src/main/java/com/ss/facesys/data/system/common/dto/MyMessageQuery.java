package com.ss.facesys.data.system.common.dto;

import com.ss.request.Pagination;

public class MyMessageQuery extends Pagination {

  private Integer id;
  //消息类型
  private Integer type;
  //报警类型
  private Integer alarmType;
  //报警等级
  private Integer alarmGrade;
  //关键字
  private String keyword;
  private Long startTime;
  private Long endTime;
  private String userId;

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

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Long getStartTime() {
    return startTime;
  }

  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }

  public Long getEndTime() {
    return endTime;
  }

  public void setEndTime(Long endTime) {
    this.endTime = endTime;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
}
