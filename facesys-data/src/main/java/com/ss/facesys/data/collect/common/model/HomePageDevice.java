package com.ss.facesys.data.collect.common.model;

/**
 * com.ss.facesys.data.collect.common.model
 *
 * @author 李爽超 chao
 * @create 2021/03/02
 * @email lishuangchao@ss-cas.com
 **/
public class HomePageDevice {

    private Integer deviceId;
    private Integer captureTotal;
    private Integer alarmTotal;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getCaptureTotal() {
        return captureTotal;
    }

    public void setCaptureTotal(Integer captureTotal) {
        this.captureTotal = captureTotal;
    }

    public Integer getAlarmTotal() {
        return alarmTotal;
    }

    public void setAlarmTotal(Integer alarmTotal) {
        this.alarmTotal = alarmTotal;
    }
}
