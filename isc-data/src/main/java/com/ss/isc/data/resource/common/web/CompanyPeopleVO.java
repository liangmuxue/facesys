package com.ss.isc.data.resource.common.web;

import com.ss.isc.data.baseinfo.common.web.BaseQueryEntity;
import java.util.Date;

public class CompanyPeopleVO extends BaseQueryEntity {
  private static final long serialVersionUID = -7904023575490933925L;
  private Integer id;
  private String villageCode;
  private String companyCode;
  private String peopleName;
  private String gender;
  private Integer genderCode;
  private Integer credentialType;
  private String credentialNo;
  private String domicileAddress;
  private String residenceAdress;
  private String phone;
  private Date entrydate;
  private String jobTitle;
  private String idCardPicUrl;
  private String remark;
  private String nation;
  private Date createTime;
  private Date updateTime;
  private String companyAdress;
  private String ids;

  
  public String getCompanyAdress() { return this.companyAdress; }


  
  public void setCompanyAdress(String companyAdress) { this.companyAdress = companyAdress; }


  
  public String getNation() { return this.nation; }


  
  public void setNation(String nation) { this.nation = nation; }


  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String getCompanyCode() { return this.companyCode; }


  
  public void setCompanyCode(String companyCode) { this.companyCode = companyCode; }


  
  public String getPeopleName() { return this.peopleName; }


  
  public void setPeopleName(String peopleName) { this.peopleName = peopleName; }


  
  public String getGender() { return this.gender; }


  
  public void setGender(String gender) { this.gender = gender; }


  
  public Integer getGenderCode() { return this.genderCode; }


  
  public void setGenderCode(Integer genderCode) { this.genderCode = genderCode; }


  
  public Integer getCredentialType() { return this.credentialType; }


  
  public void setCredentialType(Integer credentialType) { this.credentialType = credentialType; }


  
  public String getCredentialNo() { return this.credentialNo; }


  
  public void setCredentialNo(String credentialNo) { this.credentialNo = credentialNo; }


  
  public String getDomicileAddress() { return this.domicileAddress; }


  
  public void setDomicileAddress(String domicileAddress) { this.domicileAddress = domicileAddress; }


  
  public String getResidenceAdress() { return this.residenceAdress; }


  
  public void setResidenceAdress(String residenceAdress) { this.residenceAdress = residenceAdress; }


  
  public String getPhone() { return this.phone; }


  
  public void setPhone(String phone) { this.phone = phone; }


  
  public Date getEntrydate() { return this.entrydate; }


  
  public void setEntrydate(Date entrydate) { this.entrydate = entrydate; }


  
  public String getJobTitle() { return this.jobTitle; }


  
  public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }


  
  public String getIdCardPicUrl() { return this.idCardPicUrl; }


  
  public void setIdCardPicUrl(String idCardPicUrl) { this.idCardPicUrl = idCardPicUrl; }


  
  public String getRemark() { return this.remark; }


  
  public void setRemark(String remark) { this.remark = remark; }


  
  public Date getCreateTime() { return this.createTime; }


  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }


  
  public Date getUpdateTime() { return this.updateTime; }


  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

  public String getIds() {
    return ids;
  }

  public void setIds(String ids) {
    this.ids = ids;
  }
}
