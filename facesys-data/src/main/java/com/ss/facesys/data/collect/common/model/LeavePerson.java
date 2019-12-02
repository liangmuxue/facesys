package com.ss.facesys.data.collect.common.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Transient;

public class LeavePerson {
    public String id;
    public String address;
    public String capturePath;
    public String panoramaPath;
    public Integer state;
    public int leaveDays;
    private Date createdTime;
    private Date updateTime;
    private String peopleId;
    private String peopleName;
    private String remark;
    private String residenceAddress;
    private String credentialNo;
    private String villageId;
    private Integer rowNum;
    private Date lastCaptureTime;
    private String facePitch;
    private String faceYaw;
    private String faceRoll;
    private String facex;
    private String facey;
    private String faceWidth;
    private String faceHeight;
    private List<LeavePersonDetail> detail;
    @Transient
    private String phoneNo;
    @Transient
    private String villageCode;
    @Transient
    private String idCardPic;
    @Transient
    private String stateName;

    public LeavePerson() {
    }

    public LeavePerson(String id, Integer state) {
        this.id = id;
        this.state = state;
    }


    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
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


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public int getLeaveDays() {
        return this.leaveDays;
    }


    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }


    public Date getCreatedTime() {
        return this.createdTime;
    }


    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getPeopleId() {
        return this.peopleId;
    }


    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }


    public String getPeopleName() {
        return this.peopleName;
    }


    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getResidenceAddress() {
        return this.residenceAddress;
    }


    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }


    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    public String getVillageId() {
        return this.villageId;
    }


    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }


    public Integer getRowNum() {
        return this.rowNum;
    }


    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }


    public List<LeavePersonDetail> getDetail() {
        return this.detail;
    }


    public void setDetail(List<LeavePersonDetail> detail) {
        this.detail = detail;
    }


    public Date getLastCaptureTime() {
        return this.lastCaptureTime;
    }


    public void setLastCaptureTime(Date lastCaptureTime) {
        this.lastCaptureTime = lastCaptureTime;
    }


    public String getPhoneNo() {
        return this.phoneNo;
    }


    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getIdCardPic() {
        return this.idCardPic;
    }


    public void setIdCardPic(String idCardPic) {
        this.idCardPic = idCardPic;
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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
