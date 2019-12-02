package com.ss.isc.data.collect.common.model;

import java.util.Date;

public class AnalysisTask {
  private String id;
  private String taskName;
  private int state;
  private int taskType;
  private Date createTime;
  private Date updateTime;
  
  public String getId() { return this.id; }

  
  public void setId(String id) { this.id = id; }

  
  public String getTaskName() { return this.taskName; }

  
  public void setTaskName(String taskName) { this.taskName = taskName; }

  
  public int getState() { return this.state; }

  
  public void setState(int state) { this.state = state; }

  
  public int getTaskType() { return this.taskType; }

  
  public void setTaskType(int taskType) { this.taskType = taskType; }

  
  public Date getCreateTime() { return this.createTime; }

  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }

  
  public Date getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
