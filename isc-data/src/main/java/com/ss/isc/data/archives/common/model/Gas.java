package com.ss.isc.data.archives.common.model;

import java.io.Serializable;
import javax.persistence.Table;

























































@Table(name = "cw_base_gas_usage")
public class Gas
  implements Serializable
{
  private static final long serialVersionUID = -8832037444780717225L;
  private Integer id;
  private String villageCode;
  private String buildingNo;
  private String houseNo;
  private String houseHodeName;
  private String csmsNo;
  private String csmsAddress;
  private String year;
  private String month;
  private Integer usage;
  private Integer total;
  private String createTime;
  private String updateTime;
  
  public Integer getId() { return this.id; }

  
  public void setId(Integer id) { this.id = id; }

  
  public String getVillageCode() { return this.villageCode; }

  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }

  
  public String getBuildingNo() { return this.buildingNo; }

  
  public void setBuildingNo(String buildingNo) { this.buildingNo = buildingNo; }

  
  public String getHouseNo() { return this.houseNo; }

  
  public void setHouseNo(String houseNo) { this.houseNo = houseNo; }

  
  public String getHouseHodeName() { return this.houseHodeName; }

  
  public void setHouseHodeName(String houseHodeName) { this.houseHodeName = houseHodeName; }

  
  public String getCsmsNo() { return this.csmsNo; }

  
  public void setCsmsNo(String csmsNo) { this.csmsNo = csmsNo; }

  
  public String getCsmsAddress() { return this.csmsAddress; }

  
  public void setCsmsAddress(String csmsAddress) { this.csmsAddress = csmsAddress; }

  
  public String getYear() { return this.year; }

  
  public void setYear(String year) { this.year = year; }

  
  public String getMonth() { return this.month; }

  
  public void setMonth(String month) { this.month = month; }

  
  public Integer getUsage() { return this.usage; }

  
  public void setUsage(Integer usage) { this.usage = usage; }

  
  public Integer getTotal() { return this.total; }

  
  public void setTotal(Integer total) { this.total = total; }

  
  public String getCreateTime() { return this.createTime; }

  
  public void setCreateTime(String createTime) { this.createTime = createTime; }

  
  public String getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }
}
