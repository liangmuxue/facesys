package com.ss.facesys.data.trail.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;






















































public class FaceTrackVO
  implements Serializable
{
  private static final long serialVersionUID = 1067627769884226088L;
  @NotNull(message = "阈值不能为空", groups = {com.ss.valide.APIAddGroup.class})
  private Float threshold;
  @NotNull(message = "图片类型不能为空", groups = {com.ss.valide.APIAddGroup.class})
  private Integer imgType;
  @NotBlank(message = "图片信息不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  private String img;
  @NotNull(message = "查询开始时间不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date captureTimeB;
  @NotNull(message = "查询结束时间不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date captureTimeE;
  private String[] deviceIds;
  private Float thresholdMin;
  private Float thresholdMax;
  private String villageCode;
  private String userIds;
  
  public Float getThreshold() { return this.threshold; }


  
  public void setThreshold(Float threshold) { this.threshold = threshold; }


  
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


  
  public String getUserIds() { return this.userIds; }


  
  public void setUserIds(String userIds) { this.userIds = userIds; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String toString() {
    return "FaceTrackVO [threshold=" + this.threshold + ", imgType=" + this.imgType + ", img=" + this.img + ", captureTimeB=" + this.captureTimeB + ", captureTimeE=" + this.captureTimeE + ", deviceIds=" + 
      Arrays.toString(this.deviceIds) + ", thresholdMin=" + this.thresholdMin + ", thresholdMax=" + this.thresholdMax + "]";
  }
}
