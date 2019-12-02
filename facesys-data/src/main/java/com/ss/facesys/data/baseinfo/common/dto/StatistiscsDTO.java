package com.ss.facesys.data.baseinfo.common.dto;

import java.io.Serializable;





















public class StatistiscsDTO
  implements Serializable
{
  private static final long serialVersionUID = 3307844573647524857L;
  private String type;
  private String label;
  private Integer value;
  private Integer number;
  
  public String getType() { return this.type; }

  
  public void setType(String type) { this.type = type; }

  
  public String getLabel() { return this.label; }

  
  public void setLabel(String label) { this.label = label; }

  
  public Integer getValue() { return this.value; }

  
  public void setValue(Integer value) { this.value = value; }

  
  public Integer getNumber() { return this.number; }

  
  public void setNumber(Integer number) { this.number = number; }

  
  public StatistiscsDTO(String type, String label, Integer value, Integer number) {
    this.type = type;
    this.label = label;
    this.value = value;
    this.number = number;
  }

  
  public StatistiscsDTO() {}

  
  public String toString() { return "StatistiscsDTO [type=" + this.type + ", label=" + this.label + ", value=" + this.value + ", number=" + this.number + "]"; }
}
