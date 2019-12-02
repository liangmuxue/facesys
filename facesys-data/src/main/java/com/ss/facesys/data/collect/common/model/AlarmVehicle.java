package com.ss.facesys.data.collect.common.model;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
* 过车告警
* @author chao
* @create 2019/10/31
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_vehicle_alarm_record")
public class AlarmVehicle implements Serializable {

    private static final long serialVersionUID = -7878298422192029920L;
    private Integer id;
    private String notificationID;
    private String dispositionID;
    private String title;
    private String triggerTime;
    private String CntObjectID;
    private String villageCode;
    private String tollgateID;
    private String cameraId;
    private String plateType;
    private String carType;
    private String plateColor;
    private String plateNumber;
    private Date inOutTime;
    private Integer inOutType;
    private String channelName;
    private String platePicUrl;
    private Integer plateNoPicUrl;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getDispositionID() {
        return dispositionID;
    }

    public void setDispositionID(String dispositionID) {
        this.dispositionID = dispositionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(String triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getCntObjectID() {
        return CntObjectID;
    }

    public void setCntObjectID(String cntObjectID) {
        CntObjectID = cntObjectID;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
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

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
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

    public Date getInOutTime() {
        return inOutTime;
    }

    public void setInOutTime(Date inOutTime) {
        this.inOutTime = inOutTime;
    }

    public Integer getInOutType() {
        return inOutType;
    }

    public void setInOutType(Integer inOutType) {
        this.inOutType = inOutType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getPlatePicUrl() {
        return platePicUrl;
    }

    public void setPlatePicUrl(String platePicUrl) {
        this.platePicUrl = platePicUrl;
    }

    public Integer getPlateNoPicUrl() {
        return plateNoPicUrl;
    }

    public void setPlateNoPicUrl(Integer plateNoPicUrl) {
        this.plateNoPicUrl = plateNoPicUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
