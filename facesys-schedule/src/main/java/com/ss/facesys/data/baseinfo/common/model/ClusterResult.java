package com.ss.facesys.data.baseinfo.common.model;


public class ClusterResult {

    private String id;
    private String taskId;
    private String groupLabel;
    private String count;
    private String refFaceId;
    private String captureUrl;
    private String remark;
    private String createdTime;

    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getTaskId() {
        return this.taskId;
    }


    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    public String getGroupLabel() {
        return this.groupLabel;
    }


    public void setGroupLabel(String groupLabel) {
        this.groupLabel = groupLabel;
    }


    public String getCount() {
        return this.count;
    }


    public void setCount(String count) {
        this.count = count;
    }


    public String getRefFaceId() {
        return this.refFaceId;
    }


    public void setRefFaceId(String refFaceId) {
        this.refFaceId = refFaceId;
    }


    public String getCaptureUrl() {
        return this.captureUrl;
    }


    public void setCaptureUrl(String captureUrl) {
        this.captureUrl = captureUrl;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getCreatedTime() {
        return this.createdTime;
    }


    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
