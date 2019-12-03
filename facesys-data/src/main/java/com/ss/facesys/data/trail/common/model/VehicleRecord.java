package com.ss.facesys.data.trail.common.model;

import com.ss.entity.DTEntity;
import java.util.Date;
import javax.persistence.Table;




























































@Table(name = "cw_vehicle_record")
public class VehicleRecord
  extends DTEntity
{
  private static final long serialVersionUID = 4686703494765511406L;
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

  
  public Integer getWithState() { return this.withState; }

  
  public void setWithState(Integer withState) { this.withState = withState; }

  
  public Integer getLeaveState() { return this.leaveState; }

  
  public void setLeaveState(Integer leaveState) { this.leaveState = leaveState; }

  
  public Integer getRetenState() { return this.retenState; }

  
  public void setRetenState(Integer retenState) { this.retenState = retenState; }
}
