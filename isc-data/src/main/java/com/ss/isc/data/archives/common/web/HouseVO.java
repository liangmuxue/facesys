package com.ss.isc.data.archives.common.web;

public class HouseVO {
  private String villageName;
  private String buildingNo;
  private String unitNo;
  private String houseNo;
  private String houseId;
  private String buildingNoAndUnitNo;
  private String buildingNameUnitName;
  private String buildingName;
  private String unitName;
  private String villageCode;
  private String address;
  
  public String getVillageName() { return this.villageName; }

  
  public void setVillageName(String villageName) { this.villageName = villageName; }

  
  public String getBuildingNo() { return this.buildingNo; }

  
  public void setBuildingNo(String buildingNo) { this.buildingNo = buildingNo; }

  
  public String getHouseNo() { return this.houseNo; }

  
  public void setHouseNo(String houseNo) { this.houseNo = houseNo; }

  
  public String getHouseId() { return this.houseId; }

  
  public void setHouseId(String houseId) { this.houseId = houseId; }

  
  public String getUnitNo() { return this.unitNo; }

  
  public void setUnitNo(String unitNo) { this.unitNo = unitNo; }

  
  public String getBuildingNoAndUnitNo() { return this.buildingNoAndUnitNo; }

  
  public void setBuildingNoAndUnitNo(String buildingNoAndUnitNo) { this.buildingNoAndUnitNo = buildingNoAndUnitNo; }

  
  public String getBuildingNameUnitName() { return this.buildingNameUnitName; }

  
  public void setBuildingNameUnitName(String buildingNameUnitName) { this.buildingNameUnitName = buildingNameUnitName; }

  
  public String getBuildingName() { return this.buildingName; }

  
  public void setBuildingName(String buildingName) { this.buildingName = buildingName; }

  
  public String getUnitName() { return this.unitName; }

  
  public void setUnitName(String unitName) { this.unitName = unitName; }


  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String toString() { return "HouseVO [villageName=" + this.villageName + ", buildingNo=" + this.buildingNo + ", unitNo=" + this.unitNo + ", houseNo=" + this.houseNo + ", houseId=" + this.houseId + ", buildingNoAndUnitNo=" + this.buildingNoAndUnitNo + ", buildingNameUnitName=" + this.buildingNameUnitName + ", buildingName=" + this.buildingName + ", unitName=" + this.unitName + ", villageCode=" + this.villageCode + "]"; }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
