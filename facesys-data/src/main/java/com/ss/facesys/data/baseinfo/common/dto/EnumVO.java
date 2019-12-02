package com.ss.facesys.data.baseinfo.common.dto;










public class EnumVO
{
  private String enumName;
  private String enumValue;
  
  public EnumVO(String enumName, String enumValue) {
    this.enumName = enumName;
    this.enumValue = enumValue;
  }
  
  public String getEnumName() { return this.enumName; }

  
  public void setEnumName(String enumName) { this.enumName = enumName; }

  
  public String getEnumValue() { return this.enumValue; }

  
  public void setEnumValue(String enumValue) { this.enumValue = enumValue; }


  
  public String toString() { return "EnumVO [enumName=" + this.enumName + ", enumValue=" + this.enumValue + "]"; }
}
