package com.ss.facesys.data.viid.common.dto;

import com.ss.facesys.data.viid.common.dto.ext.DispositionExt;

import java.beans.ConstructorProperties;
import java.util.List;

public class DispositionsAddDTO {
    private List<DispositionExt> dispositions;
    private String appId;
    private String systemId;

    @ConstructorProperties({"dispositions", "appId", "systemId"})
    public DispositionsAddDTO(List<DispositionExt> dispositions, String appId, String systemId) {
        this.dispositions = dispositions;
        this.appId = appId;
        this.systemId = systemId;
    }

    public DispositionsAddDTO() {
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DispositionsAddDTO)) return false;
        DispositionsAddDTO other = (DispositionsAddDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$dispositions = getDispositions(), other$dispositions = other.getDispositions();
        if ((this$dispositions == null) ? (other$dispositions != null) : !this$dispositions.equals(other$dispositions))
            return false;
        Object this$appId = getAppId(), other$appId = other.getAppId();
        if ((this$appId == null) ? (other$appId != null) : !this$appId.equals(other$appId)) return false;
        Object this$systemId = getSystemId(), other$systemId = other.getSystemId();
        return !((this$systemId == null) ? (other$systemId != null) : !this$systemId.equals(other$systemId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof DispositionsAddDTO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $dispositions = getDispositions();
        result = result * 59 + (($dispositions == null) ? 0 : $dispositions.hashCode());
        Object $appId = getAppId();
        result = result * 59 + (($appId == null) ? 0 : $appId.hashCode());
        Object $systemId = getSystemId();
        return result * 59 + (($systemId == null) ? 0 : $systemId.hashCode());
    }

    public String toString() {
        return "DispositionsAddDTO(dispositions=" + getDispositions() + ", appId=" + getAppId() + ", systemId=" + getSystemId() + ")";
    }

    public List<DispositionExt> getDispositions() {
        return this.dispositions;
    }

    public void setDispositions(List<DispositionExt> dispositions) {
        this.dispositions = dispositions;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
