package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * VIID过车信息
 * @author 李爽超 chao
 * @create 2019/10/29
 * @email lishuangchao@ss-cas.com
 **/
public class MotorVehicle {

    @JSONField(name = "MotorVehicleID")
    @JsonProperty("MotorVehicleID")
    private String recordId;
    @JSONField(name = "TollgateID")
    @JsonProperty("TollgateID")
    private String tollgateID;
    @JSONField(name = "DeviceID")
    @JsonProperty("DeviceID")
    private String cameraId;
    @JSONField(name = "StorageUrl1")
    @JsonProperty("StorageUrl1")
    private String platePicUrl;
    @JSONField(name = "StorageUrl2")
    @JsonProperty("StorageUrl2")
    private String plateNoPicUrl;
    @JSONField(name = "PlateClass")
    @JsonProperty("PlateClass")
    private String plateType;
    @JSONField(name = "PlateColor")
    @JsonProperty("PlateColor")
    private String plateColor;
    @JSONField(name = "PlateNo")
    @JsonProperty("PlateNo")
    private String plateNumber;
    @JSONField(name = "VehicleClass")
    @JsonProperty("VehicleClass")
    private String carType;
    @JSONField(name = "VehicleColor")
    @JsonProperty("VehicleColor")
    private String carColor;
    @JSONField(name = "MarkTime")
    @JsonProperty("MarkTime")
    private Date inOutTime;
    @JSONField(name = "NameOfPassedRoad")
    @JsonProperty("NameOfPassedRoad")
    private Date channelName;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getTollgateID() {
        return tollgateID;
    }

    public void setTollgateID(String tollgateID) {
        this.tollgateID = tollgateID;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getPlatePicUrl() {
        return platePicUrl;
    }

    public void setPlatePicUrl(String platePicUrl) {
        this.platePicUrl = platePicUrl;
    }

    public String getPlateNoPicUrl() {
        return plateNoPicUrl;
    }

    public void setPlateNoPicUrl(String plateNoPicUrl) {
        this.plateNoPicUrl = plateNoPicUrl;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Date getInOutTime() {
        return inOutTime;
    }

    public void setInOutTime(Date inOutTime) {
        this.inOutTime = inOutTime;
    }

    public Date getChannelName() {
        return channelName;
    }

    public void setChannelName(Date channelName) {
        this.channelName = channelName;
    }
}
