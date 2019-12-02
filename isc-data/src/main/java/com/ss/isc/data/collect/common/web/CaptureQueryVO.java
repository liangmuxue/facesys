package com.ss.isc.data.collect.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.request.Pagination;
import com.ss.valide.APIPageGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * CaptureQueryVO 抓拍视图对象
 *
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
public class CaptureQueryVO extends Pagination implements Serializable {

    private static final long serialVersionUID = -5950212875104481558L;
    @NotNull(message = "查询开始时间不能为空", groups = {APIPageGroup.class})
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date captureTimeB;
    @NotNull(message = "查询结束时间不能为空", groups = {APIPageGroup.class})
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date captureTimeE;
    private String[] deviceIds;
    private String deviceIdStr;
    private Integer glassesState;
    private Integer sunGlassesState;
    private Integer maskState;
    private Integer race;
    private Integer gender;
    private Integer ageB;
    private Integer ageE;
    private String villageCode;
    private String villageCodes;
    private String[] villages;
    private String userIds;

    public String[] getVillages() {
        return this.villages;
    }
    public void setVillages(String[] villages) {
        this.villages = villages;
    }
    public String getUserIds() {
        return this.userIds;
    }
    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
    public String getVillageCode() {
        return this.villageCode;
    }
    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }
    public Date getCaptureTimeB() {
        return this.captureTimeB;
    }
    public void setCaptureTimeB(Date captureTimeB) {
        this.captureTimeB = captureTimeB;
    }
    public Date getCaptureTimeE() {
        return this.captureTimeE;
    }
    public void setCaptureTimeE(Date captureTimeE) {
        this.captureTimeE = captureTimeE;
    }
    public String[] getDeviceIds() {
        return this.deviceIds;
    }
    public void setDeviceIds(String[] deviceIds) {
        this.deviceIds = deviceIds;
    }
    public Integer getGlassesState() {
        return this.glassesState;
    }
    public void setGlassesState(Integer glassesState) {
        this.glassesState = glassesState;
    }
    public Integer getSunGlassesState() {
        return this.sunGlassesState;
    }
    public void setSunGlassesState(Integer sunGlassesState) {
        this.sunGlassesState = sunGlassesState;
    }
    public Integer getMaskState() {
        return this.maskState;
    }
    public void setMaskState(Integer maskState) {
        this.maskState = maskState;
    }
    public Integer getRace() {
        return this.race;
    }
    public void setRace(Integer race) {
        this.race = race;
    }
    public Integer getGender() {
        return this.gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public Integer getAgeB() {
        return this.ageB;
    }
    public void setAgeB(Integer ageB) {
        this.ageB = ageB;
    }
    public Integer getAgeE() {
        return this.ageE;
    }
    public void setAgeE(Integer ageE) {
        this.ageE = ageE;
    }

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    public String getDeviceIdStr() {
        return deviceIdStr;
    }

    public void setDeviceIdStr(String deviceIdStr) {
        this.deviceIdStr = deviceIdStr;
    }

    @Override
    public String toString() {
        return "CaptureQueryVO{" +
                "captureTimeB=" + captureTimeB +
                ", captureTimeE=" + captureTimeE +
                ", deviceIds=" + Arrays.toString(deviceIds) +
                ", deviceIdStr='" + deviceIdStr + '\'' +
                ", glassesState=" + glassesState +
                ", sunGlassesState=" + sunGlassesState +
                ", maskState=" + maskState +
                ", race=" + race +
                ", gender=" + gender +
                ", ageB=" + ageB +
                ", ageE=" + ageE +
                ", villageCode='" + villageCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", villages=" + Arrays.toString(villages) +
                ", userIds='" + userIds + '\'' +
                '}';
    }

}
