package com.ss.isc.data.collect.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;


























































































@Table
public class Vehicle
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer id;
  private Integer infoNumber;
  private String bayonetNumber;
  private String bayonetName;
  private Double lat;
  private Double lon;
  private Date elapsedTime;
  private Integer laneNumber;
  private String licensePlate;
  private String licenseColour;
  private Integer directionCode;
  private String driveDirection;
  private String vehicleType;
  private String vehicleColour;
  private String licenseType;
  private Double speed;
  private String rowNum;
  private String illegalCode;
  private String licensePlatePicture;
  private String passPicture;
  private Date createTime;
  private Date beginTime;
  private Date endTime;
  private Integer currentPage;
  private Integer pageSize;
  private String villageCode;
  
  public String getVillageCode() { return this.villageCode; }


  
  public void setVillageCode(String villageCode) { this.villageCode = villageCode; }


  
  public Integer getCurrentPage() { return this.currentPage; }


  
  public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }


  
  public Integer getPageSize() { return this.pageSize; }


  
  public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }


  
  public String getRowNum() { return this.rowNum; }


  
  public void setRowNum(String rowNum) { this.rowNum = rowNum; }


  
  public Integer getId() { return this.id; }


  
  public void setId(Integer id) { this.id = id; }


  
  public Integer getInfoNumber() { return this.infoNumber; }


  
  public void setInfoNumber(Integer infoNumber) { this.infoNumber = infoNumber; }


  
  public String getBayonetNumber() { return this.bayonetNumber; }


  
  public void setBayonetNumber(String bayonetNumber) { this.bayonetNumber = (bayonetNumber == null) ? null : bayonetNumber.trim(); }


  
  public String getBayonetName() { return this.bayonetName; }


  
  public void setBayonetName(String bayonetName) { this.bayonetName = (bayonetName == null) ? null : bayonetName.trim(); }


  
  public Double getLon() { return this.lon; }


  
  public void setLon(Double lon) { this.lon = lon; }


  
  public Double getLat() { return this.lat; }


  
  public void setLat(Double lat) { this.lat = lat; }


  
  public Date getElapsedTime() { return this.elapsedTime; }


  
  public void setElapsedTime(Date elapsedTime) { this.elapsedTime = elapsedTime; }


  
  public Integer getLaneNumber() { return this.laneNumber; }


  
  public void setLaneNumber(Integer laneNumber) { this.laneNumber = laneNumber; }


  
  public String getLicensePlate() { return this.licensePlate; }


  
  public void setLicensePlate(String licensePlate) { this.licensePlate = (licensePlate == null) ? null : licensePlate.trim(); }


  
  public String getLicenseColour() { return this.licenseColour; }


  
  public void setLicenseColour(String licenseColour) { this.licenseColour = (licenseColour == null) ? null : licenseColour.trim(); }


  
  public Integer getDirectionCode() { return this.directionCode; }


  
  public void setDirectionCode(Integer directionCode) { this.directionCode = directionCode; }


  
  public String getDriveDirection() { return this.driveDirection; }


  
  public void setDriveDirection(String driveDirection) { this.driveDirection = (driveDirection == null) ? null : driveDirection.trim(); }


  
  public String getVehicleType() { return this.vehicleType; }


  
  public void setVehicleType(String vehicleType) { this.vehicleType = (vehicleType == null) ? null : vehicleType.trim(); }


  
  public String getVehicleColour() { return this.vehicleColour; }


  
  public void setVehicleColour(String vehicleColour) { this.vehicleColour = (vehicleColour == null) ? null : vehicleColour.trim(); }


  
  public String getLicenseType() { return this.licenseType; }


  
  public void setLicenseType(String licenseType) { this.licenseType = (licenseType == null) ? null : licenseType.trim(); }


  
  public Double getSpeed() { return this.speed; }


  
  public void setSpeed(Double speed) { this.speed = speed; }


  
  public String getIllegalCode() { return this.illegalCode; }


  
  public void setIllegalCode(String illegalCode) { this.illegalCode = (illegalCode == null) ? null : illegalCode.trim(); }


  
  public String getLicensePlatePicture() { return this.licensePlatePicture; }


  
  public void setLicensePlatePicture(String licensePlatePicture) { this.licensePlatePicture = (licensePlatePicture == null) ? null : licensePlatePicture.trim(); }


  
  public String getPassPicture() { return this.passPicture; }


  
  public void setPassPicture(String passPicture) { this.passPicture = (passPicture == null) ? null : passPicture.trim(); }


  
  public Date getCreateTime() { return this.createTime; }


  
  public void setCreateTime(Date createTime) { this.createTime = createTime; }


  
  public Date getBeginTime() { return this.beginTime; }


  
  public void setBeginTime(Date beginTime) { this.beginTime = beginTime; }



  
  public Date getEndTime() { return this.endTime; }


  
  public void setEndTime(Date endTime) { this.endTime = endTime; }



  
  public String toString() { return "Vehicle [id=" + this.id + ", infoNumber=" + this.infoNumber + ", bayonetNumber=" + this.bayonetNumber + ", bayonetName=" + this.bayonetName + ", lat=" + this.lat + ", lon=" + this.lon + ", elapsedTime=" + this.elapsedTime + ", laneNumber=" + this.laneNumber + ", licensePlate=" + this.licensePlate + ", licenseColour=" + this.licenseColour + ", directionCode=" + this.directionCode + ", driveDirection=" + this.driveDirection + ", vehicleType=" + this.vehicleType + ", vehicleColour=" + this.vehicleColour + ", licenseType=" + this.licenseType + ", speed=" + this.speed + ", rowNum=" + this.rowNum + ", illegalCode=" + this.illegalCode + ", licensePlatePicture=" + this.licensePlatePicture + ", passPicture=" + this.passPicture + ", createTime=" + this.createTime + ", beginTime=" + this.beginTime + ", endTime=" + this.endTime + ", currentPage=" + this.currentPage + ", pageSize=" + this.pageSize + "]"; }
}
