package com.ss.facesys.data.collect.common.web;

import com.ss.facesys.data.baseinfo.common.web.BaseQueryEntity;

import java.util.Date;

public class CompanyListVO extends BaseQueryEntity {
  private static final long serialVersionUID = -6583345108062073908L;
  private Integer id;
  private String companyCode;
  private String villageCode;
  private String companyName;
  private Integer companyType;
  private Integer companySize;
  private Integer economicType;
  private Integer importantFlag;
  private String companyAdress;
  private String companyRegisterAddress;
  private String companyTel;
  private String organCode;
  private String leaderName;
  private Integer credentialType;
  private String credentialNo;
  private String leaderTel;
  private String companyPic;
  private Integer gisType;
  private Double lon;
  private Double lat;
  private Double alt;
  private String description;
  private Date createTime;
  private Date updateTime;
  private String villageCodes;

  public Integer getId() { return this.id; }

  
  public void setId(Integer id) { this.id = id; }

  
  public String getCompanyCode() { return this.companyCode; }

  
  public void setCompanyCode(String companyCode) { this.companyCode = companyCode; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }

  
  public String getCompanyName() { return this.companyName; }

  
  public void setCompanyName(String companyName) { this.companyName = companyName; }

  
  public Integer getCompanyType() { return this.companyType; }

  
  public void setCompanyType(Integer companyType) { this.companyType = companyType; }

  
  public Integer getCompanySize() { return this.companySize; }

  
  public void setCompanySize(Integer companySize) { this.companySize = companySize; }

  
  public Integer getEconomicType() { return this.economicType; }

  
  public void setEconomicType(Integer economicType) { this.economicType = economicType; }

  
  public Integer getImportantFlag() { return this.importantFlag; }

  
  public void setImportantFlag(Integer importantFlag) { this.importantFlag = importantFlag; }

  
  public String getCompanyAdress() { return this.companyAdress; }

  
  public void setCompanyAdress(String companyAdress) { this.companyAdress = companyAdress; }

  
  public String getCompanyRegisterAddress() { return this.companyRegisterAddress; }

  
  public void setCompanyRegisterAddress(String companyRegisterAddress) { this.companyRegisterAddress = companyRegisterAddress; }

  
  public String getCompanyTel() { return this.companyTel; }

  
  public void setCompanyTel(String companyTel) { this.companyTel = companyTel; }

  
  public String getOrganCode() { return this.organCode; }

  
  public void setOrganCode(String organCode) { this.organCode = organCode; }

  
  public String getLeaderName() { return this.leaderName; }

  
  public void setLeaderName(String leaderName) { this.leaderName = leaderName; }

  
  public Integer getCredentialType() { return this.credentialType; }

  
  public void setCredentialType(Integer credentialType) { this.credentialType = credentialType; }

  
  public String getCredentialNo() { return this.credentialNo; }

  
  public void setCredentialNo(String credentialNo) { this.credentialNo = credentialNo; }

  
  public String getLeaderTel() { return this.leaderTel; }

  
  public void setLeaderTel(String leaderTel) { this.leaderTel = leaderTel; }

  
  public String getCompanyPic() { return this.companyPic; }

  
  public void setCompanyPic(String companyPic) { this.companyPic = companyPic; }

  
  public Integer getGisType() { return this.gisType; }

  
  public void setGisType(Integer gisType) { this.gisType = gisType; }

  
  public Double getLon() { return this.lon; }

  
  public void setLon(Double lon) { this.lon = lon; }

  
  public Double getLat() { return this.lat; }

  
  public void setLat(Double lat) { this.lat = lat; }

  
  public Double getAlt() { return this.alt; }

  
  public void setAlt(Double alt) { this.alt = alt; }

  
  public String getDescription() { return this.description; }

  
  public void setDescription(String description) { this.description = description; }

  
  public Date getCreateTime() { return this.createTime; }

  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }

  
  public Date getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

  public String getVillageCodes() {
    return villageCodes;
  }

  public void setVillageCodes(String villageCodes) {
    this.villageCodes = villageCodes;
  }
}
