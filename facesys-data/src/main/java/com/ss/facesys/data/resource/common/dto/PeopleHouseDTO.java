package com.ss.facesys.data.resource.common.dto;

import java.io.Serializable;
import java.util.Date;



















































public class PeopleHouseDTO
  implements Serializable
{
  private static final long serialVersionUID = 3219919564555255139L;
  private Integer id;
  private String villageCode;
  private String credentialType;
  private String credentialNo;
  private String peopleName;
  private String buildingNo;
  private String unitNo;
  private String houseNo;
  private Integer peopleRelation;
  private Date createTime;
  private Date updateTime;
  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String getCredentialType() { return this.credentialType; }


  
  public void setCredentialType(String credentialType) { this.credentialType = credentialType; }


  
  public String getCredentialNo() { return this.credentialNo; }


  
  public void setCredentialNo(String credentialNo) { this.credentialNo = credentialNo; }


  
  public String getPeopleName() { return this.peopleName; }


  
  public void setPeopleName(String peopleName) { this.peopleName = peopleName; }


  
  public String getBuildingNo() { return this.buildingNo; }


  
  public void setBuildingNo(String buildingNo) { this.buildingNo = buildingNo; }


  
  public String getUnitNo() { return this.unitNo; }


  
  public void setUnitNo(String unitNo) { this.unitNo = unitNo; }


  
  public String getHouseNo() { return this.houseNo; }


  
  public void setHouseNo(String houseNo) { this.houseNo = houseNo; }


  
  public Integer getPeopleRelation() { return this.peopleRelation; }


  
  public void setPeopleRelation(Integer peopleRelation) { this.peopleRelation = peopleRelation; }


  
  public Date getCreateTime() { return this.createTime; }


  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }


  
  public Date getUpdateTime() { return this.updateTime; }


  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }



  
  public String toString() { return "PeopleHouseDTO [id=" + this.id + ", villageCode=" + this.villageCode + ", credentialType=" + this.credentialType + ", credentialNo=" + this.credentialNo + ", peopleName=" + this.peopleName + ", buildingNo=" + this.buildingNo + ", unitNo=" + this.unitNo + ", houseNo=" + this.houseNo + ", peopleRelation=" + this.peopleRelation + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "]"; }
}
