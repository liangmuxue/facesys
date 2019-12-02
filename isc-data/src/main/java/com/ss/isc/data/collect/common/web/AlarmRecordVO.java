package com.ss.isc.data.collect.common.web;

import java.util.Date;

/**
 * AlarmRecordVO 预警记录视图对象
 *
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
public class AlarmRecordVO {

    private Integer id;
    private String captureUrlFull;
    private String cameraName;
    private String villageCode;
    private Date alarmTime;
    private Float simScore;
    private String faceUrlFull;
    private String facedbName;
    private String faceName;
    private Integer age;
    private Integer state;

    /** 状态中文描述 */
    private String stateName;

    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getCaptureUrlFull() {
        return this.captureUrlFull;
    }


    public void setCaptureUrlFull(String captureUrlFull) {
        this.captureUrlFull = captureUrlFull;
    }


    public String getCameraName() {
        return this.cameraName;
    }


    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }


    public Date getAlarmTime() {
        return this.alarmTime;
    }


    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }


    public Float getSimScore() {
        return this.simScore;
    }


    public void setSimScore(Float simScore) {
        this.simScore = simScore;
    }


    public String getFaceUrlFull() {
        return this.faceUrlFull;
    }


    public void setFaceUrlFull(String faceUrlFull) {
        this.faceUrlFull = faceUrlFull;
    }


    public String getFacedbName() {
        return this.facedbName;
    }


    public void setFacedbName(String facedbName) {
        this.facedbName = facedbName;
    }


    public String getFaceName() {
        return this.faceName;
    }


    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }


    public Integer getAge() {
        return this.age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "AlarmRecordVO [id=" + this.id + ", captureUrlFull=" + this.captureUrlFull + ", cameraName=" + this.cameraName + ", villageCode=" + this.villageCode + ", alarmTime=" + this.alarmTime + ", simScore=" + this.simScore + ", faceUrlFull=" + this.faceUrlFull + ", facedbName=" + this.facedbName + ", faceName=" + this.faceName + ", age=" + this.age + ", state=" + this.state + "]";
    }

}
