package com.ss.isc.data.collect.common.dto;

import java.util.Date;

public class LongtimeStayDTO {
    private String id;
    private String deviceNo;
    private String capturePath;
    private String panoramaPath;
    private Integer state;
    private Date createTime;
    private Date updateTime;
    private String peopleId;
    private String villageId;
    private Integer stayTime;
    private String remark;
    private Integer rowNum;
    private Date lastCaptureTime;
    private String facePitch;
    private String faceYaw;
    private String faceRoll;
    private String facex;
    private String facey;
    private String faceWidth;
    private String faceHeight;
    private String villageName;
    private String cameraName;
    private String stateName;

    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public Integer getStayTime() {
        return this.stayTime;
    }


    public void setStayTime(Integer stayTime) {
        this.stayTime = stayTime;
    }


    public String getCapturePath() {
        return this.capturePath;
    }


    public void setCapturePath(String capturePath) {
        this.capturePath = capturePath;
    }


    public String getPanoramaPath() {
        return this.panoramaPath;
    }


    public void setPanoramaPath(String panoramaPath) {
        this.panoramaPath = panoramaPath;
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


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Integer getRowNum() {
        return this.rowNum;
    }


    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public String getCameraName() {
        return this.cameraName;
    }


    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }


    public Date getLastCaptureTime() {
        return this.lastCaptureTime;
    }


    public void setLastCaptureTime(Date lastCaptureTime) {
        this.lastCaptureTime = lastCaptureTime;
    }


    public String getFacePitch() {
        return this.facePitch;
    }


    public void setFacePitch(String facePitch) {
        this.facePitch = facePitch;
    }


    public String getFaceYaw() {
        return this.faceYaw;
    }


    public void setFaceYaw(String faceYaw) {
        this.faceYaw = faceYaw;
    }


    public String getFaceRoll() {
        return this.faceRoll;
    }


    public void setFaceRoll(String faceRoll) {
        this.faceRoll = faceRoll;
    }


    public String getFacex() {
        return this.facex;
    }


    public void setFacex(String facex) {
        this.facex = facex;
    }


    public String getFacey() {
        return this.facey;
    }


    public void setFacey(String facey) {
        this.facey = facey;
    }


    public String getFaceWidth() {
        return this.faceWidth;
    }


    public void setFaceWidth(String faceWidth) {
        this.faceWidth = faceWidth;
    }


    public String getFaceHeight() {
        return this.faceHeight;
    }


    public void setFaceHeight(String faceHeight) {
        this.faceHeight = faceHeight;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
