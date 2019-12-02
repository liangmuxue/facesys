package com.ss.isc.data.collect.common.model;

import java.util.Date;

public class AddPersonCollect {
  private String collectId;
  private String addPersonId;
  private int days;
  private String dayBegin;
  private String dayEnd;
  private Date createTime;
  private Date updateTime;
  
  public String getCollectId() { return this.collectId; }


  
  public void setCollectId(String collectId) { this.collectId = collectId; }


  
  public String getAddPersonId() { return this.addPersonId; }


  
  public void setAddPersonId(String addPersonId) { this.addPersonId = addPersonId; }


  
  public int getDays() { return this.days; }


  
  public void setDays(int days) { this.days = days; }


  
  public String getDayBegin() { return this.dayBegin; }


  
  public void setDayBegin(String dayBegin) { this.dayBegin = dayBegin; }


  
  public String getDayEnd() { return this.dayEnd; }


  
  public void setDayEnd(String dayEnd) { this.dayEnd = dayEnd; }


  
  public Date getCreateTime() { return this.createTime; }


  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }


  
  public Date getUpdateTime() { return this.updateTime; }


  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
