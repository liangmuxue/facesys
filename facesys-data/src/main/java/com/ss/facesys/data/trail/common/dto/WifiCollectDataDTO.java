package com.ss.facesys.data.trail.common.dto;

import com.ss.entity.DTEntity;
import java.util.Date;









public class WifiCollectDataDTO
  extends DTEntity {
  private static final long serialVersionUID = -5694333488500753401L;
  private Integer id;
  private String villageCode;
  private String deviceId;
  private Double lon;
  private Double lng;
  private Double lat;
  private Integer gisType;
  private String collectMac;
  private String collectAddress;
  private Date collectTime;

  public Integer getId() {
    return this.id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public String getVillageCode() {
    return this.villageCode;
  }


  public void setVillageCode(String villageCode) {
    this.villageCode = villageCode;
  }


  public String getDeviceId() {
    return this.deviceId;
  }


  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }


  public Double getLon() {
    return this.lon;
  }


  public void setLon(Double lon) {
    this.lon = lon;
  }


  public Double getLng() {
    return this.lng;
  }


  public void setLng(Double lng) {
    this.lng = lng;
  }


  public Double getLat() {
    return this.lat;
  }


  public void setLat(Double lat) {
    this.lat = lat;
  }


  public Integer getGisType() {
    return this.gisType;
  }


  public void setGisType(Integer gisType) {
    this.gisType = gisType;
  }


  public String getCollectMac() {
    return this.collectMac;
  }


  public void setCollectMac(String collectMac) {
    this.collectMac = collectMac;
  }


  public String getCollectAddress() {
    return this.collectAddress;
  }


  public void setCollectAddress(String collectAddress) {
    this.collectAddress = collectAddress;
  }


  public Date getCollectTime() {
    return this.collectTime;
  }


  public void setCollectTime(Date collectTime) {
    this.collectTime = collectTime;
  }
}
