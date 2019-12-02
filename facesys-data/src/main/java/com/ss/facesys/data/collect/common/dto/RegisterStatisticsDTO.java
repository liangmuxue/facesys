package com.ss.facesys.data.collect.common.dto;

import java.io.Serializable;


























public class RegisterStatisticsDTO
  implements Serializable
{
  private static final long serialVersionUID = -8110586035359972685L;
  private String villageCode;
  private Integer unRegisterNumbe;
  private Integer registerNumber;
  
  public RegisterStatisticsDTO() {}
  
  public RegisterStatisticsDTO(String villageCode, Integer unRegisterNumbe, Integer registerNumber) {
    this.villageCode = villageCode;
    this.unRegisterNumbe = unRegisterNumbe;
    this.registerNumber = registerNumber;
  }

  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public int getUnRegisterNumbe() { return this.unRegisterNumbe.intValue(); }


  
  public void setUnRegisterNumbe(Integer unRegisterNumbe) { this.unRegisterNumbe = unRegisterNumbe; }


  
  public Integer getRegisterNumber() { return this.registerNumber; }


  
  public void setRegisterNumber(Integer registerNumber) { this.registerNumber = registerNumber; }



  
  public String toString() { return "RegisterStatisticsDTO [villageCode=" + this.villageCode + ", unRegisterNumbe=" + this.unRegisterNumbe + ", registerNumber=" + this.registerNumber + "]"; }
}
