package com.ss.facesys.data.access.common.web;

import java.util.Date;


public class CaptureStatistics {

    private Date captureTimeStart;
    private Date captureTimeEnd;
    private String[] deviceIds;

    public Date getCaptureTimeStart() {
        return this.captureTimeStart;
    }


    public void setCaptureTimeStart(Date captureTimeStart) {
        this.captureTimeStart = captureTimeStart;
    }


    public Date getCaptureTimeEnd() {
        return this.captureTimeEnd;
    }


    public void setCaptureTimeEnd(Date captureTimeEnd) {
        this.captureTimeEnd = captureTimeEnd;
    }


    public String[] getDeviceIds() {
        return this.deviceIds;
    }


    public void setDeviceIds(String[] deviceIds) {
        this.deviceIds = deviceIds;
    }

}
