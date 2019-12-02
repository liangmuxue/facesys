package com.ss.isc.data.collect.common.dto;

import com.ss.isc.data.collect.common.model.AddPersonDetail;

import java.util.Date;
import java.util.List;

public class AddPersonDTO {
    private String id;
    private Integer rowNum;
    private String capturePath;
    private String panoramaPath;
    private Integer days;
    private String deviceNo;
    private String doorCode;
    private String doorName;
    private String villageId;
    private String villageCode;
    private String villageName;
    private String villageAddress;
    private String regionCode;
    private Integer state;
    private String stateName;
    private Integer keyState;
    private Integer excludeFlag;
    private Date createdTime;
    private Date updateTime;
    private String peopleId;
    private String peopleName;
    private String remark;
    private String residenceAddress;
    private String credentialNo;
    private List<AddPersonDetail> detail;
    private int recogState;
    private Date lastCaptureTime;
    private Float threshold;
    private String facePitch;
    private String faceYaw;
    private String faceRoll;
    private String facex;
    private String facey;
    private String faceWidth;
    private String faceHeight;

    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Integer getRowNum() {
        return this.rowNum;
    }


    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
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


    public Integer getDays() {
        return this.days;
    }


    public void setDays(Integer days) {
        this.days = days;
    }


    public String getDeviceNo() {
        return this.deviceNo;
    }


    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }


    public String getDoorCode() {
        return this.doorCode;
    }


    public void setDoorCode(String doorCode) {
        this.doorCode = doorCode;
    }


    public String getDoorName() {
        return this.doorName;
    }


    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }


    public String getVillageId() {
        return this.villageId;
    }


    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public String getVillageAddress() {
        return this.villageAddress;
    }


    public void setVillageAddress(String villageAddress) {
        this.villageAddress = villageAddress;
    }


    public String getRegionCode() {
        return this.regionCode;
    }


    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public Integer getKeyState() {
        return this.keyState;
    }


    public void setKeyState(Integer keyState) {
        this.keyState = keyState;
    }


    public Integer getExcludeFlag() {
        return this.excludeFlag;
    }


    public void setExcludeFlag(Integer excludeFlag) {
        this.excludeFlag = excludeFlag;
    }


    public Date getCreatedTime() {
        return this.createdTime;
    }


    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public String getPeopleId() {
        return this.peopleId;
    }


    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }


    public String getPeopleName() {
        if (null == this.peopleId || this.peopleId.length() == 0) {
            return "--";
        }

        return this.peopleName;
    }


    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }


    public String getResidenceAddress() {
        if (null == this.peopleId || this.peopleId.length() == 0) {
            return "--";
        }

        return this.residenceAddress;
    }


    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }


    public String getCredentialNo() {
        if (null == this.peopleId || this.peopleId.length() == 0) {
            return "--";
        }

        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public List<AddPersonDetail> getDetail() {
        return this.detail;
    }


    public void setDetail(List<AddPersonDetail> detail) {
        this.detail = detail;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public Date getLastCaptureTime() {
        return this.lastCaptureTime;
    }


    public void setLastCaptureTime(Date lastCaptureTime) {
        this.lastCaptureTime = lastCaptureTime;
    }


    public Float getThreshold() {
        return this.threshold;
    }


    public void setThreshold(Float threshold) {
        this.threshold = threshold;
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


    public int getRecogState() {
        return this.recogState;
    }


    public void setRecogState(int recogState) {
        this.recogState = recogState;
    }


    public String toString() {
        return "AddPersonDTO [id=" + this.id + ", capturePath=" + this.capturePath + ", panoramaPath=" + this.panoramaPath + ", days=" + this.days + ", deviceNo=" + this.deviceNo + ", doorCode=" + this.doorCode + ", doorName=" + this.doorName + ", villageId=" + this.villageId + ", villageCode=" + this.villageCode + ", villageName=" + this.villageName + ", regionCode=" + this.regionCode + ", state=" + this.state + ", keyState=" + this.keyState + ", excludeFlag=" + this.excludeFlag + ", createdTime=" + this.createdTime + ", updateTime=" + this.updateTime + ", peopleId=" + this.peopleId + ", peopleName=" + this.peopleName + ", remark=" + this.remark + ", residenceAddress=" + this.residenceAddress + ", credentialNo=" + this.credentialNo + ", detail=" + this.detail + "]";
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
