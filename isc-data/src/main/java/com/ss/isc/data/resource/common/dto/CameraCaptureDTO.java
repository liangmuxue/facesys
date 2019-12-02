package com.ss.isc.data.resource.common.dto;

import java.io.Serializable;
















public class CameraCaptureDTO
  implements Serializable
{
  private static final long serialVersionUID = 4479881043960018527L;
  private String villageCode;
  private String deviceIds;
  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }

  
  public String getDeviceIds() { return this.deviceIds; }

  
  public void setDeviceIds(String deviceIds) { this.deviceIds = deviceIds; }


  
  public String toString() { return "CameraCaptureDTO [villageCode=" + this.villageCode + ", deviceIds=" + this.deviceIds + "]"; }
}
