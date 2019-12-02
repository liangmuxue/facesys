package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;


public class ResponseStatusObject {

    @JSONField(name = "ResponseStatusObject")
    private ResponseStatusVIID ResponseStatusObject;

    @Override
    public String toString() {
        return "ResponseStatusObject(ResponseStatusObject=" + getResponseStatusObject() + ")";
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $ResponseStatusObject = getResponseStatusObject();
        return result * 59 + (($ResponseStatusObject == null) ? 0 : $ResponseStatusObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResponseStatusObject;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResponseStatusObject)) {
            return false;
        }
        ResponseStatusObject other = (ResponseStatusObject) o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$ResponseStatusObject = getResponseStatusObject(), other$ResponseStatusObject = other
                .getResponseStatusObject();
        return !((this$ResponseStatusObject == null) ? (other$ResponseStatusObject != null)
                : !this$ResponseStatusObject.equals(other$ResponseStatusObject));
    }

    public ResponseStatusVIID getResponseStatusObject() {
        return this.ResponseStatusObject;
    }

    public void setResponseStatusObject(ResponseStatusVIID ResponseStatusObject) {
        this.ResponseStatusObject = ResponseStatusObject;
    }
}
