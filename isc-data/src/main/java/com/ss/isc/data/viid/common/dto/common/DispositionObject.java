package com.ss.isc.data.viid.common.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ss.isc.data.viid.common.dto.ext.DispositionExt;

import java.util.List;

/**
* VIID-布控对象
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class DispositionObject {
    @JsonProperty("DispositionObject")
    private List<Disposition> dispositionObject;

    @Override
    public String toString() {
        return "DispositionObject(dispositionObject=" + getDispositionObject() + ")";
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $dispositionObject = getDispositionObject();
        return result * 59 + (($dispositionObject == null) ? 0 : $dispositionObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof DispositionObject;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DispositionObject)) {
            return false;
        }
        DispositionObject other = (DispositionObject) o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$dispositionObject = getDispositionObject(), other$dispositionObject = other.getDispositionObject();
        return !((this$dispositionObject == null) ? (other$dispositionObject != null) : !this$dispositionObject.equals(other$dispositionObject));
    }

    public List<Disposition> getDispositionObject() {
        return this.dispositionObject;
    }

    public void setDispositionObject(List<Disposition> dispositionObject) {
        this.dispositionObject = dispositionObject;
    }
}
