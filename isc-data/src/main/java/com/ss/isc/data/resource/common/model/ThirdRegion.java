package com.ss.isc.data.resource.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;




































@Table(name = "cw_third_region_info")
public class ThirdRegion
  implements Serializable
{
  private static final long serialVersionUID = 1006594815085045379L;
  private String id;
  private String regionCode;
  private String regionName;
  private String parentId;
  private String parentCodeStr;
  private int state;
  private String coordinates;
  private String systemNo;
  private int regionType;
  private String remark;
  private Date createTime;
  
  public String getId() { return this.id; }

  
  public void setId(String id) { this.id = id; }

  
  public String getRegionCode() { return this.regionCode; }

  
  public void setRegionCode(String regionCode) { this.regionCode = regionCode; }

  
  public String getRegionName() { return this.regionName; }

  
  public void setRegionName(String regionName) { this.regionName = regionName; }

  
  public String getParentId() { return this.parentId; }

  
  public void setParentId(String parentId) { this.parentId = parentId; }

  
  public String getParentCodeStr() { return this.parentCodeStr; }

  
  public void setParentCodeStr(String parentCodeStr) { this.parentCodeStr = parentCodeStr; }

  
  public int getState() { return this.state; }

  
  public void setState(int state) { this.state = state; }

  
  public String getCoordinates() { return this.coordinates; }

  
  public void setCoordinates(String coordinates) { this.coordinates = coordinates; }

  
  public String getSystemNo() { return this.systemNo; }

  
  public void setSystemNo(String systemNo) { this.systemNo = systemNo; }

  
  public int getRegionType() { return this.regionType; }

  
  public void setRegionType(int regionType) { this.regionType = regionType; }

  
  public String getRemark() { return this.remark; }

  
  public void setRemark(String remark) { this.remark = remark; }

  
  public Date getCreateTime() { return this.createTime; }

  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
