package com.ss.facesys.data.trail.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

































public class WifiCollectDataVO
{
  @NotBlank(message = "MAC地址不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  private String collectMac;
  @NotNull(message = "查询采集开始时间不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date collectTimeB;
  @NotNull(message = "查询采集结束时间不能为空", groups = {com.ss.valide.APIAddGroup.class, com.ss.valide.APIEditGroup.class})
  @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date collectTimeE;
  private String villageCode;
  private String userIds;
  
  public String getCollectMac() { return this.collectMac; }


  
  public void setCollectMac(String collectMac) { this.collectMac = collectMac; }


  
  public Date getCollectTimeB() { return this.collectTimeB; }


  
  public void setCollectTimeB(Date collectTimeB) { this.collectTimeB = collectTimeB; }


  
  public Date getCollectTimeE() { return this.collectTimeE; }


  
  public void setCollectTimeE(Date collectTimeE) { this.collectTimeE = collectTimeE; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public String getUserIds() { return this.userIds; }


  
  public void setUserIds(String userIds) { this.userIds = userIds; }



  
  public String toString() { return "WifiCollectDataVO [collectMac=" + this.collectMac + ", collectTimeB=" + this.collectTimeB + ", collectTimeE=" + this.collectTimeE + "]"; }
}
