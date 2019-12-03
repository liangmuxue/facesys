package com.ss.facesys.data.trail.common.model;

import com.ss.entity.DTEntity;
import java.util.Date;





































public class WifiCollectData
  extends DTEntity
{
  private static final long serialVersionUID = -4224465833013165767L;
  private Integer id;
  private String villageCode;
  private String deviceId;
  private Double lon;
  private Double lat;
  private Integer gisType;
  private String collectMac;
  private Date collectTime;
  
  public Integer getId() { return this.id; }

  
  public void setId(Integer id) { this.id = id; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }

  
  public String getDeviceId() { return this.deviceId; }

  
  public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

  
  public Double getLon() { return this.lon; }

  
  public void setLon(Double lon) { this.lon = lon; }

  
  public Double getLat() { return this.lat; }

  
  public void setLat(Double lat) { this.lat = lat; }

  
  public Integer getGisType() { return this.gisType; }

  
  public void setGisType(Integer gisType) { this.gisType = gisType; }

  
  public String getCollectMac() { return this.collectMac; }

  
  public void setCollectMac(String collectMac) { this.collectMac = collectMac; }

  
  public Date getCollectTime() { return this.collectTime; }

  
  public void setCollectTime(Date collectTime) { this.collectTime = collectTime; }

}
