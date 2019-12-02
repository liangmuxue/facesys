package com.ss.isc.data.archives.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "cw_vehicle_record")
public class VehicleRecord implements Serializable {
  private static final long serialVersionUID = 4029572986525075942L;
  private Integer id;
  private String villageCode;
  private String tollgateID;
  private String cameraId;
  private String recordId;
  private String plateType;
  private String carType;
  private String plateColor;
  private String plateNumber;
  private Date inOutTime;
  private Integer inOutType;
  private String channelName;
  private String platePicUrl;
  private String plateNoPicUrl;
  private Date createTime;
  private Date updateTime;
  private Integer flag;
  @Transient
  private String address;
  @Transient
  private Date beginTime;
  @Transient
  private Date endTime;
  @Transient
  private Integer days;
  @Transient
  private String villageAddress;
  @Transient
  private String thirdId;
  @Transient
  private String dayBegin;
  @Transient
  private String dayEnd;
  @Transient
  private String cameraName;
  @Transient
  private String villageName;
  @Transient
  private String userIds;
  @Transient
  private String sqlString;
  @Transient
  private String dayEndEnd;
  @Transient
  private String inOutTypeName;
  @Transient
  private String tollgateName;
  private Integer withState;
  private Integer leaveState;
  private Integer retenState;
  
  public Integer getId() { return this.id; }

  
  public void setId(Integer id) { this.id = id; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }

  
  public String getTollgateID() { return this.tollgateID; }

  
  public void setTollgateID(String tollgateID) { this.tollgateID = tollgateID; }

  
  public String getCameraId() { return this.cameraId; }

  
  public void setCameraId(String cameraId) { this.cameraId = cameraId; }

  
  public String getRecordId() { return this.recordId; }

  
  public void setRecordId(String recordId) { this.recordId = recordId; }

  
  public String getPlateType() { return this.plateType; }

  
  public void setPlateType(String plateType) { this.plateType = plateType; }

  
  public String getCarType() { return this.carType; }

  
  public void setCarType(String carType) { this.carType = carType; }

  
  public String getPlateColor() { return this.plateColor; }

  
  public void setPlateColor(String plateColor) { this.plateColor = plateColor; }

  
  public String getPlateNumber() { return this.plateNumber; }

  
  public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

  
  public Date getInOutTime() { return this.inOutTime; }

  
  public void setInOutTime(Date inOutTime) { this.inOutTime = inOutTime; }

  
  public Integer getInOutType() { return this.inOutType; }

  
  public void setInOutType(Integer inOutType) { this.inOutType = inOutType; }

  
  public String getChannelName() { return this.channelName; }

  
  public void setChannelName(String channelName) { this.channelName = channelName; }

  
  public String getPlatePicUrl() { return this.platePicUrl; }

  
  public void setPlatePicUrl(String platePicUrl) { this.platePicUrl = platePicUrl; }

  
  public String getPlateNoPicUrl() { return this.plateNoPicUrl; }

  
  public void setPlateNoPicUrl(String plateNoPicUrl) { this.plateNoPicUrl = plateNoPicUrl; }

  
  public Date getCreateTime() { return this.createTime; }

  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }

  
  public Date getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

  
  public String getAddress() { return this.address; }

  
  public void setAddress(String address) { this.address = address; }

  
  public Date getBeginTime() { return this.beginTime; }

  
  public void setBeginTime(Date beginTime) { this.beginTime = beginTime; }

  
  public Date getEndTime() { return this.endTime; }

  
  public void setEndTime(Date endTime) { this.endTime = endTime; }

  
  public Integer getDays() { return this.days; }

  
  public void setDays(Integer days) { this.days = days; }

  
  public String getVillageAddress() { return this.villageAddress; }

  
  public void setVillageAddress(String villageAddress) { this.villageAddress = villageAddress; }

  
  public String getThirdId() { return this.thirdId; }

  
  public void setThirdId(String thirdId) { this.thirdId = thirdId; }

  
  public Integer getFlag() { return this.flag; }

  
  public void setFlag(Integer flag) { this.flag = flag; }

  
  public String getDayBegin() { return this.dayBegin; }

  
  public void setDayBegin(String dayBegin) { this.dayBegin = dayBegin; }

  
  public String getDayEnd() { return this.dayEnd; }

  
  public void setDayEnd(String dayEnd) { this.dayEnd = dayEnd; }

  
  public String getCameraName() { return this.cameraName; }

  
  public void setCameraName(String cameraName) { this.cameraName = cameraName; }

  
  public String getVillageName() { return this.villageName; }

  
  public void setVillageName(String villageName) { this.villageName = villageName; }

  
  public String getUserIds() { return this.userIds; }

  
  public void setUserIds(String userIds) { this.userIds = userIds; }

  
  public String getSqlString() { return this.sqlString; }

  
  public void setSqlString(String sqlString) { this.sqlString = sqlString; }

  
  public String getDayEndEnd() { return this.dayEndEnd; }

  
  public void setDayEndEnd(String dayEndEnd) { this.dayEndEnd = dayEndEnd; }

  
  public Integer getWithState() { return this.withState; }

  
  public void setWithState(Integer withState) { this.withState = withState; }

  
  public Integer getLeaveState() { return this.leaveState; }

  
  public void setLeaveState(Integer leaveState) { this.leaveState = leaveState; }

  
  public Integer getRetenState() { return this.retenState; }

  
  public void setRetenState(Integer retenState) { this.retenState = retenState; }

  public String getInOutTypeName() {
    return inOutTypeName;
  }

  public void setInOutTypeName(String inOutTypeName) {
    this.inOutTypeName = inOutTypeName;
  }

  public String getTollgateName() {
    return tollgateName;
  }

  public void setTollgateName(String tollgateName) {
    this.tollgateName = tollgateName;
  }
}
