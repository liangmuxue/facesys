package com.ss.isc.data.process.common.web;

import java.util.Arrays;





















































public class AddPersonRecogVO
{
  private String recordId;
  private String[] facedbIds;
  private Integer gender;
  private String img;
  private Integer imgType;
  private Integer ageB;
  private Integer ageE;
  private String nation;
  private Integer topN;
  private Float thresholdMin;
  private Float thresholdMax;
  
  public String getRecordId() { return this.recordId; }


  
  public void setRecordId(String recordId) { this.recordId = recordId; }


  
  public String[] getFacedbIds() { return this.facedbIds; }


  
  public void setFacedbIds(String[] facedbIds) { this.facedbIds = facedbIds; }


  
  public Integer getGender() { return this.gender; }


  
  public void setGender(Integer gender) { this.gender = gender; }


  
  public String getImg() { return this.img; }


  
  public void setImg(String img) { this.img = img; }


  
  public Integer getImgType() { return this.imgType; }


  
  public void setImgType(Integer imgType) { this.imgType = imgType; }


  
  public Integer getAgeB() { return this.ageB; }


  
  public void setAgeB(Integer ageB) { this.ageB = ageB; }


  
  public Integer getAgeE() { return this.ageE; }


  
  public void setAgeE(Integer ageE) { this.ageE = ageE; }


  
  public String getNation() { return this.nation; }


  
  public void setNation(String nation) { this.nation = nation; }


  
  public Integer getTopN() { return this.topN; }


  
  public void setTopN(Integer topN) { this.topN = topN; }


  
  public Float getThresholdMin() { return this.thresholdMin; }


  
  public void setThresholdMin(Float thresholdMin) { this.thresholdMin = thresholdMin; }


  
  public Float getThresholdMax() { return this.thresholdMax; }


  
  public void setThresholdMax(Float thresholdMax) { this.thresholdMax = thresholdMax; }



  
  public String toString() { return "AddPersonRecogVO [recordId=" + this.recordId + ", facedbIds=" + Arrays.toString(this.facedbIds) + ", gender=" + this.gender + ", img=" + this.img + ", imgType=" + this.imgType + ", ageB=" + this.ageB + ", ageE=" + this.ageE + ", nation=" + this.nation + ", topN=" + this.topN + ", thresholdMin=" + this.thresholdMin + ", thresholdMax=" + this.thresholdMax + "]"; }
}
