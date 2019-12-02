package com.ss.facesys.data.viid.common.from;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

/**
* 订阅对象
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class SubscribeObjectForm {
    @JsonProperty("SubscribeObject")
    @JSONField(name = "SubscribeObject")
    @Valid
    private SubscribeBaseForm SubscribeObject;

    public SubscribeBaseForm getSubscribeObject() {
        return this.SubscribeObject;
    }

    public void setSubscribeObject(final SubscribeBaseForm SubscribeObject) {
        this.SubscribeObject = SubscribeObject;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SubscribeObjectForm)) {
            return false;
        }
        final SubscribeObjectForm other = (SubscribeObjectForm) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$SubscribeObject = this.getSubscribeObject();
        final Object other$SubscribeObject = other.getSubscribeObject();
        if (this$SubscribeObject == null) {
            if (other$SubscribeObject == null) {
                return true;
            }
        } else if (this$SubscribeObject.equals(other$SubscribeObject)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SubscribeObjectForm;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $SubscribeObject = this.getSubscribeObject();
        result = result * 59 + (($SubscribeObject == null) ? 0 : $SubscribeObject.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SubscribeObjectForm(SubscribeObject=" + this.getSubscribeObject() + ")";
    }
}
