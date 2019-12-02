package com.ss.isc.data.resource.common.web;

import com.ss.request.Pagination;
import java.util.Date;
import javax.validation.constraints.NotNull;













































































public class HouseQueryVO extends Pagination {
  private static final long serialVersionUID = -7776914434825485331L;
  @NotNull(message = "{HouseQuery.id.empty}", groups = {com.ss.valide.APIGetsGroup.class})
  private Integer id;
  private String villageCode;
  private String buildingNo;
  private String unitNo;
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
  private String userIds;
  private String regionCode;
  private String villageCodes;
  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String getBuildingNo() { return this.buildingNo; }


  
  public void setBuildingNo(String buildingNo) { this.buildingNo = buildingNo; }


  
  public String getUnitNo() { return this.unitNo; }


  
  public void setUnitNo(String unitNo) { this.unitNo = unitNo; }


  
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


  
  public String getUserIds() { return this.userIds; }


  
  public void setUserIds(String userIds) { this.userIds = userIds; }


  
  public String getRegionCode() { return this.regionCode; }


  
  public void setRegionCode(String regionCode) { this.regionCode = regionCode; }


  
  public String getVillageCodes() { return this.villageCodes; }


  
  public void setVillageCodes(String villageCodes) { this.villageCodes = villageCodes; }



  
  public String toString() { return "HouseQueryVO [id=" + this.id + ", villageCode=" + this.villageCode + ", buildingNo=" + this.buildingNo + ", unitNo=" + this.unitNo + ", houseNo=" + this.houseNo + ", floor=" + this.floor + ", peopleRelation=" + this.peopleRelation + ", houseType=" + this.houseType + ", gisType=" + this.gisType + ", lon=" + this.lon + ", lat=" + this.lat + ", alt=" + this.alt + ", address=" + this.address + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "]"; }
}
