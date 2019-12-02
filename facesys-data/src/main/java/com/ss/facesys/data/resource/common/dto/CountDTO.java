package com.ss.facesys.data.resource.common.dto;

import java.io.Serializable;
















public class CountDTO
  implements Serializable
{
  private static final long serialVersionUID = 6782511086699776181L;
  private String villageCode;
  private String villageName;
  private int num;
  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String getVillageName() { return this.villageName; }


  
  public void setVillageName(String villageName) { this.villageName = villageName; }


  
  public int getNum() { return this.num; }


  
  public void setNum(int num) { this.num = num; }



  
  public CountDTO() {}


  
  public CountDTO(String villageCode, String villageName, int num) {
    this.villageCode = villageCode;
    this.villageName = villageName;
    this.num = num;
  }


  
  public String toString() { return "CountDTO [villageCode=" + this.villageCode + ", villageName=" + this.villageName + ", num=" + this.num + "]"; }
}
