package com.ss.isc.data.access.common.dto;

import java.util.Date;


public class AlarmDTO {

    private String alarmId;
    private String monitorId;
    private Float alarmScore;
    private String captureId;
    private Date captureTime;
    private Date alarmTime;
    private String deviceId;
    private String deviceCode;
    private String deviceName;
    private String deviceAddress;
    private String captureUrlFull;
    private Float simScore;
    private String faceId;
    private String facedbId;
    private String facedbName;
    private String faceUrlFull;
    private String faceName;
    private String faceCardId;
    private Integer age;
    private String villageCode;

    public String getAlarmId() {
        return this.alarmId;
    }


    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }


    public String getMonitorId() {
        return this.monitorId;
    }


    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }


    public Float getAlarmScore() {
        return this.alarmScore;
    }


    public void setAlarmScore(Float alarmScore) {
        this.alarmScore = alarmScore;
    }


    public String getCaptureId() {
        return this.captureId;
    }


    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }


    public Date getCaptureTime() {
        return this.captureTime;
    }


    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }


    public Date getAlarmTime() {
        return this.alarmTime;
    }


    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }


    public String getDeviceId() {
        return this.deviceId;
    }


    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    public String getDeviceCode() {
        return this.deviceCode;
    }


    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }


    public String getDeviceName() {
        return this.deviceName;
    }


    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    public String getDeviceAddress() {
        return this.deviceAddress;
    }


    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }


    public String getCaptureUrlFull() {
        return this.captureUrlFull;
    }


    public void setCaptureUrlFull(String captureUrlFull) {
        this.captureUrlFull = captureUrlFull;
    }


    public Float getSimScore() {
        return this.simScore;
    }


    public void setSimScore(Float simScore) {
        this.simScore = simScore;
    }


    public String getFaceId() {
        return this.faceId;
    }


    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }


    public String getFacedbId() {
        return this.facedbId;
    }


    public void setFacedbId(String facedbId) {
        this.facedbId = facedbId;
    }


    public String getFacedbName() {
        return this.facedbName;
    }


    public void setFacedbName(String facedbName) {
        this.facedbName = facedbName;
    }


    public String getFaceUrlFull() {
        return this.faceUrlFull;
    }


    public void setFaceUrlFull(String faceUrlFull) {
        this.faceUrlFull = faceUrlFull;
    }


    public String getFaceName() {
        return this.faceName;
    }


    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }


    public String getFaceCardId() {
        return this.faceCardId;
    }


    public void setFaceCardId(String faceCardId) {
        this.faceCardId = faceCardId;
    }


    public Integer getAge() {
        return this.age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String toString() {
        return "AlarmDTO [alarmId=" + this.alarmId + ", monitorId=" + this.monitorId + ", alarmScore=" + this.alarmScore + ", captureId=" + this.captureId + ", captureTime=" + this.captureTime + ", alarmTime=" + this.alarmTime + ", deviceId=" + this.deviceId + ", deviceCode=" + this.deviceCode + ", deviceName=" + this.deviceName + ", deviceAddress=" + this.deviceAddress + ", captureUrlFull=" + this.captureUrlFull + ", simScore=" + this.simScore + ", faceId=" + this.faceId + ", facedbId=" + this.facedbId + ", facedbName=" + this.facedbName + ", faceUrlFull=" + this.faceUrlFull + ", faceName=" + this.faceName + ", faceCardId=" + this.faceCardId + ", age=" + this.age + "]";
    }

}
