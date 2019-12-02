package com.ss.facesys.data.collect.common.dto;

import java.io.Serializable;



























public class CaptureCompareDTO
  implements Serializable
{
  private static final long serialVersionUID = -7284298213057076759L;
  private String deviceAddress;
  private Long captureTime;
  private String captureUrlFull;
  private String cardId;
  private String name;
  private String cardPathFull;
  private String faceDbName;
  private Float recogScore;
  
  public String getDeviceAddress() { return this.deviceAddress; }

  
  public void setDeviceAddress(String deviceAddress) { this.deviceAddress = deviceAddress; }

  
  public Long getCaptureTime() { return this.captureTime; }

  
  public void setCaptureTime(Long captureTime) { this.captureTime = captureTime; }

  
  public String getCaptureUrlFull() { return this.captureUrlFull; }

  
  public void setCaptureUrlFull(String captureUrlFull) { this.captureUrlFull = captureUrlFull; }

  
  public String getCardId() { return this.cardId; }

  
  public void setCardId(String cardId) { this.cardId = cardId; }

  
  public String getName() { return this.name; }

  
  public void setName(String name) { this.name = name; }

  
  public String getCardPathFull() { return this.cardPathFull; }

  
  public void setCardPathFull(String cardPathFull) { this.cardPathFull = cardPathFull; }

  
  public String getFaceDbName() { return this.faceDbName; }

  
  public void setFaceDbName(String faceDbName) { this.faceDbName = faceDbName; }

  
  public Float getRecogScore() { return this.recogScore; }

  
  public void setRecogScore(Float recogScore) { this.recogScore = recogScore; }


  
  public String toString() { return "CaptureCompareDTO [deviceAddress=" + this.deviceAddress + ", captureTime=" + this.captureTime + ", captureUrlFull=" + this.captureUrlFull + ", cardId=" + this.cardId + ", name=" + this.name + ", cardPathFull=" + this.cardPathFull + ", faceDbName=" + this.faceDbName + ", recogScore=" + this.recogScore + "]"; }
}
