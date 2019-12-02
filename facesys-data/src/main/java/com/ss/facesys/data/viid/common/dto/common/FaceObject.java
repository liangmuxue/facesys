package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class FaceObject {
    @JSONField(name = "FaceObject")
    @JsonProperty("FaceObject")
    private List<Face> faceObject;

    public String toString() {
        return "FaceObject(faceObject=" + getFaceObject() + ")";
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $faceObject = getFaceObject();
        return result * 59 + (($faceObject == null) ? 0 : $faceObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof FaceObject;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FaceObject)) return false;
        FaceObject other = (FaceObject) o;
        if (!other.canEqual(this)) return false;
        Object this$faceObject = getFaceObject(), other$faceObject = other.getFaceObject();
        return !((this$faceObject == null) ? (other$faceObject != null) : !this$faceObject.equals(other$faceObject));
    }

    public List<Face> getFaceObject() {
        return this.faceObject;
    }

    public void setFaceObject(List<Face> faceObject) {
        this.faceObject = faceObject;
    }
}
