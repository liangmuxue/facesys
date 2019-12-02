package com.ss.isc.data.access.common.web;

import java.util.Date;


public class WifiCollectReceive {

    private String villageCode;
    private String deviceId;
    private Double lon;
    private Double lat;
    private Integer gisType;
    private String collectMac;
    private String detailAddress;
    private Date collectTime;

    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getDeviceId() {
        return this.deviceId;
    }


    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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


    public Integer getGisType() {
        return this.gisType;
    }


    public void setGisType(Integer gisType) {
        this.gisType = gisType;
    }


    public String getCollectMac() {
        return this.collectMac;
    }


    public void setCollectMac(String collectMac) {
        this.collectMac = collectMac;
    }


    public Date getCollectTime() {
        return this.collectTime;
    }


    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }


    public String getDetailAddress() {
        return this.detailAddress;
    }


    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }


    public String toString() {
        return "WifiReceive [villageCode=" + this.villageCode + ", deviceId=" + this.deviceId + ", lon=" + this.lon + ", lat=" + this.lat + ", gisType=" + this.gisType + ", collectMac=" + this.collectMac + ", collectTime=" + this.collectTime + "]";
    }

}
