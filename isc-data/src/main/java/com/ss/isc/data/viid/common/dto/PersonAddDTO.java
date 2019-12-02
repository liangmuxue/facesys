package com.ss.isc.data.viid.common.dto;

import com.ss.isc.data.viid.common.dto.ext.PersonExt;

import java.beans.ConstructorProperties;
import java.util.List;


public class PersonAddDTO {
    private List<PersonExt> personExts;
    private String appId;

    @ConstructorProperties({"personExts", "appId"})
    public PersonAddDTO(List<PersonExt> personExts, String appId) {
        this.personExts = personExts;
        this.appId = appId;
    }

    public PersonAddDTO() {
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PersonAddDTO)) return false;
        PersonAddDTO other = (PersonAddDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$personExts = getPersonExts(), other$personExts = other.getPersonExts();
        if ((this$personExts == null) ? (other$personExts != null) : !this$personExts.equals(other$personExts))
            return false;
        Object this$appId = getAppId(), other$appId = other.getAppId();
        return !((this$appId == null) ? (other$appId != null) : !this$appId.equals(other$appId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PersonAddDTO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $personExts = getPersonExts();
        result = result * 59 + (($personExts == null) ? 0 : $personExts.hashCode());
        Object $appId = getAppId();
        return result * 59 + (($appId == null) ? 0 : $appId.hashCode());
    }

    public String toString() {
        return "PersonAddDTO(personExts=" + getPersonExts() + ", appId=" + getAppId() + ")";
    }

    public List<PersonExt> getPersonExts() {
        return this.personExts;
    }

    public void setPersonExts(List<PersonExt> personExts) {
        this.personExts = personExts;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
