package com.ss.facesys.data.collect.common.dto;

import java.util.Date;






























































public class FrequencyRecordDTO
{
  private String id;
  private Integer state;
  private Integer amount;
  private String label;
  private String capturePath;
  private String panoramaPath;
  private String remark;
  private Date createTime;
  private Date updateTime;
  private Integer rowNum;
  private String villageName;
  private String cameraName;
  private String villageCode;
  private Date lastCaptureTime;
  private String facePitch;
  private String faceYaw;
  private String faceRoll;
  private String facex;
  private String facey;
  private String faceWidth;
  private String faceHeight;
  
  public String getId() { return this.id; }


  
  public void setId(String id) { this.id = id; }


  
  public Integer getState() { return this.state; }


  
  public void setState(Integer state) { this.state = state; }


  
  public Integer getAmount() { return this.amount; }


  
  public void setAmount(Integer amount) { this.amount = amount; }


  
  public String getLabel() { return this.label; }


  
  public void setLabel(String label) { this.label = label; }


  
  public String getCapturePath() { return this.capturePath; }


  
  public void setCapturePath(String capturePath) { this.capturePath = capturePath; }


  
  public String getPanoramaPath() { return this.panoramaPath; }


  
  public void setPanoramaPath(String panoramaPath) { this.panoramaPath = panoramaPath; }


  
  public String getRemark() { return this.remark; }


  
  public void setRemark(String remark) { this.remark = remark; }


  
  public Date getCreateTime() { return this.createTime; }


  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }


  
  public Date getUpdateTime() { return this.updateTime; }


  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }


  
  public Integer getRowNum() { return this.rowNum; }


  
  public void setRowNum(Integer rowNum) { this.rowNum = rowNum; }


  
  public String getVillageName() { return this.villageName; }


  
  public void setVillageName(String villageName) { this.villageName = villageName; }


  
  public String getCameraName() { return this.cameraName; }


  
  public void setCameraName(String cameraName) { this.cameraName = cameraName; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public Date getLastCaptureTime() { return this.lastCaptureTime; }


  
  public void setLastCaptureTime(Date lastCaptureTime) { this.lastCaptureTime = lastCaptureTime; }


  
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



  
  public String toString() { return "FrequencyRecordDTO [id=" + this.id + ", state=" + this.state + ", amount=" + this.amount + ", label=" + this.label + ", capturePath=" + this.capturePath + ", panoramaPath=" + this.panoramaPath + ", remark=" + this.remark + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + ", rowNum=" + this.rowNum + ", villageName=" + this.villageName + ", cameraName=" + this.cameraName + ", villageCode=" + this.villageCode + ", lastCaptureTime=" + this.lastCaptureTime + ", facePitch=" + this.facePitch + ", faceYaw=" + this.faceYaw + ", faceRoll=" + this.faceRoll + ", facex=" + this.facex + ", facey=" + this.facey + ", faceWidth=" + this.faceWidth + ", faceHeight=" + this.faceHeight + "]"; }
}
