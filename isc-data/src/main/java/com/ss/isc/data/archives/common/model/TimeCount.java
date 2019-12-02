package com.ss.isc.data.archives.common.model;

import java.io.Serializable;













public class TimeCount
  implements Serializable
{
  private static final long serialVersionUID = 6926158504605378402L;
  private long time;
  private Integer count;
  
  public long getTime() { return this.time; }


  
  public void setTime(long time) { this.time = time; }


  
  public Integer getCount() { return this.count; }


  
  public void setCount(Integer count) { this.count = count; }



  
  public TimeCount() {}


  
  public TimeCount(long time, Integer count) {
    this.time = time;
    this.count = count;
  }
}
