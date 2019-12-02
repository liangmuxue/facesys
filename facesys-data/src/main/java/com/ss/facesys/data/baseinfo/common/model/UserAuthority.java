package com.ss.facesys.data.baseinfo.common.model;

import java.io.Serializable;













public class UserAuthority
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String name;
  private String userId;
  private String loginName;
  private String orgId;
  
  public String getName() { return this.name; }


  
  public void setName(String name) { this.name = name; }


  
  public String getUserId() { return this.userId; }


  
  public void setUserId(String userId) { this.userId = userId; }


  
  public String getLoginName() { return this.loginName; }


  
  public void setLoginName(String loginName) { this.loginName = loginName; }


  
  public String getOrgId() { return this.orgId; }


  
  public void setOrgId(String orgId) { this.orgId = orgId; }
}
