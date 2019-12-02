package com.ss.isc.data.archives.common.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
















































public class PersonCaptureVO
  implements Serializable
{
  private static final long serialVersionUID = 9134615178743910043L;
  private Integer imgType;
  private String img;
  private Date captureTimeB;
  private Date captureTimeE;
  private String[] deviceIds;
  private Float thresholdMin;
  private Float thresholdMax;
  private String villageCode;
  private Integer requestType;
  private String userIds;
  
  public Integer getImgType() { return this.imgType; }

  
  public void setImgType(Integer imgType) { this.imgType = imgType; }

  
  public String getImg() { return this.img; }

  
  public void setImg(String img) { this.img = img; }

  
  public Date getCaptureTimeB() { return this.captureTimeB; }

  
  public void setCaptureTimeB(Date captureTimeB) { this.captureTimeB = captureTimeB; }

  
  public Date getCaptureTimeE() { return this.captureTimeE; }

  
  public void setCaptureTimeE(Date captureTimeE) { this.captureTimeE = captureTimeE; }

  
  public String[] getDeviceIds() { return this.deviceIds; }

  
  public void setDeviceIds(String[] deviceIds) { this.deviceIds = deviceIds; }

  
  public Float getThresholdMin() { return this.thresholdMin; }

  
  public void setThresholdMin(Float thresholdMin) { this.thresholdMin = thresholdMin; }

  
  public Float getThresholdMax() { return this.thresholdMax; }

  
  public void setThresholdMax(Float thresholdMax) { this.thresholdMax = thresholdMax; }

  
  public static long getSerialversionuid() { return 9134615178743910043L; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }

  
  public Integer getRequestType() { return this.requestType; }

  
  public void setRequestType(Integer requestType) { this.requestType = requestType; }

  
  public String getUserIds() { return this.userIds; }

  
  public void setUserIds(String userIds) { this.userIds = userIds; }

  
  public String toString() {
    return "PersonCaptureVO [imgType=" + this.imgType + ", img=" + this.img + ", captureTimeB=" + this.captureTimeB + ", captureTimeE=" + this.captureTimeE + ", deviceIds=" + 
      Arrays.toString(this.deviceIds) + ", thresholdMin=" + this.thresholdMin + ", thresholdMax=" + this.thresholdMax + ", villageCode=" + this.villageCode + ", requestType=" + this.requestType + ", userIds=" + this.userIds + "]";
  }
}
