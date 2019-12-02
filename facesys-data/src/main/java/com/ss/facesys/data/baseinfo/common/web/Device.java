package com.ss.facesys.data.baseinfo.common.web;

/**
 * Device 设备信息
 *
 * @author FrancisYs
 * @date 2019/8/21
 * @email yaoshuai@ss-cas.com
 */
public class Device {

    private String deviceId;
    private short deviceType;

    public String getDeviceId() {
        return this.deviceId;
    }


    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    public short getDeviceType() {
        return this.deviceType;
    }


    public void setDeviceType(short deviceType) {
        this.deviceType = deviceType;
    }

}
