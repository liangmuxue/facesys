package com.ss.facesys.data.collect.common.model;

import java.util.Date;
import javax.persistence.Transient;

public class FrequencyRecord {
  private String addPersonId;
  private Date beginTime;
  private Date endTime;
  private int amount;
  private Integer state;
  private Date createTime;
  private Date updateTime;
  @Transient
  private String label;
  
  public String getAddPersonId() { return this.addPersonId; }

  
  public void setAddPersonId(String addPersonId) { this.addPersonId = addPersonId; }

  
  public Date getBeginTime() { return this.beginTime; }

  
  public void setBeginTime(Date beginTime) { this.beginTime = beginTime; }

  
  public Date getEndTime() { return this.endTime; }

  
  public void setEndTime(Date endTime) { this.endTime = endTime; }

  
  public int getAmount() { return this.amount; }

  
  public void setAmount(int amount) { this.amount = amount; }

  
  public Integer getState() { return this.state; }

  
  public void setState(Integer state) { this.state = state; }

  
  public Date getCreateTime() { return this.createTime; }

  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }

  
  public Date getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

  
  public String getLabel() { return this.label; }

  
  public void setLabel(String label) { this.label = label; }
}
