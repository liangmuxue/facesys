package com.ss.facesys.data.collect.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * com.ss.facesys.data.collect.common.model
 *
 * @author 李爽超 chao
 * @create 2021/02/24
 * @email lishuangchao@ss-cas.com
 **/
@Table(name = "ss_snap_record")
public class SnapRecord {

    @Id
    private Integer id;
    @Column(name = "capture_url")
    private String captureUrl;
    @Column(name = "panorama_url")
    private String panoramaUrl;
    @Column(name = "capture_type")
    private Integer captureType;
    @Column(name = "capture_time")
    private Long captureTime;
    @Column(name = "device_id")
    private Integer deviceId;
    @Column(name = "device_name")
    private String deviceName;
    private Integer glasses;
    private Integer sunglasses;
    private Integer mask;
    private Integer minority;
    private Integer gender;
    private Integer age;
    @Column(name = "age_type")
    private Integer ageType;

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
}
