package com.ss.isc.data.resource.common.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Table;




























@Table(name = "cw_capture_detail")
public class CaptureDetail
  implements Serializable
{
  private static final long serialVersionUID = -20521021910352916L;
  private String id;
  private Date date;
  private String villageCode;
  
  public CaptureDetail() {}
  
  public Date getDate() { return this.date; }


  
  public void setDate(Date date) { this.date = date; }


  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public static long getSerialversionuid() { return -20521021910352916L; }


  
  public String getId() { return this.id; }


  
  public void setId(String id) { this.id = id; }



  
  public String toString() { return "CaptureDetail [id=" + this.id + ", date=" + this.date + ", villageCode=" + this.villageCode + "]"; }


  
  public CaptureDetail(String id, Date date, String villageCode) {
    this.id = id;
    this.date = date;
    this.villageCode = villageCode;
  }
}
