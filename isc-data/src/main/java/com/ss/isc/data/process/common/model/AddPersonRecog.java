package com.ss.isc.data.process.common.model;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;





































@Table(name = "cw_add_person_recog")
public class AddPersonRecog
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  private Integer id;
  private String recordId;
  private String cardId;
  private String cardPathFull;
  private Float recogScore;
  private String remark;
  @Transient
  private int status;
  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  public String getRecordId() { return this.recordId; }


  
  public void setRecordId(String recordId) { this.recordId = recordId; }


  
  public String getCardId() { return this.cardId; }


  
  public void setCardId(String cardId) { this.cardId = cardId; }


  
  public String getCardPathFull() { return this.cardPathFull; }


  
  public void setCardPathFull(String cardPathFull) { this.cardPathFull = cardPathFull; }


  
  public Float getRecogScore() { return this.recogScore; }


  
  public void setRecogScore(Float recogScore) { this.recogScore = recogScore; }


  
  public String getRemark() { return this.remark; }


  
  public void setRemark(String remark) { this.remark = remark; }


  
  public int getStatus() { return this.status; }


  
  public void setStatus(int status) { this.status = status; }
}
