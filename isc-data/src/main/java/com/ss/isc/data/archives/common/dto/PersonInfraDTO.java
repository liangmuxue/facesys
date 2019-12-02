package com.ss.isc.data.archives.common.dto;
/**
* 人房关系实体类
* @author chao
* @create 2019/9/23
* @email lishuangchao@ss-cas.com
**/
public class PersonInfraDTO {
    private String houseId;
    private String year;
    private String month;
    private String villageCode;
    private String buildingNo;
    private String unitNo;
    private String floor;
    private String houseNo;
    private String userIds;
    private String sqlString;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getYear() {
        return this.year;
    }


    public void setYear(String year) {
        this.year = year;
    }


    public String getMonth() {
        return this.month;
    }


    public void setMonth(String month) {
        this.month = month;
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


    public String getUnitNo() {
        return this.unitNo;
    }


    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }


    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public String getSqlString() {
        return this.sqlString;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }
}
