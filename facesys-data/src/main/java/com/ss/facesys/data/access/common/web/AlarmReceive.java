package com.ss.facesys.data.access.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.ss.valide.APIAddGroup;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


public class AlarmReceive {

    @NotBlank(message = "{alarmReceive.alarmId.empty}", groups = {APIAddGroup.class})
    private String alarmId;
    private String monitorId;
    private String monitorName;
    private String monitorType;
    private String monitorProperty;
    private Integer topn;
    private Float alarmScore;
    @NotBlank(message = "{alarmReceive.captureId.empty}", groups = {APIAddGroup.class})
    private String captureId;
    @NotNull(message = "{alarmReceive.captureTime.empty}", groups = {APIAddGroup.class})
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date captureTime;
    @NotNull(message = "{PalarmReceive.alarmTime.empty}", groups = {APIAddGroup.class})
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date alarmTime;
    @NotBlank(message = "{alarmReceive.deviceId.empty}", groups = {APIAddGroup.class})
    private String deviceId;
    private String deviceCode;
    private Integer deviceType;
    private String deviceName;
    private String deviceAddress;
    private String lng;
    private String lat;
    private String devExtField1;
    private String devExtField2;
    private String devExtField3;
    private String monExtField1;
    private String monExtField2;
    private String monExtField3;
    private String panoramaId;
    @NotBlank(message = "{alarmReceive.captureUrlFull.empty}", groups = {APIAddGroup.class})
    private String captureUrlFull;
    private String panoramaUrlFull;
    private Float qualityScore;
    private String age;
    private String ageGroup;
    private String gender;
    private Integer race;
    private Float faceTotalScore;
    private Float lightScore;
    private Float maskScore;
    private Integer maskState;
    private Float clarityScore;
    private Float glassesScore;
    private Integer glassesState;
    private Float mouthScore;
    private Float faceClarityScore;
    private Float sunGlassesScore;
    private Integer sunGlassesState;
    private Float nation;
    private Float pitch;
    private Float yaw;
    private Float roll;
    private Float x;
    private Float y;
    private Float width;
    private Float height;
    private Float printfaceScore;
    private String fromSystem;
    private String remark;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date createTime;
    private String birthday;
    private String cardNation;
    private String cardId;
    private String name;
    private Integer cardGender;
    private String address;
    private String cardOrg;
    private String validdateStart;
    private String validdateEnd;
    private Float score;
    private String cardUrlFull;
    private Integer authResult;
    private String snId;
    @NotEmpty(message = "{alarmReceive.recordAlarmTopns.empty}", groups = {APIAddGroup.class})
    private List<RecordAlarmReceive> recordAlarmTopns;

    public String getAlarmId() {
        return this.alarmId;
    }


    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }


    public String getMonitorId() {
        return this.monitorId;
    }


    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }


    public String getMonitorName() {
        return this.monitorName;
    }


    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }


    public String getMonitorType() {
        return this.monitorType;
    }


    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }


    public String getMonitorProperty() {
        return this.monitorProperty;
    }


    public void setMonitorProperty(String monitorProperty) {
        this.monitorProperty = monitorProperty;
    }


    public Integer getTopn() {
        return this.topn;
    }


    public void setTopn(Integer topn) {
        this.topn = topn;
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


    public Date getCaptureTime() {
        return this.captureTime;
    }


    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }


    public Date getAlarmTime() {
        return this.alarmTime;
    }


    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }


    public String getDeviceId() {
        return this.deviceId;
    }


    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    public String getDeviceCode() {
        return this.deviceCode;
    }


    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }


    public Integer getDeviceType() {
        return this.deviceType;
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


    public String getDevExtField1() {
        return this.devExtField1;
    }


    public void setDevExtField1(String devExtField1) {
        this.devExtField1 = devExtField1;
    }


    public String getDevExtField2() {
        return this.devExtField2;
    }


    public void setDevExtField2(String devExtField2) {
        this.devExtField2 = devExtField2;
    }


    public String getDevExtField3() {
        return this.devExtField3;
    }


    public void setDevExtField3(String devExtField3) {
        this.devExtField3 = devExtField3;
    }


    public String getMonExtField1() {
        return this.monExtField1;
    }


    public void setMonExtField1(String monExtField1) {
        this.monExtField1 = monExtField1;
    }


    public String getMonExtField2() {
        return this.monExtField2;
    }


    public void setMonExtField2(String monExtField2) {
        this.monExtField2 = monExtField2;
    }


    public String getMonExtField3() {
        return this.monExtField3;
    }


    public void setMonExtField3(String monExtField3) {
        this.monExtField3 = monExtField3;
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


    public String getAge() {
        return this.age;
    }


    public void setAge(String age) {
        this.age = age;
    }


    public String getAgeGroup() {
        return this.ageGroup;
    }


    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }


    public String getGender() {
        return this.gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public Integer getRace() {
        return this.race;
    }


    public void setRace(Integer race) {
        this.race = race;
    }


    public Float getFaceTotalScore() {
        return this.faceTotalScore;
    }


    public void setFaceTotalScore(Float faceTotalScore) {
        this.faceTotalScore = faceTotalScore;
    }


    public Float getLightScore() {
        return this.lightScore;
    }


    public void setLightScore(Float lightScore) {
        this.lightScore = lightScore;
    }


    public Float getMaskScore() {
        return this.maskScore;
    }


    public void setMaskScore(Float maskScore) {
        this.maskScore = maskScore;
    }


    public Integer getMaskState() {
        return this.maskState;
    }


    public void setMaskState(Integer maskState) {
        this.maskState = maskState;
    }


    public Float getClarityScore() {
        return this.clarityScore;
    }


    public void setClarityScore(Float clarityScore) {
        this.clarityScore = clarityScore;
    }


    public Float getGlassesScore() {
        return this.glassesScore;
    }


    public void setGlassesScore(Float glassesScore) {
        this.glassesScore = glassesScore;
    }


    public Integer getGlassesState() {
        return this.glassesState;
    }


    public void setGlassesState(Integer glassesState) {
        this.glassesState = glassesState;
    }


    public Float getMouthScore() {
        return this.mouthScore;
    }


    public void setMouthScore(Float mouthScore) {
        this.mouthScore = mouthScore;
    }


    public Float getFaceClarityScore() {
        return this.faceClarityScore;
    }


    public void setFaceClarityScore(Float faceClarityScore) {
        this.faceClarityScore = faceClarityScore;
    }


    public Float getSunGlassesScore() {
        return this.sunGlassesScore;
    }


    public void setSunGlassesScore(Float sunGlassesScore) {
        this.sunGlassesScore = sunGlassesScore;
    }


    public Integer getSunGlassesState() {
        return this.sunGlassesState;
    }


    public void setSunGlassesState(Integer sunGlassesState) {
        this.sunGlassesState = sunGlassesState;
    }


    public Float getNation() {
        return this.nation;
    }


    public void setNation(Float nation) {
        this.nation = nation;
    }


    public Float getPitch() {
        return this.pitch;
    }


    public void setPitch(Float pitch) {
        this.pitch = pitch;
    }


    public Float getYaw() {
        return this.yaw;
    }


    public void setYaw(Float yaw) {
        this.yaw = yaw;
    }


    public Float getRoll() {
        return this.roll;
    }


    public void setRoll(Float roll) {
        this.roll = roll;
    }


    public Float getX() {
        return this.x;
    }


    public void setX(Float x) {
        this.x = x;
    }


    public Float getY() {
        return this.y;
    }


    public void setY(Float y) {
        this.y = y;
    }


    public Float getWidth() {
        return this.width;
    }


    public void setWidth(Float width) {
        this.width = width;
    }


    public Float getHeight() {
        return this.height;
    }


    public void setHeight(Float height) {
        this.height = height;
    }


    public Float getPrintfaceScore() {
        return this.printfaceScore;
    }


    public void setPrintfaceScore(Float printfaceScore) {
        this.printfaceScore = printfaceScore;
    }


    public String getFromSystem() {
        return this.fromSystem;
    }


    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getBirthday() {
        return this.birthday;
    }


    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getCardNation() {
        return this.cardNation;
    }


    public void setCardNation(String cardNation) {
        this.cardNation = cardNation;
    }


    public String getCardId() {
        return this.cardId;
    }


    public void setCardId(String cardId) {
        this.cardId = cardId;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getCardGender() {
        return this.cardGender;
    }


    public void setCardGender(Integer cardGender) {
        this.cardGender = cardGender;
    }


    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getCardOrg() {
        return this.cardOrg;
    }


    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg;
    }


    public String getValiddateStart() {
        return this.validdateStart;
    }


    public void setValiddateStart(String validdateStart) {
        this.validdateStart = validdateStart;
    }


    public String getValiddateEnd() {
        return this.validdateEnd;
    }


    public void setValiddateEnd(String validdateEnd) {
        this.validdateEnd = validdateEnd;
    }


    public Float getScore() {
        return this.score;
    }


    public void setScore(Float score) {
        this.score = score;
    }


    public String getCardUrlFull() {
        return this.cardUrlFull;
    }


    public void setCardUrlFull(String cardUrlFull) {
        this.cardUrlFull = cardUrlFull;
    }


    public Integer getAuthResult() {
        return this.authResult;
    }


    public void setAuthResult(Integer authResult) {
        this.authResult = authResult;
    }


    public String getSnId() {
        return this.snId;
    }


    public void setSnId(String snId) {
        this.snId = snId;
    }


    public List<RecordAlarmReceive> getRecordAlarmTopns() {
        return this.recordAlarmTopns;
    }


    public void setRecordAlarmTopns(List<RecordAlarmReceive> recordAlarmTopns) {
        this.recordAlarmTopns = recordAlarmTopns;
    }

}
