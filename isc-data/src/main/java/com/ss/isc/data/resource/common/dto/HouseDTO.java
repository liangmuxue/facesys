package com.ss.isc.data.resource.common.dto;

import java.io.Serializable;
import java.util.Date;

public class HouseDTO implements Serializable {
  private static final long serialVersionUID = -3502062886377789866L;
  private Integer id;
  private String villageCode;
  private String villageName;
  private String buildingNo;
  private String buildingName;
  private String unitNo;
  private String unitName;
  private String houseNo;
  private String floor;
  private Integer peopleRelation;
  private Integer houseType;
  private Integer gisType;
  private Double lon;
  private Double lat;
  private Double alt;
  private String address;
  private Date createTime;
  private Date updateTime;
  private String thirdId;
  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String getVillageName() { return this.villageName; }


  
  public void setVillageName(String villageName) { this.villageName = villageName; }


  
  public String getBuildingNo() { return this.buildingNo; }


  
  public void setBuildingNo(String buildingNo) { this.buildingNo = buildingNo; }


  
  public String getUnitNo() { return this.unitNo; }


  
  public void setUnitNo(String unitNo) { this.unitNo = unitNo; }


  
  public String getUnitName() { return this.unitName; }


  
  public void setUnitName(String unitName) { this.unitName = unitName; }


  
  public String getBuildingName() { return this.buildingName; }


  
  public void setBuildingName(String buildingName) { this.buildingName = buildingName; }


  
  public String getHouseNo() { return this.houseNo; }


  
  public void setHouseNo(String houseNo) { this.houseNo = houseNo; }


  
  public String getFloor() { return this.floor; }


  
  public void setFloor(String floor) { this.floor = floor; }


  
  public Integer getPeopleRelation() { return this.peopleRelation; }


  
  public void setPeopleRelation(Integer peopleRelation) { this.peopleRelation = peopleRelation; }


  
  public Integer getHouseType() { return this.houseType; }


  
  public void setHouseType(Integer houseType) { this.houseType = houseType; }


  
  public Integer getGisType() { return this.gisType; }


  
  public void setGisType(Integer gisType) { this.gisType = gisType; }


  
  public Double getLon() { return this.lon; }


  
  public void setLon(Double lon) { this.lon = lon; }


  
  public Double getLat() { return this.lat; }


  
  public void setLat(Double lat) { this.lat = lat; }


  
  public Double getAlt() { return this.alt; }


  
  public void setAlt(Double alt) { this.alt = alt; }


  
  public String getAddress() { return this.address; }


  
  public void setAddress(String address) { this.address = address; }


  
  public Date getCreateTime() { return this.createTime; }


  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }


  
  public Date getUpdateTime() { return this.updateTime; }


  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }


  
  public String getThirdId() { return this.thirdId; }


  
  public void setThirdId(String thirdId) { this.thirdId = thirdId; }



  
  public String toString() { return "HouseDTO [id=" + this.id + ", villageCode=" + this.villageCode + ", villageName=" + this.villageName + ", buildingNo=" + this.buildingNo + ", buildingName=" + this.buildingName + ", unitNo=" + this.unitNo + ", houseNo=" + this.houseNo + ", floor=" + this.floor + ", peopleRelation=" + this.peopleRelation + ", houseType=" + this.houseType + ", gisType=" + this.gisType + ", lon=" + this.lon + ", lat=" + this.lat + ", alt=" + this.alt + ", address=" + this.address + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "]"; }
}
