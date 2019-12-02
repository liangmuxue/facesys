package com.ss.facesys.data.statistic.common.dto;

import java.io.Serializable;
import java.util.Date;

public class CaptureQueryVO implements Serializable {

    private Date captureTimeStart;//抓拍开始时间 时间戳
    private Date captureTimeEnd;//抓拍结束时间 时间戳
    private String[] deviceIds;//设备 id 集合，数组长度 2000 [,,,,]

    public Date getCaptureTimeStart() {
        return captureTimeStart;
    }

    public void setCaptureTimeStart(Date captureTimeStart) {
        this.captureTimeStart = captureTimeStart;
    }

    public Date getCaptureTimeEnd() {
        return captureTimeEnd;
    }

    public void setCaptureTimeEnd(Date captureTimeEnd) {
        this.captureTimeEnd = captureTimeEnd;
    }

    public String[] getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(String[] deviceIds) {
        this.deviceIds = deviceIds;
    }
}
