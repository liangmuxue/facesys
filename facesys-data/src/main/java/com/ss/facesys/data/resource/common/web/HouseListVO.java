package com.ss.facesys.data.resource.common.web;

import javax.persistence.Table;
import java.util.Date;

import com.ss.facesys.data.baseinfo.common.web.BaseQueryEntity;

@Table
public class HouseListVO extends BaseQueryEntity {
    private static final long serialVersionUID = 1L;
    private int id;
    private String villageCode;
    private String buildingno;
    private String houseno;
    private String floor;
    private int peoplerelation;
    private int houseType;
    private int gistype;
    private Double lon;
    private Double lat;
    private Double alt;
    private String address;
    private Date createtime;
    private Date updatetime;
    private String enumName;
    private String villageName;
    private Integer rowNum;
    private String unitName;
    private String unitNo;
    private String buildingNoAndUnitNo;
    private String thirdRegionCode;
    private String thirdStreetCode;
    private String thirdId;
    private String buildingName;
    private String countPeople;
    private String countCar;

    public String getBuildingName() {
        return this.buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitName() {
        return this.unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitNo() {
        return this.unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getBuildingNoAndUnitNo() {
        return this.buildingNoAndUnitNo;
    }

    public void setBuildingNoAndUnitNo(String buildingNoAndUnitNo) {
        this.buildingNoAndUnitNo = buildingNoAndUnitNo;
    }

    public static long getSerialversionuid() {
        return 1L;
    }

    public String getCountCar() {
        return this.countCar;
    }

    public void setCountCar(String countCar) {
        this.countCar = countCar;
    }

    public String getCountPeople() {
        return this.countPeople;
    }

    public void setCountPeople(String countPeople) {
        this.countPeople = countPeople;
    }

    public Integer getRowNum() {
        return this.rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public String getVillageName() {
        return this.villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getEnumName() {
        return this.enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

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

    public String getBuildingno() {
        return this.buildingno;
    }

    public void setBuildingno(String buildingno) {
        this.buildingno = buildingno;
    }

    public String getHouseno() {
        return this.houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
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

    public int getGistype() {
        return this.gistype;
    }

    public void setGistype(int gistype) {
        this.gistype = gistype;
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

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }
}
