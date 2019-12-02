package com.ss.isc.data.collect.common.model;

import java.util.Date;



































public class SpecialPerson
{
  private String id;
  private String peopleId;
  private String recordId;
  private int state;
  private int specialType;
  private int days;
  private String remark;
  private Date leaveTime;
  private Date createdTime;
  private Date updateTime;
  
  public SpecialPerson() {}
  
  public SpecialPerson(String id, int state) {
    this.id = id;
    this.state = state;
  }

  
  public String getId() { return this.id; }


  
  public void setId(String id) { this.id = id; }


  
  public String getPeopleId() { return this.peopleId; }


  
  public void setPeopleId(String peopleId) { this.peopleId = peopleId; }


  
  public int getState() { return this.state; }


  
  public void setState(int state) { this.state = state; }


  
  public int getDays() { return this.days; }


  
  public void setDays(int days) { this.days = days; }


  
  public String getRemark() { return this.remark; }


  
  public void setRemark(String remark) { this.remark = remark; }


  
  public Date getCreatedTime() { return this.createdTime; }


  
  public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }


  
  public Date getUpdateTime() { return this.updateTime; }


  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }


  
  public String getRecordId() { return this.recordId; }


  
  public void setRecordId(String recordId) { this.recordId = recordId; }


  
  public int getSpecialType() { return this.specialType; }


  
  public void setSpecialType(int specialType) { this.specialType = specialType; }


  
  public Date getLeaveTime() { return this.leaveTime; }


  
  public void setLeaveTime(Date leaveTime) { this.leaveTime = leaveTime; }
}
