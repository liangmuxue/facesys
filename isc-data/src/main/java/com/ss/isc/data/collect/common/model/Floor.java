package com.ss.isc.data.collect.common.model;

import java.util.List;
import javax.persistence.Table;










@Table
public class Floor
{
  private String villagecode;
  private String buildingno;
  private Integer floorNum;
  private List<House> houses;
  
  public String getVillagecode() { return this.villagecode; }

  
  public void setVillagecode(String villagecode) { this.villagecode = villagecode; }

  
  public String getBuildingno() { return this.buildingno; }

  
  public void setBuildingno(String buildingno) { this.buildingno = buildingno; }

  
  public Integer getFloorNum() { return this.floorNum; }

  
  public void setFloorNum(Integer floorNum) { this.floorNum = floorNum; }

  
  public List<House> getHouses() { return this.houses; }

  
  public void setHouses(List<House> houses) { this.houses = houses; }


  
  public Floor() {}

  
  public Floor(String villagecode, String buildingno, Integer floorNum, List<House> houses) {
    this.villagecode = villagecode;
    this.buildingno = buildingno;
    this.floorNum = floorNum;
    this.houses = houses;
  }
}
