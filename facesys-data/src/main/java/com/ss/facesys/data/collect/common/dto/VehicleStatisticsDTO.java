package com.ss.facesys.data.collect.common.dto;

import java.io.Serializable;
import javax.persistence.Table;

























@Table
public class VehicleStatisticsDTO
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String villageCode;
  private Integer quantity;
  private Integer enterNumber;
  private Integer outNumber;
  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public Integer getQuantity() { return this.quantity; }


  
  public void setQuantity(Integer quantity) { this.quantity = quantity; }


  
  public VehicleStatisticsDTO() {}


  
  public Integer getEnterNumber() { return this.enterNumber; }


  
  public void setEnterNumber(Integer enterNumber) { this.enterNumber = enterNumber; }


  
  public Integer getOutNumber() { return this.outNumber; }


  
  public void setOutNumber(Integer outNumber) { this.outNumber = outNumber; }



  
  public VehicleStatisticsDTO(Integer enterNumber, Integer outNumber) {
    this.enterNumber = enterNumber;
    this.outNumber = outNumber;
  }


  
  public String toString() { return "VehicleStatisticsVO [villageCode=" + this.villageCode + ", quantity=" + this.quantity + ", enterNumber=" + this.enterNumber + ", outNumber=" + this.outNumber + "]"; }
}
