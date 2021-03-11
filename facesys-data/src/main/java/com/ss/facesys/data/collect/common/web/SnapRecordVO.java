package com.ss.facesys.data.collect.common.web;

import com.ss.request.Pagination;

/**
 * com.ss.facesys.data.collect.common.model
 *
 * @author 李爽超 chao
 * @create 2021/02/24
 * @email lishuangchao@ss-cas.com
 **/
public class SnapRecordVO extends Pagination {

    private Integer id;
    private String captureUrl;
    private String panoramaUrl;
    private Integer captureType;
    private Long captureTime;
    private Integer deviceId;
    private String deviceName;
    private Integer deviceType;
    private Integer glasses;
    private Integer sunglasses;
    private Integer mask;
    private Integer minority;
    private Integer gender;
    private Integer age;
    private Integer ageType;
    private Long startTime;
    private Long endTime;
    private String deviceIds;
    private Integer startAge;
    private Integer endAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaptureUrl() {
        return captureUrl;
    }

    public void setCaptureUrl(String captureUrl) {
        this.captureUrl = captureUrl;
    }

    public String getPanoramaUrl() {
        return panoramaUrl;
    }

    public void setPanoramaUrl(String panoramaUrl) {
        this.panoramaUrl = panoramaUrl;
    }

    public Integer getCaptureType() {
        return captureType;
    }

    public void setCaptureType(Integer captureType) {
        this.captureType = captureType;
    }

    public Long getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Long captureTime) {
        this.captureTime = captureTime;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getGlasses() {
        return glasses;
    }

    public void setGlasses(Integer glasses) {
        this.glasses = glasses;
    }

    public Integer getSunglasses() {
        return sunglasses;
    }

    public void setSunglasses(Integer sunglasses) {
        this.sunglasses = sunglasses;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Integer getMinority() {
        return minority;
    }

    public void setMinority(Integer minority) {
        this.minority = minority;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAgeType() {
        return ageType;
    }

    public void setAgeType(Integer ageType) {
        this.ageType = ageType;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(String deviceIds) {
        this.deviceIds = deviceIds;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }
}
