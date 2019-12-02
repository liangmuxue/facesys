package com.ss.facesys.data.viid.common.dto.ext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ss.facesys.data.viid.common.dto.common.Person;

public class PersonExt extends Person {
    @JsonProperty("DispositionPersonLib")
    private String dispositionPersonLib;

    public String toString() {
        return "PersonExt(dispositionPersonLib=" + getDispositionPersonLib() + ")";
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $dispositionPersonLib = getDispositionPersonLib();
        return result * 59 + (($dispositionPersonLib == null) ? 0 : $dispositionPersonLib.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof PersonExt;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PersonExt)) return false;
        PersonExt other = (PersonExt) o;
        if (!other.canEqual(this)) return false;
        Object this$dispositionPersonLib = getDispositionPersonLib(), other$dispositionPersonLib = other.getDispositionPersonLib();
        return !((this$dispositionPersonLib == null) ? (other$dispositionPersonLib != null) : !this$dispositionPersonLib.equals(other$dispositionPersonLib));
    }

    public String getDispositionPersonLib() {
        return this.dispositionPersonLib;
    }

    public void setDispositionPersonLib(String dispositionPersonLib) {
        this.dispositionPersonLib = dispositionPersonLib;
    }
}
