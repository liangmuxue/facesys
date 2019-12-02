package com.ss.isc.data.collect.common.dto;

import java.io.Serializable;
import java.util.Date;

















































































































public class RegisterDTO
  implements Serializable
{
  private static final long serialVersionUID = 6833868729443571128L;
  private String id;
  private String refFaceId;
  private String faceDbId;
  private String faceDbName;
  private String engineFaceId;
  private String cardType;
  private String cardId;
  private String name;
  private Integer age;
  private String facePath;
  private String cardPath;
  private String facePathFull;
  private String cardPathFull;
  private Integer gender;
  private String genderName;
  private String nation;
  private String birthday;
  private String phoneNo;
  private Integer state;
  private Integer keyState;
  private Integer suspectState;
  private String remark;
  private Date createdTime;
  private Date updatedTime;
  private String address;
  private String nativePlace;
  private String validDate;
  private String faceExtField1;
  private String faceExtField2;
  private String faceExtField3;
  private Date deletedTime;
  private Float threshold;
  private Float recogScore;
  private String cardOrg;
  private int status;
  
  public String getId() { return this.id; }

  
  public void setId(String id) { this.id = id; }

  
  public String getRefFaceId() { return this.refFaceId; }

  
  public void setRefFaceId(String refFaceId) { this.refFaceId = refFaceId; }

  
  public String getFaceDbId() { return this.faceDbId; }

  
  public void setFaceDbId(String faceDbId) { this.faceDbId = faceDbId; }

  
  public String getFaceDbName() { return this.faceDbName; }

  
  public void setFaceDbName(String faceDbName) { this.faceDbName = faceDbName; }

  
  public String getEngineFaceId() { return this.engineFaceId; }

  
  public void setEngineFaceId(String engineFaceId) { this.engineFaceId = engineFaceId; }

  
  public String getCardType() { return this.cardType; }

  
  public void setCardType(String cardType) { this.cardType = cardType; }

  
  public String getCardId() { return this.cardId; }

  
  public void setCardId(String cardId) { this.cardId = cardId; }

  
  public String getName() { return this.name; }

  
  public void setName(String name) { this.name = name; }

  
  public Integer getAge() { return this.age; }

  
  public void setAge(Integer age) { this.age = age; }

  
  public String getFacePath() { return this.facePath; }

  
  public void setFacePath(String facePath) { this.facePath = facePath; }

  
  public String getCardPath() { return this.cardPath; }

  
  public void setCardPath(String cardPath) { this.cardPath = cardPath; }

  
  public String getFacePathFull() { return this.facePathFull; }

  
  public void setFacePathFull(String facePathFull) { this.facePathFull = facePathFull; }

  
  public String getCardPathFull() { return this.cardPathFull; }

  
  public void setCardPathFull(String cardPathFull) { this.cardPathFull = cardPathFull; }

  
  public Integer getGender() { return this.gender; }

  
  public void setGender(Integer gender) { this.gender = gender; }

  
  public String getGenderName() { return this.genderName; }

  
  public void setGenderName(String genderName) { this.genderName = genderName; }

  
  public String getNation() { return this.nation; }

  
  public void setNation(String nation) { this.nation = nation; }

  
  public String getBirthday() { return this.birthday; }

  
  public void setBirthday(String birthday) { this.birthday = birthday; }

  
  public String getPhoneNo() { return this.phoneNo; }

  
  public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

  
  public Integer getState() { return this.state; }

  
  public void setState(Integer state) { this.state = state; }

  
  public Integer getKeyState() { return this.keyState; }

  
  public void setKeyState(Integer keyState) { this.keyState = keyState; }

  
  public Integer getSuspectState() { return this.suspectState; }

  
  public void setSuspectState(Integer suspectState) { this.suspectState = suspectState; }

  
  public String getRemark() { return this.remark; }

  
  public void setRemark(String remark) { this.remark = remark; }

  
  public Date getCreatedTime() { return this.createdTime; }

  
  public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }

  
  public Date getUpdatedTime() { return this.updatedTime; }

  
  public void setUpdatedTime(Date updatedTime) { this.updatedTime = updatedTime; }

  
  public String getAddress() { return this.address; }

  
  public void setAddress(String address) { this.address = address; }

  
  public String getNativePlace() { return this.nativePlace; }

  
  public void setNativePlace(String nativePlace) { this.nativePlace = nativePlace; }

  
  public String getValidDate() { return this.validDate; }

  
  public void setValidDate(String validDate) { this.validDate = validDate; }

  
  public String getFaceExtField1() { return this.faceExtField1; }

  
  public void setFaceExtField1(String faceExtField1) { this.faceExtField1 = faceExtField1; }

  
  public String getFaceExtField2() { return this.faceExtField2; }

  
  public void setFaceExtField2(String faceExtField2) { this.faceExtField2 = faceExtField2; }

  
  public String getFaceExtField3() { return this.faceExtField3; }

  
  public void setFaceExtField3(String faceExtField3) { this.faceExtField3 = faceExtField3; }

  
  public Date getDeletedTime() { return this.deletedTime; }

  
  public void setDeletedTime(Date deletedTime) { this.deletedTime = deletedTime; }

  
  public Float getThreshold() { return this.threshold; }

  
  public void setThreshold(Float threshold) { this.threshold = threshold; }

  
  public Float getRecogScore() { return this.recogScore; }

  
  public void setRecogScore(Float recogScore) { this.recogScore = recogScore; }

  
  public String getCardOrg() { return this.cardOrg; }

  
  public void setCardOrg(String cardOrg) { this.cardOrg = cardOrg; }


  
  public int getStatus() { return this.status; }

  
  public void setStatus(int status) { this.status = status; }


  
  public String toString() { return "RegisterDTO [id=" + this.id + ", refFaceId=" + this.refFaceId + ", faceDbId=" + this.faceDbId + ", faceDbName=" + this.faceDbName + ", engineFaceId=" + this.engineFaceId + ", cardType=" + this.cardType + ", cardId=" + this.cardId + ", name=" + this.name + ", age=" + this.age + ", facePath=" + this.facePath + ", cardPath=" + this.cardPath + ", facePathFull=" + this.facePathFull + ", cardPathFull=" + this.cardPathFull + ", gender=" + this.gender + ", genderName=" + this.genderName + ", nation=" + this.nation + ", birthday=" + this.birthday + ", phoneNo=" + this.phoneNo + ", state=" + this.state + ", keyState=" + this.keyState + ", suspectState=" + this.suspectState + ", remark=" + this.remark + ", createdTime=" + this.createdTime + ", updatedTime=" + this.updatedTime + ", address=" + this.address + ", nativePlace=" + this.nativePlace + ", validDate=" + this.validDate + ", faceExtField1=" + this.faceExtField1 + ", faceExtField2=" + this.faceExtField2 + ", faceExtField3=" + this.faceExtField3 + ", deletedTime=" + this.deletedTime + ", threshold=" + this.threshold + ", recogScore=" + this.recogScore + ", cardOrg=" + this.cardOrg + "]"; }
}
