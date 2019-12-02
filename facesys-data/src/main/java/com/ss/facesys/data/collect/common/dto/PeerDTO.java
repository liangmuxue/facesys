package com.ss.facesys.data.collect.common.dto;

import org.hibernate.validator.constraints.NotBlank;

public class PeerDTO {
  private String name;
  private Integer peersNumber;
  private String address;
  @NotBlank(message = "{peer.cardId.empty}", groups = {com.ss.valide.APIListGroup.class})
  private String cardId;
  private String cardPathFull;
  @NotBlank(message = "{peer.addPersonId.empty}", groups = {com.ss.valide.APIGetsGroup.class, com.ss.valide.APIListGroup.class})
  private String addPersonId;
  
  public String getName() { return this.name; }


  
  public void setName(String name) { this.name = name; }


  
  public Integer getPeersNumber() { return this.peersNumber; }


  
  public void setPeersNumber(Integer peersNumber) { this.peersNumber = peersNumber; }


  
  public String getAddress() { return this.address; }


  
  public void setAddress(String address) { this.address = address; }


  
  public String getCardId() { return this.cardId; }


  
  public void setCardId(String cardId) { this.cardId = cardId; }


  
  public String getCardPathFull() { return this.cardPathFull; }


  
  public void setCardPathFull(String cardPathFull) { this.cardPathFull = cardPathFull; }


  
  public String getAddPersonId() { return this.addPersonId; }


  
  public void setAddPersonId(String addPersonId) { this.addPersonId = addPersonId; }



  
  public String toString() { return "PeerDTO [name=" + this.name + ", peersNumber=" + this.peersNumber + ", address=" + this.address + ", cardId=" + this.cardId + ", cardPathFull=" + this.cardPathFull + ", addPersonId=" + this.addPersonId + "]"; }
}
