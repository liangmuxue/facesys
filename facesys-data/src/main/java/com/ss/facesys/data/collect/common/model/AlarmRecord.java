package com.ss.facesys.data.collect.common.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * AlarmRecord 预警记录
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_alarm_record")
public class AlarmRecord {

    @Id
    private Integer id;
    private Integer alarmId;
    private String alarmName;
    private Integer tipFlag;
    private Integer voiceFlag;
    private String colorCode;
    private String voiceUrl;
    private Integer monitorId;
    private String monitorCode;
    private String monitorName;
    private Integer monitorType;
    private Integer monitorProperty;
    private Integer topn;
    private Float alarmScore;
    private String captureId;
    private Long captureTime;
    private Long alarmTime;
    private Integer deviceId;
    private String deviceNo;
    private String deviceCode;
    private Integer deviceType;
    private String deviceName;
    private String deviceAddress;
    private String lng;
    private String lat;
    private String panoramaId;
    private String captureUrlFull;
    private String panoramaUrlFull;
    private Float qualityScore;
    private Integer age;
    private Integer ageGroup;
    private String ageGroupName;
    private Integer gender;
    private Integer maskState;
    private Integer glassesState;
    private Integer sunGlassesState;
    private Integer minority;
    private String snId;
    private String pitch;
    private String yaw;
    private String roll;
    private String x;
    private String y;
    private String width;
    private String height;
    private String remark;
    private Integer state;
    private Long createTime;
    private String birthday;
    private String cardNation;
    private String cardId;
    private String name;
    private Integer cardGender;
    private String registerUrl;
    private String regId;
    private String regdb;
    private String regdbType;
    private Integer regdbId;
    private String regdbName;
    private String monitorUserName;
    private float score;

    @Transient
    private Integer cnt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public String getMonitorName() {
        return this.monitorName;
    }


    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }


    public Integer getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(Integer monitorType) {
        this.monitorType = monitorType;
    }

    public Float getAlarmScore() {
        return this.alarmScore;
    }


    public void setAlarmScore(Float alarmScore) {
        this.alarmScore = alarmScore;
    }


    public String getCaptureId() {
        return this.captureId;
    }


    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }


    public Long getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Long captureTime) {
        this.captureTime = captureTime;
    }

    public Long getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return this.deviceCode;
    }


    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }


    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
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


    public String getPanoramaId() {
        return this.panoramaId;
    }


    public void setPanoramaId(String panoramaId) {
        this.panoramaId = panoramaId;
    }


    public String getCaptureUrlFull() {
        return this.captureUrlFull;
    }


    public void setCaptureUrlFull(String captureUrlFull) {
        this.captureUrlFull = captureUrlFull;
    }


    public String getPanoramaUrlFull() {
        return this.panoramaUrlFull;
    }


    public void setPanoramaUrlFull(String panoramaUrlFull) {
        this.panoramaUrlFull = panoramaUrlFull;
    }


    public Float getQualityScore() {
        return this.qualityScore;
    }


    public void setQualityScore(Float qualityScore) {
        this.qualityScore = qualityScore;
    }


    public String getSnId() {
        return this.snId;
    }


    public void setSnId(String snId) {
        this.snId = snId;
    }


    public String getPitch() {
        return this.pitch;
    }


    public void setPitch(String pitch) {
        this.pitch = pitch;
    }


    public String getYaw() {
        return this.yaw;
    }


    public void setYaw(String yaw) {
        this.yaw = yaw;
    }


    public String getRoll() {
        return this.roll;
    }


    public void setRoll(String roll) {
        this.roll = roll;
    }


    public String getX() {
        return this.x;
    }


    public void setX(String x) {
        this.x = x;
    }


    public String getY() {
        return this.y;
    }


    public void setY(String y) {
        this.y = y;
    }


    public String getWidth() {
        return this.width;
    }


    public void setWidth(String width) {
        this.width = width;
    }


    public String getHeight() {
        return this.height;
    }


    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getMonitorProperty() {
        return monitorProperty;
    }

    public void setMonitorProperty(Integer monitorProperty) {
        this.monitorProperty = monitorProperty;
    }

    public Integer getTopn() {
        return topn;
    }

    public void setTopn(Integer topn) {
        this.topn = topn;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardNation() {
        return cardNation;
    }

    public void setCardNation(String cardNation) {
        this.cardNation = cardNation;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCardGender() {
        return cardGender;
    }

    public void setCardGender(Integer cardGender) {
        this.cardGender = cardGender;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegdb() {
        return regdb;
    }

    public void setRegdb(String regdb) {
        this.regdb = regdb;
    }

    public Integer getRegdbId() {
        return regdbId;
    }

    public void setRegdbId(Integer regdbId) {
        this.regdbId = regdbId;
    }

    public String getRegdbName() {
        return regdbName;
    }

    public void setRegdbName(String regdbName) {
        this.regdbName = regdbName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Integer getTipFlag() {
        return tipFlag;
    }

    public void setTipFlag(Integer tipFlag) {
        this.tipFlag = tipFlag;
    }

    public Integer getVoiceFlag() {
        return voiceFlag;
    }

    public void setVoiceFlag(Integer voiceFlag) {
        this.voiceFlag = voiceFlag;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRegdbType() {
        return regdbType;
    }

    public void setRegdbType(String regdbType) {
        this.regdbType = regdbType;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(Integer ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getMonitorUserName() {
        return monitorUserName;
    }

    public void setMonitorUserName(String monitorUserName) {
        this.monitorUserName = monitorUserName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSunGlassesState() {
        return sunGlassesState;
    }

    public void setSunGlassesState(Integer sunGlassesState) {
        this.sunGlassesState = sunGlassesState;
    }

    public Integer getMinority() {
        return minority;
    }

    public void setMinority(Integer minority) {
        this.minority = minority;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getAgeGroupName() {
        return ageGroupName;
    }

    public void setAgeGroupName(String ageGroupName) {
        this.ageGroupName = ageGroupName;
    }
}
