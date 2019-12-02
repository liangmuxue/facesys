package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ss.isc.data.viid.common.dto.AbstractVIIDNotifyDataDTO;
/**
* VIID-设备和机动车对象集合
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class SubscribeNotification extends AbstractVIIDNotifyDataDTO
{
    @JSONField(name = "DeviceList")
    @JsonProperty("DeviceList")
    private APEObject deviceList;
    @JSONField(name = "MotorVehicleObjectList")
    @JsonProperty("MotorVehicleObjectList")
    private MotorVehicleObject motorVehicleList;

    public APEObject getDeviceList() {
        return this.deviceList;
    }

    public void setDeviceList(final APEObject deviceList) {
        this.deviceList = deviceList;
    }

    public MotorVehicleObject getMotorVehicleList() {
        return motorVehicleList;
    }

    public void setMotorVehicleList(MotorVehicleObject motorVehicleList) {
        this.motorVehicleList = motorVehicleList;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SubscribeNotification)) {
            return false;
        }
        final SubscribeNotification other = (SubscribeNotification)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$deviceList = this.getDeviceList();
        final Object other$deviceList = other.getDeviceList();
        Label_0065: {
            if (this$deviceList == null) {
                if (other$deviceList == null) {
                    break Label_0065;
                }
            }
            else if (this$deviceList.equals(other$deviceList)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$motorVehicleList = this.getMotorVehicleList();
        final Object other$motorVehicleList = other.getMotorVehicleList();
        if (this$motorVehicleList == null) {
            if (other$motorVehicleList == null) {
                return true;
            }
        }
        else if (this$motorVehicleList.equals(other$motorVehicleList)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof SubscribeNotification;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $deviceList = this.getDeviceList();
        result = result * 59 + (($deviceList == null) ? 0 : $deviceList.hashCode());
        final Object $motorVehicleList = this.getMotorVehicleList();
        result = result * 59 + (($motorVehicleList == null) ? 0 : $motorVehicleList.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SubscribeNotification(deviceList=" + this.getDeviceList() + ", motorVehicleList=" + this.getMotorVehicleList() + ")";
    }
}
