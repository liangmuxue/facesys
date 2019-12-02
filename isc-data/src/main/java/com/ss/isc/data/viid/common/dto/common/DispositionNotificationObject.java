package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
* VIID-告警对象
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class DispositionNotificationObject
{
    @JSONField(name = "DispositionNotificationObject")
    @JsonProperty("DispositionNotificationObject")
    private List<DispositionNotification> dispositionNotificationObject;
    
    public List<DispositionNotification> getDispositionNotificationObject() {
        return this.dispositionNotificationObject;
    }
    
    public void setDispositionNotificationObject(final List<DispositionNotification> dispositionNotificationObject) {
        this.dispositionNotificationObject = dispositionNotificationObject;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DispositionNotificationObject)) {
            return false;
        }
        final DispositionNotificationObject other = (DispositionNotificationObject)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$dispositionNotificationObject = this.getDispositionNotificationObject();
        final Object other$dispositionNotificationObject = other.getDispositionNotificationObject();
        if (this$dispositionNotificationObject == null) {
            if (other$dispositionNotificationObject == null) {
                return true;
            }
        }
        else if (this$dispositionNotificationObject.equals(other$dispositionNotificationObject)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof DispositionNotificationObject;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $dispositionNotificationObject = this.getDispositionNotificationObject();
        result = result * 59 + (($dispositionNotificationObject == null) ? 0 : $dispositionNotificationObject.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "DispositionNotificationObject(dispositionNotificationObject=" + this.getDispositionNotificationObject() + ")";
    }
}
