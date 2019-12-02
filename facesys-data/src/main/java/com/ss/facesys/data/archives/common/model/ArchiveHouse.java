package com.ss.facesys.data.archives.common.model;

import java.io.Serializable;
import javax.persistence.Table;


@Table(name = "cw_base_house")
public class ArchiveHouse
  implements Serializable
{
  private static final long serialVersionUID = 198149452992363978L;
  private int id;
  private String villageCode;
  private String buildingNo;
  private String unitNo;
  private String houseNo;
  private String floor;
  private int peopleRelation;
  private int houseType;
  private int gisType;
  private Double lon;
  private Double lat;
  private Double alt;
  private String address;
  private String createTime;
  private String updateTime;
  private String villageName;
  private String buildingName;
  private String unitName;
  
  public int getId() { return this.id; }

  
  public void setId(int id) { this.id = id; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }

  
  public String getBuildingNo() { return this.buildingNo; }

  
  public void setBuildingNo(String buildingNo) { this.buildingNo = buildingNo; }

  
  public String getHouseNo() { return this.houseNo; }

  
  public void setHouseNo(String houseNo) { this.houseNo = houseNo; }

  
  public String getFloor() { return this.floor; }

  
  public void setFloor(String floor) { this.floor = floor; }

  
  public int getPeopleRelation() { return this.peopleRelation; }

  
  public void setPeopleRelation(int peopleRelation) { this.peopleRelation = peopleRelation; }

  
  public int getHouseType() { return this.houseType; }

  
  public void setHouseType(int houseType) { this.houseType = houseType; }

  
  public int getGisType() { return this.gisType; }

  
  public void setGisType(int gisType) { this.gisType = gisType; }

  
  public Double getLon() { return this.lon; }

  
  public void setLon(Double lon) { this.lon = lon; }

  
  public Double getLat() { return this.lat; }

  
  public void setLat(Double lat) { this.lat = lat; }

  
  public Double getAlt() { return this.alt; }

  
  public void setAlt(Double alt) { this.alt = alt; }

  
  public String getAddress() { return this.address; }

  
  public void setAddress(String address) { this.address = address; }

  
  public String getCreateTime() { return this.createTime; }

  
  public void setCreateTime(String createTime) { this.createTime = createTime; }

  
  public String getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }

  
  public String getVillageName() { return this.villageName; }

  
  public void setVillageName(String villageName) { this.villageName = villageName; }

  
  public String getUnitNo() { return this.unitNo; }

  
  public void setUnitNo(String unitNo) { this.unitNo = unitNo; }

  
  public String getBuildingName() { return this.buildingName; }

  
  public void setBuildingName(String buildingName) { this.buildingName = buildingName; }

  
  public String getUnitName() { return this.unitName; }

  
  public void setUnitName(String unitName) { this.unitName = unitName; }
}
