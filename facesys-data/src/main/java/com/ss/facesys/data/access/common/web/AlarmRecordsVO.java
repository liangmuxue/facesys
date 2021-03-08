package com.ss.facesys.data.access.common.web;

public class AlarmRecordsVO {

    private Integer id;
    private String userId;
    private String name;
    private String cardId;
    private String facedbIds;
    private String cameraIds;
    private String personcardDeviceIds;
    private Integer monitorId;
    private Integer alarmId;
    private Integer state;
    private Long startTime;
    private Long endTime;
    private String monitorUserName;
    private String remark;
    private Integer monitorType;
    private Integer currentPage;
    private Integer pageSize;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getFacedbIds() {
        return facedbIds;
    }

    public void setFacedbIds(String facedbIds) {
        this.facedbIds = facedbIds;
    }

    public String getCameraIds() {
        return cameraIds;
    }

    public void setCameraIds(String cameraIds) {
        this.cameraIds = cameraIds;
    }

    public String getPersoncardDeviceIds() {
        return personcardDeviceIds;
    }

    public void setPersoncardDeviceIds(String personcardDeviceIds) {
        this.personcardDeviceIds = personcardDeviceIds;
    }

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getMonitorUserName() {
        return monitorUserName;
    }

    public void setMonitorUserName(String monitorUserName) {
        this.monitorUserName = monitorUserName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(Integer monitorType) {
        this.monitorType = monitorType;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
