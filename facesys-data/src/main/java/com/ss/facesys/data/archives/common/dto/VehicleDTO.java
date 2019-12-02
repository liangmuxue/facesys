package com.ss.facesys.data.archives.common.dto;

import java.util.Date;









public class VehicleDTO
{
  private String plateNumber;
  private Integer inOutType;
  private Date inType;
  private Date outType;
  private String villageCode;
  
  public String getPlateNumber() { return this.plateNumber; }

  
  public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

  
  public Integer getInOutType() { return this.inOutType; }

  
  public void setInOutType(Integer inOutType) { this.inOutType = inOutType; }

  
  public Date getInType() { return this.inType; }

  
  public void setInType(Date inType) { this.inType = inType; }

  
  public Date getOutType() { return this.outType; }

  
  public void setOutType(Date outType) { this.outType = outType; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String toString() { return "VehicleDTO [plateNumber=" + this.plateNumber + ", inOutType=" + this.inOutType + ", inType=" + this.inType + ", outType=" + this.outType + ", villageCode=" + this.villageCode + "]"; }
}
