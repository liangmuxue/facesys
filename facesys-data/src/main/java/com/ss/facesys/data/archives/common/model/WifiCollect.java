package com.ss.facesys.data.archives.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;













@Table(name = "cw_wifi_collect")
public class WifiCollect
  implements Serializable
{
  private static final long serialVersionUID = -930130910184725966L;
  private Integer id;
  private String villageCode;
  private String deviceId;
  private Double lon;
  private Double lat;
  private Integer gisType;
  private String collectMac;
  private Date collectTime;
  private Date createTime;
  private Date updateTime;
  @Transient
  private String detailAddress;
  
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

  
  public Date getCreateTime() { return this.createTime; }

  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }

  
  public Date getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

  
  public String getDetailAddress() { return this.detailAddress; }

  
  public void setDetailAddress(String detailAddress) { this.detailAddress = detailAddress; }
}
