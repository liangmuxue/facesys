package com.ss.facesys.data.resource.common.web;

import java.io.Serializable;









































public class PeopleImageVO
  implements Serializable
{
  private static final long serialVersionUID = -4617889963847299817L;
  private String name;
  private Integer model;
  private String remark;
  private String type;
  private String facedbExtField1;
  private String facedbExtField2;
  private String facedbExtField;
  
  public String getName() { return this.name; }


  
  public void setName(String name) { this.name = name; }


  
  public Integer getModel() { return this.model; }


  
  public void setModel(Integer model) { this.model = model; }


  
  public String getRemark() { return this.remark; }


  
  public void setRemark(String remark) { this.remark = remark; }


  
  public String getType() { return this.type; }


  
  public void setType(String type) { this.type = type; }


  
  public String getFacedbExtField1() { return this.facedbExtField1; }


  
  public void setFacedbExtField1(String facedbExtField1) { this.facedbExtField1 = facedbExtField1; }


  
  public String getFacedbExtField2() { return this.facedbExtField2; }


  
  public void setFacedbExtField2(String facedbExtField2) { this.facedbExtField2 = facedbExtField2; }


  
  public String getFacedbExtField() { return this.facedbExtField; }


  
  public void setFacedbExtField(String facedbExtField) { this.facedbExtField = facedbExtField; }


  
  public static long getSerialversionuid() { return -4617889963847299817L; }



  
  public String toString() { return "PeopleImageVO [name=" + this.name + ", model=" + this.model + ", remark=" + this.remark + ", type=" + this.type + ", facedbExtField1=" + this.facedbExtField1 + ", facedbExtField2=" + this.facedbExtField2 + ", facedbExtField=" + this.facedbExtField + "]"; }
}
