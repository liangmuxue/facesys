package com.ss.isc.data.collect.common.web;

import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;






















public class AddPersonDetailQuery
{
  @NotBlank(message = "{addPersonDetailQuery.addPersonId.empty}", groups = {com.ss.valide.APIGetsGroup.class})
  private String addPersonId;
  private Date captureTimeB;
  private Date captureTimeE;
  private Integer amount;
  
  public String getAddPersonId() { return this.addPersonId; }


  
  public void setAddPersonId(String addPersonId) { this.addPersonId = addPersonId; }


  
  public Date getCaptureTimeB() { return this.captureTimeB; }


  
  public void setCaptureTimeB(Date captureTimeB) { this.captureTimeB = captureTimeB; }


  
  public Date getCaptureTimeE() { return this.captureTimeE; }


  
  public void setCaptureTimeE(Date captureTimeE) { this.captureTimeE = captureTimeE; }


  
  public Integer getAmount() { return this.amount; }


  
  public void setAmount(Integer amount) { this.amount = amount; }
}
