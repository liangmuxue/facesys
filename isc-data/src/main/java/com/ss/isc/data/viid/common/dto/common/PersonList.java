package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;


public class PersonList {
    @JSONField(name = "PersonListObject")
    @JsonProperty("PersonListObject")
    private PersonObject personListObject;

    public String toString() {
        return "PersonList(personListObject=" + getPersonListObject() + ")";
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $personListObject = getPersonListObject();
        return result * 59 + (($personListObject == null) ? 0 : $personListObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof PersonList;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PersonList)) return false;
        PersonList other = (PersonList) o;
        if (!other.canEqual(this)) return false;
        Object this$personListObject = getPersonListObject(), other$personListObject = other.getPersonListObject();
        return !((this$personListObject == null) ? (other$personListObject != null) : !this$personListObject.equals(other$personListObject));
    }

    public PersonObject getPersonListObject() {
        return this.personListObject;
    }

    public void setPersonListObject(PersonObject personListObject) {
        this.personListObject = personListObject;
    }
}
