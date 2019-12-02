package com.ss.isc.data.resource.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;


















@Table(name = "cw_base_accessdevice")
public class AccessDevice
  implements Serializable
{
  private static final long serialVersionUID = -2545669551110282323L;
  private String villageCode;
  private String deviceId;
  private String deviceType;
  private Integer mac;
  private String deviceName;
  private String buildingNo;
  private String houseNo;
  private Integer state;
  private String stateDesc;
  private String stateTime;
  private Integer gisType;
  private double lon;
  private double lat;
  private double alt;
  private String devicePicUrl;
  private Date createTime;
  private Date updateTime;
  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }

  
  public String getDeviceId() { return this.deviceId; }

  
  public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

  
  public String getDeviceType() { return this.deviceType; }

  
  public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

  
  public Integer getMac() { return this.mac; }

  
  public void setMac(Integer mac) { this.mac = mac; }

  
  public String getDeviceName() { return this.deviceName; }

  
  public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

  
  public String getBuildingNo() { return this.buildingNo; }

  
  public void setBuildingNo(String buildingNo) { this.buildingNo = buildingNo; }

  
  public String getHouseNo() { return this.houseNo; }

  
  public void setHouseNo(String houseNo) { this.houseNo = houseNo; }

  
  public Integer getState() { return this.state; }

  
  public void setState(Integer state) { this.state = state; }

  
  public String getStateDesc() { return this.stateDesc; }

  
  public void setStateDesc(String stateDesc) { this.stateDesc = stateDesc; }

  
  public String getStateTime() { return this.stateTime; }

  
  public void setStateTime(String stateTime) { this.stateTime = stateTime; }

  
  public Integer getGisType() { return this.gisType; }

  
  public void setGisType(Integer gisType) { this.gisType = gisType; }

  
  public double getLon() { return this.lon; }

  
  public void setLon(double lon) { this.lon = lon; }

  
  public double getLat() { return this.lat; }

  
  public void setLat(double lat) { this.lat = lat; }

  
  public double getAlt() { return this.alt; }

  
  public void setAlt(double alt) { this.alt = alt; }

  
  public String getDevicePicUrl() { return this.devicePicUrl; }

  
  public void setDevicePicUrl(String devicePicUrl) { this.devicePicUrl = devicePicUrl; }

  
  public Date getCreateTime() { return this.createTime; }

  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }

  
  public Date getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }



  
  public AccessDevice() {}


  
  public AccessDevice(String villageCode, String deviceId, String deviceType, Integer mac, String deviceName, String buildingNo, String houseNo, Integer state, String stateDesc, String stateTime, Integer gisType, double lon, double lat, double alt, String devicePicUrl, Date createTime, Date updateTime) {
    this.villageCode = villageCode;
    this.deviceId = deviceId;
    this.deviceType = deviceType;
    this.mac = mac;
    this.deviceName = deviceName;
    this.buildingNo = buildingNo;
    this.houseNo = houseNo;
    this.state = state;
    this.stateDesc = stateDesc;
    this.stateTime = stateTime;
    this.gisType = gisType;
    this.lon = lon;
    this.lat = lat;
    this.alt = alt;
    this.devicePicUrl = devicePicUrl;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }
}
