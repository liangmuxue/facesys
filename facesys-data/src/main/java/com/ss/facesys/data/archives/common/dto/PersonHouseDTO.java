package com.ss.facesys.data.archives.common.dto;












public class PersonHouseDTO
{
  private String credentialNo;
  private String villageCode;
  private String userIds;
  private String sqlString;
  
  public String getCredentialNo() { return this.credentialNo; }

  
  public void setCredentialNo(String credentialNo) { this.credentialNo = credentialNo; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String getUserIds() { return this.userIds; }

  
  public void setUserIds(String userIds) { this.userIds = userIds; }

  
  public String getSqlString() { return this.sqlString; }

  
  public void setSqlString(String sqlString) { this.sqlString = sqlString; }


  
  public String toString() { return "PersonHouseDTO [credentialNo=" + this.credentialNo + ", villageCode=" + this.villageCode + ", userIds=" + this.userIds + ", sqlString=" + this.sqlString + "]"; }
}
