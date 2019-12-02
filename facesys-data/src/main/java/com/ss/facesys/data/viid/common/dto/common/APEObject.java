package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
* VIID-设备对象集合
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class APEObject {
    @JSONField(name = "APEObject")
    @JsonProperty("APEObject")
    private List<APE> apeObject;

    @Override
    public String toString() {
        return "APEObject(apeObject=" + getApeObject() + ")";
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $apeObject = getApeObject();
        return result * 59 + (($apeObject == null) ? 0 : $apeObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof APEObject;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof APEObject)) {
            return false;
        }
        APEObject other = (APEObject) o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$apeObject = getApeObject(), other$apeObject = other.getApeObject();
        return !((this$apeObject == null) ? (other$apeObject != null) : !this$apeObject.equals(other$apeObject));
    }

    public List<APE> getApeObject() {
        return this.apeObject;
    }

    public void setApeObject(List<APE> apeObject) {
        this.apeObject = apeObject;
    }
}
