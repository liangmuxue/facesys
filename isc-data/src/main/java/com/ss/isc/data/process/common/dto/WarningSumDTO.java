package com.ss.isc.data.process.common.dto;

import java.io.Serializable;












public class WarningSumDTO
  implements Serializable
{
  private static final long serialVersionUID = -8110586035359972685L;
  private Integer num;
  private String villageCode;
  private String villageName;
  
  public Integer getNum() { return this.num; }


  
  public void setNum(Integer num) { this.num = num; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String getVillageName() { return this.villageName; }


  
  public void setVillageName(String villageName) { this.villageName = villageName; }



  
  public WarningSumDTO() {}


  
  public WarningSumDTO(Integer num, String villageCode, String villageName) {
    this.num = num;
    this.villageCode = villageCode;
    this.villageName = villageName;
  }


  
  public String toString() { return "CaptureSumDTO [num=" + this.num + ", villageCode=" + this.villageCode + ", villageName=" + this.villageName + "]"; }


  
  public WarningSumDTO(Integer num, String villageName) {
    this.num = num;
    this.villageName = villageName;
  }

  
  public WarningSumDTO(String villageCode, Integer num) {
    this.villageCode = villageCode;
    this.num = num;
  }
}
