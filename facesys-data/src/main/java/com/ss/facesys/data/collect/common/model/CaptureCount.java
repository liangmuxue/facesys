package com.ss.facesys.data.collect.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;









@Table(name = "cw_capture_count")
public class CaptureCount
  implements Serializable
{
  private static final long serialVersionUID = -5064965687109414644L;
  private Integer id;
  private String villageCode;
  private Integer num;
  private Date date;
  private Date createTime;
  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }



  
  public Integer getNum() { return this.num; }


  
  public void setNum(Integer num) { this.num = num; }


  
  public Date getDate() { return this.date; }


  
  public void setDate(Date date) { this.date = date; }


  
  public Date getCreateTime() { return this.createTime; }


  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }


  
  public CaptureCount() {}


  
  public CaptureCount(String villageCode, Integer num, Date date) {
    this.villageCode = villageCode;
    this.num = num;
    this.date = date;
  }
}
