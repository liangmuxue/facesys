package com.ss.facesys.data.process.common.dto;

import java.io.Serializable;




















public class WarningDTO
  implements Serializable
{
  private static final long serialVersionUID = 5619239052429653189L;
  private String villageCode;
  private Integer untreated;
  private Integer dealed;
  
  public String getVillageCode() { return this.villageCode; }



  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }



  
  public Integer getUntreated() { return this.untreated; }



  
  public void setUntreated(Integer untreated) { this.untreated = untreated; }



  
  public Integer getDealed() { return this.dealed; }



  
  public void setDealed(Integer dealed) { this.dealed = dealed; }




  
  public WarningDTO() {}



  
  public WarningDTO(String villageCode, Integer untreated, Integer dealed) {
    this.villageCode = villageCode;
    this.untreated = untreated;
    this.dealed = dealed;
  }



  
  public String toString() { return "WarningDTO [villageCode=" + this.villageCode + ", untreated=" + this.untreated + ", dealed=" + this.dealed + "]"; }
}
