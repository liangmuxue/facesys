package com.ss.facesys.data.access.common.web;

import java.util.List;


public class TaskReceive {

    private List<String> deviceIds;
    private String name = "";
    private float similaryThr = 0.8F;
    private int minClusterNum = 2;
    private String remark = "";

    private long beginTime;
    private long endTime;

    public List<String> getDeviceIds() {
        return this.deviceIds;
    }


    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public float getSimilaryThr() {
        return this.similaryThr;
    }


    public void setSimilaryThr(float similaryThr) {
        this.similaryThr = similaryThr;
    }


    public int getMinClusterNum() {
        return this.minClusterNum;
    }


    public void setMinClusterNum(int minClusterNum) {
        this.minClusterNum = minClusterNum;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public long getBeginTime() {
        return this.beginTime;
    }


    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }


    public long getEndTime() {
        return this.endTime;
    }


    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

}
