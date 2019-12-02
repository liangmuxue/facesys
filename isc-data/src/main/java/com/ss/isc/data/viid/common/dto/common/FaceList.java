package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;


public class FaceList {
    @JSONField(name = "FaceListObject")
    @JsonProperty("FaceListObject")
    private FaceObject faceListObject;

    public String toString() {
        return "FaceList(faceListObject=" + getFaceListObject() + ")";
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $faceListObject = getFaceListObject();
        return result * 59 + (($faceListObject == null) ? 0 : $faceListObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof FaceList;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FaceList)) return false;
        FaceList other = (FaceList) o;
        if (!other.canEqual(this)) return false;
        Object this$faceListObject = getFaceListObject(), other$faceListObject = other.getFaceListObject();
        return !((this$faceListObject == null) ? (other$faceListObject != null) : !this$faceListObject.equals(other$faceListObject));
    }

    public FaceObject getFaceListObject() {
        return this.faceListObject;
    }

    public void setFaceListObject(FaceObject faceListObject) {
        this.faceListObject = faceListObject;
    }
}
