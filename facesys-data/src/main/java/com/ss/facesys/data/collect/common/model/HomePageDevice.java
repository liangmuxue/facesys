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
    private String deviceName;
    private Float lon;
    private Float lat;
    private Integer captureTotal;
    private Integer alarmTotal;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
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
