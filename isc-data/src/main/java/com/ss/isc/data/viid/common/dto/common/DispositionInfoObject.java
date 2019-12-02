package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ss.isc.data.viid.common.dto.ext.DispositionExt;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
* VIID-撤控对象
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class DispositionInfoObject {
    @JsonProperty("DispositionObject")
    @JSONField(name = "DispositionObject")
    @NotNull
    @Valid
    private Disposition dispositionObject;

    @Override
    public String toString() {
        return "DispositionInfoObject(dispositionObject=" + getDispositionObject() + ")";
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $dispositionObject = getDispositionObject();
        return result * 59 + (($dispositionObject == null) ? 0 : $dispositionObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof DispositionInfoObject;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DispositionInfoObject)) return false;
        DispositionInfoObject other = (DispositionInfoObject) o;
        if (!other.canEqual(this)) return false;
        Object this$dispositionObject = getDispositionObject(), other$dispositionObject = other.getDispositionObject();
        return !((this$dispositionObject == null) ? (other$dispositionObject != null) : !this$dispositionObject.equals(other$dispositionObject));
    }

    public Disposition getDispositionObject() {
        return this.dispositionObject;
    }

    public void setDispositionObject(Disposition dispositionObject) {
        this.dispositionObject = dispositionObject;
    }
}
