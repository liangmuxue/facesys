package com.ss.facesys.data.collect.common.dto;

import java.io.Serializable;



























































































public class CaptureDTO
  implements Serializable
{
  private static final long serialVersionUID = -6964457233611231759L;
  private String captureId;
  private String captureUrl;
  private String captureUrlFull;
  private Long captureTime;
  private String panoramaId;
  private String panoramaUrl;
  private String panoramaUrlFull;
  private String deviceId;
  private String deviceName;
  private String deviceAddress;
  private String lng;
  private String lat;
  private Integer age;
  private Integer gender;
  private Integer race;
  private Integer minorityState;
  private Float qualityScore;
  private Float faceTotalScore;
  private Float maskScore;
  private Integer maskState;
  private Float clarityScore;
  private Float glassesScore;
  private Integer glassesState;
  private Float mouthScore;
  private Float sunglassesScore;
  private Integer sunglassesState;
  private Integer nation;
  
  public String getCaptureId() { return this.captureId; }

  
  public void setCaptureId(String captureId) { this.captureId = captureId; }

  
  public String getCaptureUrl() { return this.captureUrl; }

  
  public void setCaptureUrl(String captureUrl) { this.captureUrl = captureUrl; }

  
  public String getCaptureUrlFull() { return this.captureUrlFull; }

  
  public void setCaptureUrlFull(String captureUrlFull) { this.captureUrlFull = captureUrlFull; }

  
  public Long getCaptureTime() { return this.captureTime; }

  
  public void setCaptureTime(Long captureTime) { this.captureTime = captureTime; }

  
  public String getPanoramaId() { return this.panoramaId; }

  
  public void setPanoramaId(String panoramaId) { this.panoramaId = panoramaId; }

  
  public String getPanoramaUrl() { return this.panoramaUrl; }

  
  public void setPanoramaUrl(String panoramaUrl) { this.panoramaUrl = panoramaUrl; }

  
  public String getPanoramaUrlFull() { return this.panoramaUrlFull; }

  
  public void setPanoramaUrlFull(String panoramaUrlFull) { this.panoramaUrlFull = panoramaUrlFull; }

  
  public String getDeviceId() { return this.deviceId; }

  
  public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

  
  public String getDeviceName() { return this.deviceName; }

  
  public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

  
  public String getDeviceAddress() { return this.deviceAddress; }

  
  public void setDeviceAddress(String deviceAddress) { this.deviceAddress = deviceAddress; }

  
  public String getLng() { return this.lng; }

  
  public void setLng(String lng) { this.lng = lng; }

  
  public String getLat() { return this.lat; }

  
  public void setLat(String lat) { this.lat = lat; }

  
  public Integer getAge() { return this.age; }

  
  public void setAge(Integer age) { this.age = age; }

  
  public Integer getGender() { return this.gender; }

  
  public void setGender(Integer gender) { this.gender = gender; }

  
  public Integer getRace() { return this.race; }

  
  public void setRace(Integer race) { this.race = race; }

  
  public Integer getMinorityState() { return this.minorityState; }

  
  public void setMinorityState(Integer minorityState) { this.minorityState = minorityState; }

  
  public Float getQualityScore() { return this.qualityScore; }

  
  public void setQualityScore(Float qualityScore) { this.qualityScore = qualityScore; }

  
  public Float getFaceTotalScore() { return this.faceTotalScore; }

  
  public void setFaceTotalScore(Float faceTotalScore) { this.faceTotalScore = faceTotalScore; }

  
  public Float getMaskScore() { return this.maskScore; }

  
  public void setMaskScore(Float maskScore) { this.maskScore = maskScore; }

  
  public Integer getMaskState() { return this.maskState; }

  
  public void setMaskState(Integer maskState) { this.maskState = maskState; }

  
  public Float getClarityScore() { return this.clarityScore; }

  
  public void setClarityScore(Float clarityScore) { this.clarityScore = clarityScore; }

  
  public Float getGlassesScore() { return this.glassesScore; }

  
  public void setGlassesScore(Float glassesScore) { this.glassesScore = glassesScore; }

  
  public Integer getGlassesState() { return this.glassesState; }

  
  public void setGlassesState(Integer glassesState) { this.glassesState = glassesState; }

  
  public Float getMouthScore() { return this.mouthScore; }

  
  public void setMouthScore(Float mouthScore) { this.mouthScore = mouthScore; }

  
  public Float getSunglassesScore() { return this.sunglassesScore; }

  
  public void setSunglassesScore(Float sunglassesScore) { this.sunglassesScore = sunglassesScore; }

  
  public Integer getSunglassesState() { return this.sunglassesState; }

  
  public void setSunglassesState(Integer sunglassesState) { this.sunglassesState = sunglassesState; }

  
  public Integer getNation() { return this.nation; }

  
  public void setNation(Integer nation) { this.nation = nation; }


  
  public String toString() { return "CaptureDTO [captureId=" + this.captureId + ", captureUrl=" + this.captureUrl + ", captureUrlFull=" + this.captureUrlFull + ", captureTime=" + this.captureTime + ", panoramaId=" + this.panoramaId + ", panoramaUrl=" + this.panoramaUrl + ", panoramaUrlFull=" + this.panoramaUrlFull + ", deviceId=" + this.deviceId + ", deviceName=" + this.deviceName + ", deviceAddress=" + this.deviceAddress + ", lng=" + this.lng + ", lat=" + this.lat + ", age=" + this.age + ", gender=" + this.gender + ", race=" + this.race + ", minorityState=" + this.minorityState + ", qualityScore=" + this.qualityScore + ", faceTotalScore=" + this.faceTotalScore + ", maskScore=" + this.maskScore + ", maskState=" + this.maskState + ", clarityScore=" + this.clarityScore + ", glassesScore=" + this.glassesScore + ", glassesState=" + this.glassesState + ", mouthScore=" + this.mouthScore + ", sunglassesScore=" + this.sunglassesScore + ", sunglassesState=" + this.sunglassesState + ", nation=" + this.nation + "]"; }
}
