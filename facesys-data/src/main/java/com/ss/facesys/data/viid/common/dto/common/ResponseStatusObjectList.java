package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.util.CollectionUtils;

import java.util.List;


public class ResponseStatusObjectList {

    @JSONField(name = "ResponseStatusListObject")
    private ResponseStatusListObject ResponseStatusListObject;

    @Override
    public String toString() {
        return "ResponseStatusObjectList(ResponseStatusListObject=" + getResponseStatusListObject()
                + ")";
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $ResponseStatusListObject = getResponseStatusListObject();
        return result * 59 + (($ResponseStatusListObject == null) ? 0
                : $ResponseStatusListObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResponseStatusObjectList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResponseStatusObjectList)) {
            return false;
        }
        ResponseStatusObjectList other = (ResponseStatusObjectList) o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$ResponseStatusListObject = getResponseStatusListObject(), other$ResponseStatusListObject = other
                .getResponseStatusListObject();
        return !((this$ResponseStatusListObject == null) ? (other$ResponseStatusListObject != null)
                : !this$ResponseStatusListObject.equals(other$ResponseStatusListObject));
    }

    public ResponseStatusListObject getResponseStatusListObject() {
        return this.ResponseStatusListObject;
    }

    public void setResponseStatusListObject(ResponseStatusListObject ResponseStatusListObject) {
        this.ResponseStatusListObject = ResponseStatusListObject;
    }

    public void addResponseStatusVIID(List<ResponseStatusVIID> responseStatusVIIDS) {
        if (this.ResponseStatusListObject == null || CollectionUtils
                .isEmpty(this.ResponseStatusListObject.getResponseStatusObject())) {
            this.ResponseStatusListObject = new ResponseStatusListObject();
            this.ResponseStatusListObject.setResponseStatusObject(responseStatusVIIDS);
        } else {
            this.ResponseStatusListObject.getResponseStatusObject().addAll(responseStatusVIIDS);
        }
    }

    public static class ResponseStatusListObject {

        @JSONField(name = "ResponseStatusObject")
        private List<ResponseStatusVIID> ResponseStatusObject;

        @Override
        public String toString() {
            return "ResponseStatusObjectList.ResponseStatusListObject(ResponseStatusObject="
                    + getResponseStatusObject() + ")";
        }

        @Override
        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            Object $ResponseStatusObject = getResponseStatusObject();
            return result * 59 + (($ResponseStatusObject == null) ? 0 : $ResponseStatusObject.hashCode());
        }

        protected boolean canEqual(Object other) {
            return other instanceof ResponseStatusListObject;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ResponseStatusListObject)) {
                return false;
            }
            ResponseStatusListObject other = (ResponseStatusListObject) o;
            if (!other.canEqual(this)) {
                return false;
            }
            Object this$ResponseStatusObject = getResponseStatusObject(), other$ResponseStatusObject = other
                    .getResponseStatusObject();
            return !((this$ResponseStatusObject == null) ? (other$ResponseStatusObject != null)
                    : !this$ResponseStatusObject.equals(other$ResponseStatusObject));
        }

        public List<ResponseStatusVIID> getResponseStatusObject() {
            return this.ResponseStatusObject;
        }

        public void setResponseStatusObject(List<ResponseStatusVIID> ResponseStatusObject) {
            this.ResponseStatusObject = ResponseStatusObject;
        }
    }
}
