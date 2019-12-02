package com.ss.isc.data.collect.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * VehicleDTO
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
public class VehicleDTO implements Serializable {

    private static final long serialVersionUID = 7796103439481911279L;
    private Integer id;
    private String villageCode;
    private String tollgateID;
    private String cameraId;
    private String recordId;
    private String plateType;
    private String carType;
    private String plateColor;
    private String plateNumber;
    private Date inOutTime;
    private Integer inOutType;
    private String channelName;
    private String platePicUrl;
    private String plateNoPicUrl;
    private Date createTime;
    private Date updateTime;
    private String rowNum;
    private String registerName;
    private String residenceDetailAddres;
    private Double lat;
    private Double lng;
    private Double lon;

    public String getRowNum() {
        return this.rowNum;
    }


    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }


    public String getRegisterName() {
        return this.registerName;
    }


    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }


    public String getResidenceDetailAddres() {
        return this.residenceDetailAddres;
    }


    public void setResidenceDetailAddres(String residenceDetailAddres) {
        this.residenceDetailAddres = residenceDetailAddres;
    }


    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getTollgateID() {
        return this.tollgateID;
    }


    public void setTollgateID(String tollgateID) {
        this.tollgateID = tollgateID;
    }


    public String getCameraId() {
        return this.cameraId;
    }


    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }


    public String getRecordId() {
        return this.recordId;
    }


    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }


    public String getPlateType() {
        return this.plateType;
    }


    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }


    public String getCarType() {
        return this.carType;
    }


    public void setCarType(String carType) {
        this.carType = carType;
    }


    public String getPlateColor() {
        return this.plateColor;
    }


    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }


    public String getPlateNumber() {
        return this.plateNumber;
    }


    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


    public Date getInOutTime() {
        return this.inOutTime;
    }


    public void setInOutTime(Date inOutTime) {
        this.inOutTime = inOutTime;
    }


    public Integer getInOutType() {
        return this.inOutType;
    }


    public void setInOutType(Integer inOutType) {
        this.inOutType = inOutType;
    }


    public String getChannelName() {
        return this.channelName;
    }


    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


    public String getPlatePicUrl() {
        return this.platePicUrl;
    }


    public void setPlatePicUrl(String platePicUrl) {
        this.platePicUrl = platePicUrl;
    }


    public String getPlateNoPicUrl() {
        return this.plateNoPicUrl;
    }


    public void setPlateNoPicUrl(String plateNoPicUrl) {
        this.plateNoPicUrl = plateNoPicUrl;
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


    public Double getLat() {
        return this.lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    public Double getLng() {
        return this.lng;
    }


    public void setLng(Double lng) {
        this.lng = lng;
    }


    public Double getLon() {
        return this.lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    public VehicleDTO() {
    }


    public VehicleDTO(Integer id, String villageCode, String tollgateID, String cameraId, String recordId, String plateType, String carType, String plateColor, String plateNumber, Date inOutTime, Integer inOutType, String channelName, String platePicUrl, String plateNoPicUrl, Date createTime, Date updateTime, String registerName, String residenceDetailAddres, Double lat, Double lng, Double lon) {
        this.id = id;
        this.villageCode = villageCode;
        this.tollgateID = tollgateID;
        this.cameraId = cameraId;
        this.recordId = recordId;
        this.plateType = plateType;
        this.carType = carType;
        this.plateColor = plateColor;
        this.plateNumber = plateNumber;
        this.inOutTime = inOutTime;
        this.inOutType = inOutType;
        this.channelName = channelName;
        this.platePicUrl = platePicUrl;
        this.plateNoPicUrl = plateNoPicUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.registerName = registerName;
        this.residenceDetailAddres = residenceDetailAddres;
        this.lat = lat;
        this.lng = lng;
        this.lon = lon;
    }

}
