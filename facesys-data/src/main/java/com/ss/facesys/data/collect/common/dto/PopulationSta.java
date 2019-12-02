package com.ss.facesys.data.collect.common.dto;

import java.math.BigDecimal;





















public class PopulationSta
{
  private Integer value;
  private String label;
  private String type;
  private Integer num;
  private BigDecimal percent;
  
  public PopulationSta() {}
  
  public PopulationSta(Integer value, String label, String type, Integer num, BigDecimal percent) {
    this.value = value;
    this.label = label;
    this.type = type;
    this.num = num;
    this.percent = percent;
  }

  
  public String getType() { return this.type; }



  
  public void setType(String type) { this.type = type; }


  
  public Integer getValue() { return this.value; }


  
  public void setValue(Integer value) { this.value = value; }


  
  public String getLabel() { return this.label; }


  
  public void setLabel(String label) { this.label = label; }


  
  public Integer getNum() { return this.num; }


  
  public void setNum(Integer num) { this.num = num; }




  
  public BigDecimal getPercent() { return this.percent; }




  
  public void setPercent(BigDecimal percent) { this.percent = percent; }
}
