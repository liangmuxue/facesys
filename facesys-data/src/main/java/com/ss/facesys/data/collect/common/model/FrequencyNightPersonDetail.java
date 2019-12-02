package com.ss.facesys.data.collect.common.model;

import javax.persistence.Transient;
import java.util.Date;

public class FrequencyNightPersonDetail {
  private String id;
  private String frequencyNightPersonId;
  private String detailId;
  private String taskId;
  private String createdTime;
  private String captureId;
  private String captureUrl;
  private String captureUrlFull;
  private Date captureTime;
  private String panoramaId;
  private String panoramaUrl;
  private String panoramaUrlFull;
  private String deviceId;
  private String lng;
  private String lat;
  private String deviceName;
  private String deviceType;
  @Transient
  private Integer amount;
  private String facePitch;
  private String faceYaw;
  private String faceRoll;
  private String facex;
  private String facey;
  private String faceWidth;
  private String faceHeight;
  
  public String getId() { return this.id; }

  
  public void setId(String id) { this.id = id; }

  
  public String getDetailId() { return this.detailId; }

  
  public void setDetailId(String detailId) { this.detailId = detailId; }

  
  public String getTaskId() { return this.taskId; }

  
  public void setTaskId(String taskId) { this.taskId = taskId; }

  
  public String getCreatedTime() { return this.createdTime; }

  
  public void setCreatedTime(String createdTime) { this.createdTime = createdTime; }

  
  public String getCaptureId() { return this.captureId; }

  
  public void setCaptureId(String captureId) { this.captureId = captureId; }

  
  public String getCaptureUrl() { return this.captureUrl; }

  
  public void setCaptureUrl(String captureUrl) { this.captureUrl = captureUrl; }

  
  public String getCaptureUrlFull() { return this.captureUrlFull; }

  
  public void setCaptureUrlFull(String captureUrlFull) { this.captureUrlFull = captureUrlFull; }

  
  public Date getCaptureTime() { return this.captureTime; }

  
  public void setCaptureTime(Date captureTime) { this.captureTime = captureTime; }

  
  public String getPanoramaId() { return this.panoramaId; }

  
  public void setPanoramaId(String panoramaId) { this.panoramaId = panoramaId; }

  
  public String getPanoramaUrl() { return this.panoramaUrl; }

  
  public void setPanoramaUrl(String panoramaUrl) { this.panoramaUrl = panoramaUrl; }

  
  public String getPanoramaUrlFull() { return this.panoramaUrlFull; }

  
  public void setPanoramaUrlFull(String panoramaUrlFull) { this.panoramaUrlFull = panoramaUrlFull; }

  
  public String getDeviceId() { return this.deviceId; }

  
  public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

  
  public String getLng() { return this.lng; }

  
  public void setLng(String lng) { this.lng = lng; }

  
  public String getLat() { return this.lat; }

  
  public void setLat(String lat) { this.lat = lat; }

  
  public String getDeviceName() { return this.deviceName; }

  
  public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

  
  public String getDeviceType() { return this.deviceType; }

  
  public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

  
  public Integer getAmount() { return this.amount; }

  
  public void setAmount(Integer amount) { this.amount = amount; }

  
  public String getFacePitch() { return this.facePitch; }

  
  public void setFacePitch(String facePitch) { this.facePitch = facePitch; }

  
  public String getFaceYaw() { return this.faceYaw; }

  
  public void setFaceYaw(String faceYaw) { this.faceYaw = faceYaw; }

  
  public String getFaceRoll() { return this.faceRoll; }

  
  public void setFaceRoll(String faceRoll) { this.faceRoll = faceRoll; }

  
  public String getFacex() { return this.facex; }

  
  public void setFacex(String facex) { this.facex = facex; }

  
  public String getFacey() { return this.facey; }

  
  public void setFacey(String facey) { this.facey = facey; }

  
  public String getFaceWidth() { return this.faceWidth; }

  
  public void setFaceWidth(String faceWidth) { this.faceWidth = faceWidth; }

  
  public String getFaceHeight() { return this.faceHeight; }

  
  public void setFaceHeight(String faceHeight) { this.faceHeight = faceHeight; }

  public String getFrequencyNightPersonId() {
    return frequencyNightPersonId;
  }

  public void setFrequencyNightPersonId(String frequencyNightPersonId) {
    this.frequencyNightPersonId = frequencyNightPersonId;
  }
}
