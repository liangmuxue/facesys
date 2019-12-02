package com.ss.facesys.data.collect.common.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * VehicleRecord 过车记录
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_vehicle_record")
public class VehicleRecord implements Serializable {

    private static final long serialVersionUID = -7878298422192029920L;
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
    @Transient
    private Integer currentPage;
    @Transient
    private Integer pageSize;
    @Transient
    private String beginTime;
    @Transient
    private String endTime;
    @Transient
    private String[] villages;
    @Transient
    private String villagesString;
    @Transient
    private String userIds;
    @Transient
    private Double lat;
    @Transient
    private Double lon;
    private Integer withState;
    private Integer leaveState;
    private Integer retenState;
    @Transient
    private String platePicImg;
    @Transient
    private String plateNoPicImg;

    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public String[] getVillages() {
        return this.villages;
    }


    public void setVillages(String[] villages) {
        this.villages = villages;
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


    public Integer getCurrentPage() {
        return this.currentPage;
    }


    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }


    public Integer getPageSize() {
        return this.pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public Double getLat() {
        return this.lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    public Double getLon() {
        return this.lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    public Integer getWithState() {
        return this.withState;
    }


    public void setWithState(Integer withState) {
        this.withState = withState;
    }


    public Integer getLeaveState() {
        return this.leaveState;
    }


    public void setLeaveState(Integer leaveState) {
        this.leaveState = leaveState;
    }


    public Integer getRetenState() {
        return this.retenState;
    }


    public void setRetenState(Integer retenState) {
        this.retenState = retenState;
    }


    public String getPlatePicImg() {
        return this.platePicImg;
    }


    public void setPlatePicImg(String platePicImg) {
        this.platePicImg = platePicImg;
    }


    public String getPlateNoPicImg() {
        return this.plateNoPicImg;
    }


    public void setPlateNoPicImg(String plateNoPicImg) {
        this.plateNoPicImg = plateNoPicImg;
    }


    public String getVillagesString() {
        return this.villagesString;
    }


    public void setVillagesString(String villagesString) {
        this.villagesString = villagesString;
    }

    @Override
    public String toString() {
        return "VehicleRecord{" +
                "id=" + id +
                ", villageCode='" + villageCode + '\'' +
                ", tollgateID='" + tollgateID + '\'' +
                ", cameraId='" + cameraId + '\'' +
                ", recordId='" + recordId + '\'' +
                ", plateType='" + plateType + '\'' +
                ", carType='" + carType + '\'' +
                ", plateColor='" + plateColor + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", inOutTime=" + inOutTime +
                ", inOutType=" + inOutType +
                ", channelName='" + channelName + '\'' +
                ", platePicUrl='" + platePicUrl + '\'' +
                ", plateNoPicUrl='" + plateNoPicUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", villages=" + Arrays.toString(villages) +
                ", villagesString='" + villagesString + '\'' +
                ", userIds='" + userIds + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", withState=" + withState +
                ", leaveState=" + leaveState +
                ", retenState=" + retenState +
                ", platePicImg='" + platePicImg + '\'' +
                ", plateNoPicImg='" + plateNoPicImg + '\'' +
                '}';
    }

}
