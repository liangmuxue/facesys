package com.ss.isc.data.baseinfo.common.web;

import org.hibernate.validator.constraints.NotBlank;
















public class TemplateVO
{
  private String userIds;
  @NotBlank(message = "{templateVO.templateType.empty}", groups = {com.ss.valide.APIGetsGroup.class})
  private String type;
  
  public String getUserIds() { return this.userIds; }


  
  public void setUserIds(String userIds) { this.userIds = userIds; }


  
  public String getType() { return this.type; }


  
  public void setType(String type) { this.type = type; }



  
  public String toString() { return "TemplateVO [userIds=" + this.userIds + ", type=" + this.type + "]"; }
}
