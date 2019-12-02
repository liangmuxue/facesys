package com.ss.isc.sync.data.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;


@Table
public class House implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String villageCode;
    private String buildingNo;
    private String houseNo;
    private String floor;
    private int peoplerelation;
    private int houseType;
    private int gisType;
    private int status;
    private Double lon;
    private Double lat;
    private Double alt;
    private String address;
    private Date createTime;
    private Date updateTime;
    private String unitNo;
    private String thirdRegionCode;
    private String thirdStreetCode;
    private String remark;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getBuildingNo() {
        return this.buildingNo;
    }


    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }


    public String getHouseNo() {
        return this.houseNo;
    }


    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }


    public String getFloor() {
        return this.floor;
    }


    public void setFloor(String floor) {
        this.floor = floor;
    }


    public int getPeoplerelation() {
        return this.peoplerelation;
    }


    public void setPeoplerelation(int peoplerelation) {
        this.peoplerelation = peoplerelation;
    }


    public int getHouseType() {
        return this.houseType;
    }


    public void setHouseType(int houseType) {
        this.houseType = houseType;
    }


    public int getGisType() {
        return this.gisType;
    }


    public void setGisType(int gisType) {
        this.gisType = gisType;
    }


    public int getStatus() {
        return this.status;
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public Double getLon() {
        return this.lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    public Double getLat() {
        return this.lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    public Double getAlt() {
        return this.alt;
    }


    public void setAlt(Double alt) {
        this.alt = alt;
    }


    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getUnitNo() {
        return this.unitNo;
    }


    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }


    public String getThirdRegionCode() {
        return this.thirdRegionCode;
    }


    public void setThirdRegionCode(String thirdRegionCode) {
        this.thirdRegionCode = thirdRegionCode;
    }


    public String getThirdStreetCode() {
        return this.thirdStreetCode;
    }


    public void setThirdStreetCode(String thirdStreetCode) {
        this.thirdStreetCode = thirdStreetCode;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }

}
