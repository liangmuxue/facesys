package com.ss.isc.data.viid.common.dto;

import java.beans.ConstructorProperties;
import java.util.List;

public class DispositionsDelDTO {
    private List<String> dispositionIds;
    private String appId;

    @ConstructorProperties({"dispositionIds", "appId"})
    public DispositionsDelDTO(List<String> dispositionIds, String appId) {
        this.dispositionIds = dispositionIds;
        this.appId = appId;
    }

    public DispositionsDelDTO() {
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DispositionsDelDTO)) return false;
        DispositionsDelDTO other = (DispositionsDelDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$dispositionIds = getDispositionIds(), other$dispositionIds = other.getDispositionIds();
        if ((this$dispositionIds == null) ? (other$dispositionIds != null) : !this$dispositionIds.equals(other$dispositionIds))
            return false;
        Object this$appId = getAppId(), other$appId = other.getAppId();
        return !((this$appId == null) ? (other$appId != null) : !this$appId.equals(other$appId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof DispositionsDelDTO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $dispositionIds = getDispositionIds();
        result = result * 59 + (($dispositionIds == null) ? 0 : $dispositionIds.hashCode());
        Object $appId = getAppId();
        return result * 59 + (($appId == null) ? 0 : $appId.hashCode());
    }

    public String toString() {
        return "DispositionsDelDTO(dispositionIds=" + getDispositionIds() + ", appId=" + getAppId() + ")";
    }

    public List<String> getDispositionIds() {
        return this.dispositionIds;
    }

    public void setDispositionIds(List<String> dispositionIds) {
        this.dispositionIds = dispositionIds;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
