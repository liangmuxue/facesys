package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
* VIID-布控对象集合
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class DispositionList {
    @JsonProperty("DispositionListObject")
    @JSONField(name = "DispositionListObject")
    private DispositionObject dispositionListObject;

    @Override
    public String toString() {
        return "DispositionList(dispositionListObject=" + getDispositionListObject() + ")";
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $dispositionListObject = getDispositionListObject();
        return result * 59 + (($dispositionListObject == null) ? 0 : $dispositionListObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof DispositionList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DispositionList)) {
            return false;
        }
        DispositionList other = (DispositionList) o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$dispositionListObject = getDispositionListObject(), other$dispositionListObject = other.getDispositionListObject();
        return !((this$dispositionListObject == null) ? (other$dispositionListObject != null) : !this$dispositionListObject.equals(other$dispositionListObject));
    }

    public DispositionObject getDispositionListObject() {
        return this.dispositionListObject;
    }

    public void setDispositionListObject(DispositionObject dispositionListObject) {
        this.dispositionListObject = dispositionListObject;
    }
}
