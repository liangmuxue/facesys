package com.ss.isc.data.process.common.model;

import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;









@Table(name = "cw_vehicle_discovery_detail")
public class VehicleDiscoveryDetail
{
  private Integer id;
  private String discoveryId;
  private Integer days;
  private String dayBegin;
  private String dayEnd;
  private Date createTime;
  private Date updateTime;
  @Transient
  private String villageCode;
  @Transient
  private String plateNumber;
  @Transient
  private String leaveId;
  @Transient
  private String retenId;
  @Transient
  private Integer withState;
  @Transient
  private Integer leaveState;
  @Transient
  private Integer retenState;
  private Date inOutTime;
  
  public Integer getId() { return this.id; }

  
  public void setId(Integer id) { this.id = id; }

  
  public String getDiscoveryId() { return this.discoveryId; }

  
  public void setDiscoveryId(String discoveryId) { this.discoveryId = discoveryId; }

  
  public Integer getDays() { return this.days; }

  
  public void setDays(Integer days) { this.days = days; }

  
  public String getDayBegin() { return this.dayBegin; }

  
  public void setDayBegin(String dayBegin) { this.dayBegin = dayBegin; }

  
  public String getDayEnd() { return this.dayEnd; }

  
  public void setDayEnd(String dayEnd) { this.dayEnd = dayEnd; }

  
  public Date getCreateTime() { return this.createTime; }

  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }

  
  public Date getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }

  
  public String getPlateNumber() { return this.plateNumber; }

  
  public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

  
  public Integer getWithState() { return this.withState; }

  
  public void setWithState(Integer withState) { this.withState = withState; }

  
  public Integer getLeaveState() { return this.leaveState; }

  
  public void setLeaveState(Integer leaveState) { this.leaveState = leaveState; }

  
  public Integer getRetenState() { return this.retenState; }

  
  public void setRetenState(Integer retenState) { this.retenState = retenState; }

  
  public Date getInOutTime() { return this.inOutTime; }

  
  public void setInOutTime(Date inOutTime) { this.inOutTime = inOutTime; }

  
  public String getLeaveId() { return this.leaveId; }

  
  public void setLeaveId(String leaveId) { this.leaveId = leaveId; }

  
  public String getRetenId() { return this.retenId; }

  
  public void setRetenId(String retenId) { this.retenId = retenId; }
}
