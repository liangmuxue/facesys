package com.ss.isc.data.collect.common.web;

import com.ss.request.Pagination;
import com.google.common.collect.Maps;
import java.util.Date;
import java.util.Map;




















































public class SpecialPersonQuery
  extends Pagination
{
  private static final long serialVersionUID = 2794758190182705256L;
  private String villageCode;
  public Integer state;
  private Date beginTime;
  private Date endTime;
  private Integer specialType;
  private String peopleName;
  private String credentialNo;
  private String residenceAddress;
  private Integer days;
  private String userIds;
  private Map<String, String> sqlMap;
  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public Integer getState() { return this.state; }


  
  public void setState(Integer state) { this.state = state; }


  
  public Date getBeginTime() { return this.beginTime; }


  
  public void setBeginTime(Date beginTime) { this.beginTime = beginTime; }


  
  public Date getEndTime() { return this.endTime; }


  
  public void setEndTime(Date endTime) { this.endTime = endTime; }


  
  public Integer getSpecialType() { return this.specialType; }


  
  public void setSpecialType(Integer specialType) { this.specialType = specialType; }


  
  public String getPeopleName() { return this.peopleName; }


  
  public void setPeopleName(String peopleName) { this.peopleName = peopleName; }


  
  public String getCredentialNo() { return this.credentialNo; }


  
  public void setCredentialNo(String credentialNo) { this.credentialNo = credentialNo; }


  
  public String getResidenceAddress() { return this.residenceAddress; }


  
  public void setResidenceAddress(String residenceAddress) { this.residenceAddress = residenceAddress; }


  
  public Integer getDays() { return this.days; }


  
  public void setDays(Integer days) { this.days = days; }


  
  public String getUserIds() { return this.userIds; }


  
  public void setUserIds(String userIds) { this.userIds = userIds; }

  
  public Map<String, String> getSqlMap() {
    if (this.sqlMap == null) {
      this.sqlMap = Maps.newHashMap();
    }
    return this.sqlMap;
  }

  
  public void setSqlMap(Map<String, String> sqlMap) { this.sqlMap = sqlMap; }



  
  public String toString() { return "SpecialPersonQuery [villageCode=" + this.villageCode + ", state=" + this.state + ", beginTime=" + this.beginTime + ", endTime=" + this.endTime + ", specialType=" + this.specialType + ", peopleName=" + this.peopleName + ", credentialNo=" + this.credentialNo + ", residenceAddress=" + this.residenceAddress + "]"; }
}
