package com.ss.facesys.data.access.common.web;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


public class CameraReceive {

    @NotBlank(message = "{cameraReceive.captureId.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String captureId;
    private String egCaptureId;
    @NotNull(message = "{cameraReceive.captureTime.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private Long captureTime;
    @NotBlank(message = "{cameraReceive.deviceId.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String deviceId;
    @NotBlank(message = "{cameraReceive.deviceCode.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String deviceCode;
    @NotBlank(message = "{cameraReceive.captureUrlFull.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String captureUrlFull;
    @NotBlank(message = "{cameraReceive.panoramaId.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String panoramaId;
    private String egPanoramaId;
    @NotBlank(message = "{cameraReceive.panoramaUrlFull.empty}", groups = {com.ss.valide.APIAddGroup.class})
    private String panoramaUrlFull;
    private Float x;
    private Float y;
    private Float width;
    private Float height;
    private String fromSystem;
    private String snId;
    private String deviceName;
    private String deviceAddress;
    private String lng;
    private String lat;
    private Integer dataType;
    private String villageCode;
    private String cardId;

    public String getCaptureId() {
        return this.captureId;
    }


    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }


    public String getEgCaptureId() {
        return this.egCaptureId;
    }


    public void setEgCaptureId(String egCaptureId) {
        this.egCaptureId = egCaptureId;
    }


    public Long getCaptureTime() {
        return this.captureTime;
    }


    public void setCaptureTime(Long captureTime) {
        this.captureTime = captureTime;
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


    public String getCaptureUrlFull() {
        return this.captureUrlFull;
    }


    public void setCaptureUrlFull(String captureUrlFull) {
        this.captureUrlFull = captureUrlFull;
    }


    public String getPanoramaId() {
        return this.panoramaId;
    }


    public void setPanoramaId(String panoramaId) {
        this.panoramaId = panoramaId;
    }


    public String getEgPanoramaId() {
        return this.egPanoramaId;
    }


    public void setEgPanoramaId(String egPanoramaId) {
        this.egPanoramaId = egPanoramaId;
    }


    public String getPanoramaUrlFull() {
        return this.panoramaUrlFull;
    }


    public void setPanoramaUrlFull(String panoramaUrlFull) {
        this.panoramaUrlFull = panoramaUrlFull;
    }


    public Float getX() {
        return this.x;
    }


    public void setX(Float x) {
        this.x = x;
    }


    public Float getY() {
        return this.y;
    }


    public void setY(Float y) {
        this.y = y;
    }


    public Float getWidth() {
        return this.width;
    }


    public void setWidth(Float width) {
        this.width = width;
    }


    public Float getHeight() {
        return this.height;
    }


    public void setHeight(Float height) {
        this.height = height;
    }


    public String getFromSystem() {
        return this.fromSystem;
    }


    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }


    public String getSnId() {
        return this.snId;
    }


    public void setSnId(String snId) {
        this.snId = snId;
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


    public String getLng() {
        return this.lng;
    }


    public void setLng(String lng) {
        this.lng = lng;
    }


    public String getLat() {
        return this.lat;
    }


    public void setLat(String lat) {
        this.lat = lat;
    }


    public Integer getDataType() {
        return this.dataType;
    }


    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getCardId() {
        return this.cardId;
    }


    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}
