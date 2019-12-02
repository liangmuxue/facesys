package com.ss.isc.data.collect.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.request.Pagination;

import java.util.Arrays;
import java.util.Date;

/**
 * VehicleQueryVO
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
public class VehicleQueryVO extends Pagination {

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String beginTime;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;
    private String[] villages;
    private String villageCodes;
    private String userIds;
    private Integer inOutType;
    private String plateNumber;
    private String channelName;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] getVillages() {
        return villages;
    }

    public void setVillages(String[] villages) {
        this.villages = villages;
    }

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Integer getInOutType() {
        return inOutType;
    }

    public void setInOutType(Integer inOutType) {
        this.inOutType = inOutType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return "VehicleQueryVO{" +
                "beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", villages=" + Arrays.toString(villages) +
                ", villageCodes='" + villageCodes + '\'' +
                ", userIds='" + userIds + '\'' +
                ", inOutType=" + inOutType +
                ", plateNumber='" + plateNumber + '\'' +
                ", channelName='" + channelName + '\'' +
                '}';
    }

}
