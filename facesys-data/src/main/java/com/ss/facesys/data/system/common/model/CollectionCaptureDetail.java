package com.ss.facesys.data.system.common.model;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.StringJoiner;

/**
 * CollectionCaptureDetail
 *
 * @author FrancisYs
 * @date 2020/2/18
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "ss_collection_capture_detail")
public class CollectionCaptureDetail {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    @Column(name = "capture_id")
    private String captureId;
    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "device_address")
    private String deviceAddress;
    @Column(name = "capture_time")
    private Long captureTime;
    @Column(name = "capture_url")
    private String captureUrl;
    @Column(name = "panorama_url")
    private String panoramaUrl;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "mask_state")
    private Integer maskState;
    @Column(name = "glasses_state")
    private Integer glassesState;
    @Column(name = "sun_glasses_state")
    private Integer sunGlassesState;
    @Column(name = "nation")
    private Integer nation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaptureId() {
        return captureId;
    }

    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public Long getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Long captureTime) {
        this.captureTime = captureTime;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getMaskState() {
        return maskState;
    }

    public void setMaskState(Integer maskState) {
        this.maskState = maskState;
    }

    public Integer getGlassesState() {
        return glassesState;
    }

    public void setGlassesState(Integer glassesState) {
        this.glassesState = glassesState;
    }

    public Integer getSunGlassesState() {
        return sunGlassesState;
    }

    public void setSunGlassesState(Integer sunGlassesState) {
        this.sunGlassesState = sunGlassesState;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CollectionCaptureDetail.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("captureId='" + captureId + "'")
                .add("deviceId='" + deviceId + "'")
                .add("deviceName='" + deviceName + "'")
                .add("deviceAddress='" + deviceAddress + "'")
                .add("captureTime=" + captureTime)
                .add("captureUrl='" + captureUrl + "'")
                .add("panoramaUrl='" + panoramaUrl + "'")
                .add("age=" + age)
                .add("gender=" + gender)
                .add("maskState=" + maskState)
                .add("glassesState=" + glassesState)
                .add("sunGlassesState=" + sunGlassesState)
                .add("nation=" + nation)
                .toString();
    }

}
