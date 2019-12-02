package com.ss.facesys.data.resource.common.model;

import com.ss.facesys.util.export.excel.annotation.ExcelField;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
* 实有房屋实体类
* @author chao
* @create 2019/9/24
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_base_house")
public class House implements Serializable {
  private static final long serialVersionUID = 477939492829652050L;
  private Integer id;
  private String villageCode;
  private String buildingNo;
  private String unitNo;
  private String houseNo;
  private String floor;
  private Integer peopleRelation;
  private Integer houseType;
  private Integer gisType;
  private Double lon;
  private Double lat;
  private Double alt;
  private String address;
  private Date createTime;
  private Date updateTime;
  private Integer status;
  private String thirdId;
  @Transient
  private Map<String, String> sqlMap;
  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  @ExcelField(title = "小区编号", type = 0, align = 2, sort = 1)
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  @ExcelField(title = "单元编号", type = 0, align = 2, sort = 3)
  public String getUnitNo() { return this.unitNo; }


  
  public void setUnitNo(String unitNo) { this.unitNo = unitNo; }


  
  @ExcelField(title = "楼栋编号", type = 0, align = 2, sort = 2)
  public String getBuildingNo() { return this.buildingNo; }


  
  public void setBuildingNo(String buildingNo) { this.buildingNo = buildingNo; }


  
  @ExcelField(title = "房屋编号", type = 0, align = 2, sort = 4)
  public String getHouseNo() { return this.houseNo; }


  
  public void setHouseNo(String houseNo) { this.houseNo = houseNo; }


  
  @ExcelField(title = "楼层", type = 0, align = 2, sort = 5)
  public String getFloor() { return this.floor; }


  
  public void setFloor(String floor) { this.floor = floor; }


  
  @ExcelField(title = "人房关系", type = 0, align = 2, sort = 6)
  public Integer getPeopleRelation() { return this.peopleRelation; }


  
  public void setPeopleRelation(Integer peopleRelation) { this.peopleRelation = peopleRelation; }


  
  @ExcelField(title = "房屋类别", type = 0, align = 2, sort = 7)
  public Integer getHouseType() { return this.houseType; }


  
  public void setHouseType(Integer houseType) { this.houseType = houseType; }


  
  @ExcelField(title = "坐标体系", type = 0, align = 2, sort = 8)
  public Integer getGisType() { return this.gisType; }


  
  public void setGisType(Integer gisType) { this.gisType = gisType; }


  
  @ExcelField(title = "经度", type = 0, align = 2, sort = 9)
  public Double getLon() { return this.lon; }


  
  public void setLon(Double lon) { this.lon = lon; }


  
  @ExcelField(title = "纬度", type = 0, align = 2, sort = 10)
  public Double getLat() { return this.lat; }


  
  public void setLat(Double lat) { this.lat = lat; }


  
  @ExcelField(title = "高度", type = 0, align = 2, sort = 11)
  public Double getAlt() { return this.alt; }


  
  public void setAlt(Double alt) { this.alt = alt; }


  
  @ExcelField(title = "房屋地址", type = 0, align = 2, sort = 12)
  public String getAddress() { return this.address; }


  
  public void setAddress(String address) { this.address = address; }


  
  public Date getCreateTime() { return this.createTime; }


  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }


  
  public Date getUpdateTime() { return this.updateTime; }


  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

  
  public Map<String, String> getSqlMap() {
    if (this.sqlMap == null) {
      this.sqlMap = Maps.newHashMap();
    }
    return this.sqlMap;
  }

  
  public void setSqlMap(Map<String, String> sqlMap) { this.sqlMap = sqlMap; }


  
  public Integer getStatus() { return this.status; }


  
  public void setStatus(Integer status) { this.status = status; }


  
  @ExcelField(title = "第三方编号", type = 0, align = 2, sort = 13)
  public String getThirdId() { return this.thirdId; }


  
  public void setThirdId(String thirdId) { this.thirdId = thirdId; }
}
