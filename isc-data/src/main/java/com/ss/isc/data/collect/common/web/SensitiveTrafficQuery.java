package com.ss.isc.data.collect.common.web;

import com.google.common.collect.Maps;
import com.ss.request.Pagination;

import java.util.Date;
import java.util.Map;

public class SensitiveTrafficQuery extends Pagination {
  private static final long serialVersionUID = -4687002782166117837L;
  private String villageCode;
  public Integer state;
  private Date beginTime;
  private Date endTime;
  private String villageName;
  private Integer amount;
  private String userId;
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


  
  public String getVillageName() { return this.villageName; }


  
  public void setVillageName(String villageName) { this.villageName = villageName; }


  
  public Integer getAmount() { return this.amount; }


  
  public void setAmount(Integer amount) { this.amount = amount; }


  
  public String getUserId() { return this.userId; }


  
  public void setUserId(String userId) { this.userId = userId; }


  
  public String getUserIds() { return this.userIds; }


  
  public void setUserIds(String userIds) { this.userIds = userIds; }

  
  public Map<String, String> getSqlMap() {
    if (this.sqlMap == null) {
      this.sqlMap = Maps.newHashMap();
    }
    return this.sqlMap;
  }

  
  public void setSqlMap(Map<String, String> sqlMap) { this.sqlMap = sqlMap; }
}
