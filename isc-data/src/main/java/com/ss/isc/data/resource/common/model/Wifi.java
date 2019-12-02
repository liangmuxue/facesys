package com.ss.isc.data.resource.common.model;

import com.ss.isc.util.export.excel.annotation.ExcelField;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Table;
import javax.persistence.Transient;































@Table(name = "cw_wifi_device")
public class Wifi
{
  private Integer id;
  private String villageCode;
  private String deviceId;
  private String deviceName;
  private String networkName;
  private String detailAddress;
  private String lon;
  private String lat;
  private Integer gisType;
  private String devicePicUrl;
  private Date createTime;
  private Date updateTime;
  private String alt;
  @Transient
  private List<String> villages;
  @Transient
  private String villageName;
  @Transient
  private String s;
  @Transient
  private String enumName;
  @Transient
  private String phone;
  @Transient
  private Date collectTime;
  @Transient
  private String userIds;
  @Transient
  private Integer currentPage;
  @Transient
  private Integer pageSize;
  @Transient
  private String beginTime;
  @Transient
  private String endTime;
  @Transient
  private String rowNum;
  @Transient
  private Integer pageNum;
  @Transient
  private String regionCode;
  @Transient
  private Map<String, String> sqlMap;
  @Transient
  private String villageCodes;
  @Transient
  private String collectMac;
  
  public String getAlt() { return this.alt; }


  
  public void setAlt(String alt) { this.alt = alt; }










































  
  public Date getCreateTime() { return this.createTime; }


  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }


  
  public Date getUpdateTime() { return this.updateTime; }


  
  public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }


  
  public String getCollectMac() { return this.collectMac; }


  
  public void setCollectMac(String collectMac) { this.collectMac = collectMac; }


  
  public String getVillageCodes() { return this.villageCodes; }


  
  public void setVillageCodes(String villageCodes) { this.villageCodes = villageCodes; }


  
  public Map<String, String> getSqlMap() { return this.sqlMap; }


  
  public void setSqlMap(Map<String, String> sqlMap) { this.sqlMap = sqlMap; }


  
  public Integer getPageNum() { return this.pageNum; }


  
  public void setPageNum(Integer pageNum) { this.pageNum = pageNum; }


  
  public String getRegionCode() { return this.regionCode; }


  
  public void setRegionCode(String regionCode) { this.regionCode = regionCode; }


  
  public String getRowNum() { return this.rowNum; }


  
  public void setRowNum(String rowNum) { this.rowNum = rowNum; }


  
  public String getBeginTime() { return this.beginTime; }


  
  public void setBeginTime(String beginTime) { this.beginTime = beginTime; }


  
  public String getEndTime() { return this.endTime; }


  
  public void setEndTime(String endTime) { this.endTime = endTime; }


  
  public Integer getCurrentPage() { return this.currentPage; }


  
  public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }


  
  public Integer getPageSize() { return this.pageSize; }


  
  public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }


  
  public String getUserIds() { return this.userIds; }


  
  public void setUserIds(String userIds) { this.userIds = userIds; }


  
  public String getPhone() { return this.phone; }


  
  public void setPhone(String phone) { this.phone = phone; }


  
  public Date getCollectTime() { return this.collectTime; }


  
  public void setCollectTime(Date collectTime) { this.collectTime = collectTime; }


  
  public String getEnumName() { return this.enumName; }


  
  public void setEnumName(String enumName) { this.enumName = enumName; }



  
  public String getS() { return this.s; }


  
  public void setS(String s) { this.s = s; }


  
  public String getVillageName() { return this.villageName; }


  
  public void setVillageName(String villageName) { this.villageName = villageName; }


  
  public List<String> getVillages() { return this.villages; }


  
  public void setVillages(List<String> villages) { this.villages = villages; }







  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  @ExcelField(title = "小区编号", type = 0, align = 2, sort = 1)
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  @ExcelField(title = "探针的mac", type = 0, align = 2, sort = 2)
  public String getDeviceId() { return this.deviceId; }


  
  public void setDeviceId(String deviceId) { this.deviceId = deviceId; }


  
  @ExcelField(title = "名称", type = 0, align = 2, sort = 3)
  public String getDeviceName() { return this.deviceName; }


  
  public void setDeviceName(String deviceName) { this.deviceName = deviceName; }


  
  @ExcelField(title = "网络名称", type = 0, align = 2, sort = 4)
  public String getNetworkName() { return this.networkName; }


  
  public void setNetworkName(String networkName) { this.networkName = networkName; }


  
  @ExcelField(title = "详细地址", type = 0, align = 2, sort = 5)
  public String getDetailAddress() { return this.detailAddress; }


  
  public void setDetailAddress(String detailAddress) { this.detailAddress = detailAddress; }


  
  @ExcelField(title = "经度", type = 0, align = 2, sort = 6)
  public String getLon() { return this.lon; }


  
  public void setLon(String lon) { this.lon = lon; }


  
  @ExcelField(title = "纬度", type = 0, align = 2, sort = 7)
  public String getLat() { return this.lat; }


  
  public void setLat(String lat) { this.lat = lat; }


  
  @ExcelField(title = "坐标体系", type = 0, align = 2, sort = 8)
  public Integer getGisType() { return this.gisType; }


  
  public void setGisType(Integer gisType) { this.gisType = gisType; }


  
  public String getDevicePicUrl() { return this.devicePicUrl; }


  
  public void setDevicePicUrl(String devicePicUrl) { this.devicePicUrl = devicePicUrl; }
}
