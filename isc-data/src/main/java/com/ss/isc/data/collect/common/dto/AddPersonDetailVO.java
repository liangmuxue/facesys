package com.ss.isc.data.collect.common.dto;

import java.util.Date;
















public class AddPersonDetailVO
{
  private String captureId;
  private String captureUrlFull;
  private Date captureTime;
  private String cameraName;
  private String installAdd;
  private String panoramaUrlFull;
  
  public String getCaptureId() { return this.captureId; }


  
  public void setCaptureId(String captureId) { this.captureId = captureId; }


  
  public String getCaptureUrlFull() { return this.captureUrlFull; }


  
  public void setCaptureUrlFull(String captureUrlFull) { this.captureUrlFull = captureUrlFull; }


  
  public Date getCaptureTime() { return this.captureTime; }


  
  public void setCaptureTime(Date captureTime) { this.captureTime = captureTime; }


  
  public String getCameraName() { return this.cameraName; }


  
  public void setCameraName(String cameraName) { this.cameraName = cameraName; }


  
  public String getInstallAdd() { return this.installAdd; }


  
  public void setInstallAdd(String installAdd) { this.installAdd = installAdd; }


  
  public String getPanoramaUrlFull() { return this.panoramaUrlFull; }


  
  public void setPanoramaUrlFull(String panoramaUrlFull) { this.panoramaUrlFull = panoramaUrlFull; }



  
  public String toString() { return "AddPersonDetailVO [captureId=" + this.captureId + ", captureUrlFull=" + this.captureUrlFull + ", captureTime=" + this.captureTime + ", cameraName=" + this.cameraName + ", installAdd=" + this.installAdd + "]"; }
}
