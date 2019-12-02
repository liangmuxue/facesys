package com.ss.isc.data.collect.common.web;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;





































public class CaptureCompareVO
  implements Serializable
{
  private static final long serialVersionUID = -2798185300496114279L;
  @NotBlank(message = "抓拍地点不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIGetsGroup.class})
  private String deviceAddress;
  @NotNull(message = "抓拍时间不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIGetsGroup.class})
  private Long captureTime;
  @NotBlank(message = "图片信息不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIGetsGroup.class})
  private String captureUrlFull;
  private String villageCode;
  
  public String getDeviceAddress() { return this.deviceAddress; }

  
  public void setDeviceAddress(String deviceAddress) { this.deviceAddress = deviceAddress; }

  
  public Long getCaptureTime() { return this.captureTime; }

  
  public void setCaptureTime(Long captureTime) { this.captureTime = captureTime; }

  
  public String getCaptureUrlFull() { return this.captureUrlFull; }

  
  public void setCaptureUrlFull(String captureUrlFull) { this.captureUrlFull = captureUrlFull; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String toString() { return "CaptureCompareVO [deviceAddress=" + this.deviceAddress + ", captureTime=" + this.captureTime + ", captureUrlFull=" + this.captureUrlFull + "]"; }
}
