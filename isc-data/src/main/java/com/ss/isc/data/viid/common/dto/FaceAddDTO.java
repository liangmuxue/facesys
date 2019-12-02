package com.ss.isc.data.viid.common.dto;

import com.ss.isc.data.viid.common.dto.common.Face;

import java.beans.ConstructorProperties;
import java.util.List;


public class FaceAddDTO {
    private List<Face> faces;
    private String appId;

    @ConstructorProperties({"faces", "appId"})
    public FaceAddDTO(List<Face> faces, String appId) {
        this.faces = faces;
        this.appId = appId;
    }

    public FaceAddDTO() {
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FaceAddDTO)) return false;
        FaceAddDTO other = (FaceAddDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$faces = getFaces(), other$faces = other.getFaces();
        if ((this$faces == null) ? (other$faces != null) : !this$faces.equals(other$faces)) return false;
        Object this$appId = getAppId(), other$appId = other.getAppId();
        return !((this$appId == null) ? (other$appId != null) : !this$appId.equals(other$appId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FaceAddDTO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $faces = getFaces();
        result = result * 59 + (($faces == null) ? 0 : $faces.hashCode());
        Object $appId = getAppId();
        return result * 59 + (($appId == null) ? 0 : $appId.hashCode());
    }

    public String toString() {
        return "FaceAddDTO(faces=" + getFaces() + ", appId=" + getAppId() + ")";
    }

    public List<Face> getFaces() {
        return this.faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
