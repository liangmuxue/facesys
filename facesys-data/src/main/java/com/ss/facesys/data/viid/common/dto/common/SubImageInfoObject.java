package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class SubImageInfoObject {
    @JSONField(name = "SubImageInfoObject")
    @JsonProperty("SubImageInfoObject")
    private List<SubImageInfo> subImageInfoObject;

    @Override
    public String toString() {
        return "SubImageInfoObject(subImageInfoObject=" + getSubImageInfoObject() + ")";
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $subImageInfoObject = getSubImageInfoObject();
        return result * 59 + (($subImageInfoObject == null) ? 0 : $subImageInfoObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof SubImageInfoObject;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SubImageInfoObject)) return false;
        SubImageInfoObject other = (SubImageInfoObject) o;
        if (!other.canEqual(this)) return false;
        Object this$subImageInfoObject = getSubImageInfoObject(), other$subImageInfoObject = other.getSubImageInfoObject();
        return !((this$subImageInfoObject == null) ? (other$subImageInfoObject != null) : !this$subImageInfoObject.equals(other$subImageInfoObject));
    }

    public List<SubImageInfo> getSubImageInfoObject() {
        return this.subImageInfoObject;
    }

    public void setSubImageInfoObject(List<SubImageInfo> subImageInfoObject) {
        this.subImageInfoObject = subImageInfoObject;
    }
}
