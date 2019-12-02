package com.ss.isc.data.archives.common.dto;

/**
 * VillageCodeDTO
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
public class VillageCodeDTO {

    private String villageCode;
    private String credentialNo;
    private String beginTime;
    private String endTime;
    private String userIds;
    private String sqlString;
    private String phoneNo;
    private Integer days;

    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    public String getBeginTime() {
        return this.beginTime;
    }


    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }


    public String getEndTime() {
        return this.endTime;
    }


    public void setEndTime(String endTime) {
        this.endTime = endTime;
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


    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }


    public String getPhoneNo() {
        return this.phoneNo;
    }


    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public Integer getDays() {
        return this.days;
    }


    public void setDays(Integer days) {
        this.days = days;
    }


    public String toString() {
        return "VillageCodeDTO [villageCode=" + this.villageCode + ", credentialNo=" + this.credentialNo + ", beginTime=" + this.beginTime + ", endTime=" + this.endTime + ", userIds=" + this.userIds + ", sqlString=" + this.sqlString + ", phoneNo=" + this.phoneNo + ", days=" + this.days + "]";
    }

}
