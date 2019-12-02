package com.ss.facesys.data.resource.common.web;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class HouseEditVO {
  @NotNull(message = "{HouseEditVO.id.empty}", groups = {com.ss.valide.APIEditGroup.class, com.ss.valide.APIDeltGroup.class})
  private Integer id;
  @NotBlank(message = "{HouseEditVO.villageCode.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  private String villageCode;
  @NotBlank(message = "{HouseEditVO.buildingNo.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  private String buildingNo;
  @NotBlank(message = "{HouseEditVO.unitNo.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  private String unitNo;
  @NotBlank(message = "{HouseEditVO.houseNo.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  private String houseNo;
  @NotBlank(message = "{HouseEditVO.floor.empty}", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  private String floor;
  private Integer ids[];
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


  
  public String getThirdId() { return this.thirdId; }


  
  public void setThirdId(String thirdId) { this.thirdId = thirdId; }



  @Override
  public String toString() { return "HouseEditVO [id=" + this.id + ", villageCode=" + this.villageCode + ", buildingNo=" + this.buildingNo + ", unitNo=" + this.unitNo + ", houseNo=" + this.houseNo + ", floor=" + this.floor + ", peopleRelation=" + this.peopleRelation + ", houseType=" + this.houseType + ", gisType=" + this.gisType + ", lon=" + this.lon + ", lat=" + this.lat + ", alt=" + this.alt + ", address=" + this.address + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "]"; }


  public Integer[] getIds() {
    return ids;
  }

  public void setIds(Integer[] ids) {
    this.ids = ids;
  }
}
