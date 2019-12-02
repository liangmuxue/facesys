package com.ss.isc.data.collect.common.web;

import java.io.Serializable;
import java.util.Arrays;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
















































public class RegisterVO
  implements Serializable
{
  private static final long serialVersionUID = 6131299273044611334L;
  private String[] facedbIds;
  private Integer gender;
  @NotBlank(message = "图片信息不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIGetsGroup.class})
  private String img;
  @NotNull(message = "图片类型不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIGetsGroup.class})
  @Range(min = 1L, max = 3L, groups = {com.ss.valide.APIGetsGroup.class}, message = "1:url 相对路径\t2:base64 Base64 数据,3:fullUrl 全路径")
  private Integer imgType;
  private Integer ageB;
  private Integer ageE;
  private String nation;
  private Integer topN;
  private Float thresholdMin;
  private Float thresholdMax;
  
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



  
  public String toString() { return "RegisterVO [facedbIds=" + Arrays.toString(this.facedbIds) + ", gender=" + this.gender + ", img=" + this.img + ", imgType=" + this.imgType + ", ageB=" + this.ageB + ", ageE=" + this.ageE + ", nation=" + this.nation + ", topN=" + this.topN + ", thresholdMin=" + this.thresholdMin + ", thresholdMax=" + this.thresholdMax + "]"; }
}
