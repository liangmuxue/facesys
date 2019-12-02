package com.ss.facesys.data.trail.common.dto;

import java.io.Serializable;
import java.util.Date;





















public class VehicleRecordDTO
  implements Serializable
{
  private static final long serialVersionUID = -1455289826054107109L;
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
  private Double lat;
  private Double lng;
  private Double lon;
  
  public static long getSerialversionuid() { return -1455289826054107109L; }

  
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

  
  public Double getLat() { return this.lat; }

  
  public void setLat(Double lat) { this.lat = lat; }

  
  public Double getLng() { return this.lng; }

  
  public void setLng(Double lng) { this.lng = lng; }

  
  public Double getLon() { return this.lon; }

  
  public void setLon(Double lon) { this.lon = lon; }


  
  public String toString() { return "VehicleRecordDTO [id=" + this.id + ", villageCode=" + this.villageCode + ", tollgateID=" + this.tollgateID + ", cameraId=" + this.cameraId + ", recordId=" + this.recordId + ", plateType=" + this.plateType + ", carType=" + this.carType + ", plateColor=" + this.plateColor + ", plateNumber=" + this.plateNumber + ", inOutTime=" + this.inOutTime + ", inOutType=" + this.inOutType + ", channelName=" + this.channelName + ", platePicUrl=" + this.platePicUrl + ", plateNoPicUrl=" + this.plateNoPicUrl + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + ", lat=" + this.lat + ", lng=" + this.lng + ", lon=" + this.lon + "]"; }
}
