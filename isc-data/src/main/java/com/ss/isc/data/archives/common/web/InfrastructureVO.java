package com.ss.isc.data.archives.common.web;













public class InfrastructureVO
{
  private Integer id;
  private Integer houseId;
  private Integer water;
  private Integer electric;
  private Integer coalGas;
  private String createTime;
  private String updateTime;
  
  public Integer getId() { return this.id; }

  
  public void setId(Integer id) { this.id = id; }

  
  public Integer getHouseId() { return this.houseId; }

  
  public void setHouseId(Integer houseId) { this.houseId = houseId; }

  
  public Integer getWater() { return this.water; }

  
  public void setWater(Integer water) { this.water = water; }

  
  public Integer getElectric() { return this.electric; }

  
  public void setElectric(Integer electric) { this.electric = electric; }

  
  public Integer getCoalGas() { return this.coalGas; }

  
  public void setCoalGas(Integer coalGas) { this.coalGas = coalGas; }

  
  public String getCreateTime() { return this.createTime; }

  
  public void setCreateTime(String createTime) { this.createTime = createTime; }

  
  public String getUpdateTime() { return this.updateTime; }

  
  public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }


  
  public String toString() { return "InfrastructureVO [id=" + this.id + ", houseId=" + this.houseId + ", water=" + this.water + ", electric=" + this.electric + ", coalGas=" + this.coalGas + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "]"; }
}
