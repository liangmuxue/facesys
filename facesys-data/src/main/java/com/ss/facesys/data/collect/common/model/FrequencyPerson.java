package com.ss.facesys.data.collect.common.model;

import java.util.Date;























public class FrequencyPerson
{
  private String id;
  private int state;
  private int amount;
  private String label;
  private String remark;
  private Date createdTime;
  private Date updateTime;
  
  public FrequencyPerson() {}
  
  public FrequencyPerson(String id, int state, String label) {
    this.id = id;
    this.state = state;
    this.label = label;
  }

  
  public String getId() { return this.id; }

  
  public void setId(String id) { this.id = id; }

  
  public int getState() { return this.state; }

  
  public void setState(int state) { this.state = state; }

  
  public int getAmount() { return this.amount; }

  
  public void setAmount(int amount) { this.amount = amount; }

  
  public String getLabel() { return this.label; }

  
  public void setLabel(String label) { this.label = label; }

  
  public String getRemark() { return this.remark; }

  
  public void setRemark(String remark) { this.remark = remark; }

  
  public Date getCreatedTime() { return this.createdTime; }

  
  public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }

  
  public Date getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
