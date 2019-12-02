package com.ss.isc.data.viid.common.dto;

import java.beans.ConstructorProperties;
import java.util.List;


public class PersonDelDTO {
    private List<String> idList;
    private String appId;

    @ConstructorProperties({"idList", "appId"})
    public PersonDelDTO(List<String> idList, String appId) {
        this.idList = idList;
        this.appId = appId;
    }

    public PersonDelDTO() {
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PersonDelDTO)) return false;
        PersonDelDTO other = (PersonDelDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$idList = getIdList(), other$idList = other.getIdList();
        if ((this$idList == null) ? (other$idList != null) : !this$idList.equals(other$idList)) return false;
        Object this$appId = getAppId(), other$appId = other.getAppId();
        return !((this$appId == null) ? (other$appId != null) : !this$appId.equals(other$appId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PersonDelDTO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $idList = getIdList();
        result = result * 59 + (($idList == null) ? 0 : $idList.hashCode());
        Object $appId = getAppId();
        return result * 59 + (($appId == null) ? 0 : $appId.hashCode());
    }

    public String toString() {
        return "PersonDelDTO(idList=" + getIdList() + ", appId=" + getAppId() + ")";
    }

    public List<String> getIdList() {
        return this.idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
