package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * VIID-机动车对象集合
 * @author 李爽超 chao
 * @create 2019/10/29
 * @email lishuangchao@ss-cas.com
 **/
public class MotorVehicleObject {

    @JSONField(name = "MotorVehicleObject")
    @JsonProperty("MotorVehicleObject")
    private List<MotorVehicle> motorVehicleObject;

    @Override
    public String toString() {
        return "MotorVehicleObject(motorVehicleObject=" + getMotorVehicleObject() + ")";
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $motorVehicleObject = getMotorVehicleObject();
        return result * 59 + (($motorVehicleObject == null) ? 0 : $motorVehicleObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof MotorVehicleObject;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MotorVehicleObject)) return false;
        MotorVehicleObject other = (MotorVehicleObject) o;
        if (!other.canEqual(this)) return false;
        Object this$motorVehicleObject = getMotorVehicleObject(), other$motorVehicleObject = other.getMotorVehicleObject();
        return !((this$motorVehicleObject == null) ? (other$motorVehicleObject != null) : !this$motorVehicleObject.equals(other$motorVehicleObject));
    }

    public List<MotorVehicle> getMotorVehicleObject() {
        return motorVehicleObject;
    }

    public void setMotorVehicleObject(List<MotorVehicle> motorVehicleObject) {
        this.motorVehicleObject = motorVehicleObject;
    }
}
