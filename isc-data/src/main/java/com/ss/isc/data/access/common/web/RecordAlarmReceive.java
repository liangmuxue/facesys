package com.ss.isc.data.access.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class RecordAlarmReceive {

    private String alarmTopId;
    private String alarmId;
    private Float simScore;
    private String faceId;
    private String facedbId;
    private String facedbName;
    private String faceCardType;
    private String faceCardId;
    private String faceName;
    private String faceBirthday;
    private String faceAddress;
    private Integer faceGender;
    private String faceCardOrg;
    private String faceUrlFull;
    private Integer topSeq;
    private String remark;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date createTime;
    private String faceExtField1;
    private String faceExtField2;
    private String faceExtField3;
    private String facedbExtField1;
    private String facedbExtField2;
    private String facedbExtField3;

    public String getAlarmTopId() {
        return this.alarmTopId;
    }


    public void setAlarmTopId(String alarmTopId) {
        this.alarmTopId = alarmTopId;
    }


    public String getAlarmId() {
        return this.alarmId;
    }


    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }


    public Float getSimScore() {
        return this.simScore;
    }


    public void setSimScore(Float simScore) {
        this.simScore = simScore;
    }


    public String getFaceId() {
        return this.faceId;
    }


    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }


    public String getFacedbId() {
        return this.facedbId;
    }


    public void setFacedbId(String facedbId) {
        this.facedbId = facedbId;
    }


    public String getFacedbName() {
        return this.facedbName;
    }


    public void setFacedbName(String facedbName) {
        this.facedbName = facedbName;
    }


    public String getFaceCardType() {
        return this.faceCardType;
    }


    public void setFaceCardType(String faceCardType) {
        this.faceCardType = faceCardType;
    }


    public String getFaceCardId() {
        return this.faceCardId;
    }


    public void setFaceCardId(String faceCardId) {
        this.faceCardId = faceCardId;
    }


    public String getFaceName() {
        return this.faceName;
    }


    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }


    public String getFaceBirthday() {
        return this.faceBirthday;
    }


    public void setFaceBirthday(String faceBirthday) {
        this.faceBirthday = faceBirthday;
    }


    public String getFaceAddress() {
        return this.faceAddress;
    }


    public void setFaceAddress(String faceAddress) {
        this.faceAddress = faceAddress;
    }


    public Integer getFaceGender() {
        return this.faceGender;
    }


    public void setFaceGender(Integer faceGender) {
        this.faceGender = faceGender;
    }


    public String getFaceCardOrg() {
        return this.faceCardOrg;
    }


    public void setFaceCardOrg(String faceCardOrg) {
        this.faceCardOrg = faceCardOrg;
    }


    public String getFaceUrlFull() {
        return this.faceUrlFull;
    }


    public void setFaceUrlFull(String faceUrlFull) {
        this.faceUrlFull = faceUrlFull;
    }


    public Integer getTopSeq() {
        return this.topSeq;
    }


    public void setTopSeq(Integer topSeq) {
        this.topSeq = topSeq;
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


    public String getFaceExtField1() {
        return this.faceExtField1;
    }


    public void setFaceExtField1(String faceExtField1) {
        this.faceExtField1 = faceExtField1;
    }


    public String getFaceExtField2() {
        return this.faceExtField2;
    }


    public void setFaceExtField2(String faceExtField2) {
        this.faceExtField2 = faceExtField2;
    }


    public String getFaceExtField3() {
        return this.faceExtField3;
    }


    public void setFaceExtField3(String faceExtField3) {
        this.faceExtField3 = faceExtField3;
    }


    public String getFacedbExtField1() {
        return this.facedbExtField1;
    }


    public void setFacedbExtField1(String facedbExtField1) {
        this.facedbExtField1 = facedbExtField1;
    }


    public String getFacedbExtField2() {
        return this.facedbExtField2;
    }


    public void setFacedbExtField2(String facedbExtField2) {
        this.facedbExtField2 = facedbExtField2;
    }


    public String getFacedbExtField3() {
        return this.facedbExtField3;
    }


    public void setFacedbExtField3(String facedbExtField3) {
        this.facedbExtField3 = facedbExtField3;
    }

}
