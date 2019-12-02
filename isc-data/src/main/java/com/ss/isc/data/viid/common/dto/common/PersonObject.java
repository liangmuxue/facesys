package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ss.isc.data.viid.common.dto.ext.PersonExt;

import java.util.List;


public class PersonObject {
    @JSONField(name = "PersonObject")
    @JsonProperty("PersonObject")
    private List<PersonExt> personObject;

    public String toString() {
        return "PersonObject(personObject=" + getPersonObject() + ")";
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $personObject = getPersonObject();
        return result * 59 + (($personObject == null) ? 0 : $personObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof PersonObject;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PersonObject)) return false;
        PersonObject other = (PersonObject) o;
        if (!other.canEqual(this)) return false;
        Object this$personObject = getPersonObject(), other$personObject = other.getPersonObject();
        return !((this$personObject == null) ? (other$personObject != null) : !this$personObject.equals(other$personObject));
    }

    public List<PersonExt> getPersonObject() {
        return this.personObject;
    }

    public void setPersonObject(List<PersonExt> personObject) {
        this.personObject = personObject;
    }
}
