package com.ss.facesys.data.collect.common.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Table;

























@Table
public class Building
{
  private Integer id;
  private String villageCode;
  private String buildingno;
  private String buildingname;
  private Integer floornum;
  private Integer housenum;
  private Short gistype;
  private String description;
  private Double lon;
  private Double lat;
  private Double alt;
  private Date createtime;
  private Date updatetime;
  private String unitNo;
  private List<House> houses;
  
  public String getUnitNo() { return this.unitNo; }


  
  public void setUnitNo(String unitNo) { this.unitNo = unitNo; }




  
  public List<House> getHouses() { return this.houses; }


  
  public void setHouses(List<House> houses) { this.houses = houses; }


  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = (villageCode == null) ? null : villageCode.trim(); }


  
  public String getBuildingno() { return this.buildingno; }


  
  public void setBuildingno(String buildingno) { this.buildingno = (buildingno == null) ? null : buildingno.trim(); }


  
  public String getBuildingname() { return this.buildingname; }


  
  public void setBuildingname(String buildingname) { this.buildingname = (buildingname == null) ? null : buildingname.trim(); }


  
  public Integer getFloornum() { return this.floornum; }


  
  public void setFloornum(Integer floornum) { this.floornum = floornum; }


  
  public Integer getHousenum() { return this.housenum; }


  
  public void setHousenum(Integer housenum) { this.housenum = housenum; }


  
  public Short getGistype() { return this.gistype; }


  
  public void setGistype(Short gistype) { this.gistype = gistype; }


  
  public String getDescription() { return this.description; }


  
  public void setDescription(String description) { this.description = (description == null) ? null : description.trim(); }


  
  public Double getLon() { return this.lon; }


  
  public void setLon(Double lon) { this.lon = lon; }


  
  public Double getLat() { return this.lat; }


  
  public void setLat(Double lat) { this.lat = lat; }


  
  public Double getAlt() { return this.alt; }


  
  public void setAlt(Double alt) { this.alt = alt; }


  
  public Date getCreatetime() { return this.createtime; }


  
  public void setCreatetime(Date createtime) { this.createtime = createtime; }


  
  public Date getUpdatetime() { return this.updatetime; }


  
  public void setUpdatetime(Date updatetime) { this.updatetime = updatetime; }



  
  public Building() {}


  
  public Building(String villageCode, String buildingno, Integer floornum, List<House> houses) {
    this.villageCode = villageCode;
    this.buildingno = buildingno;
    this.floornum = floornum;
    this.houses = houses;
  }
}
