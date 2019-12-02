package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
/**
* VIID-通知对象
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class SubscribeNotificationObject
{
    @JSONField(name = "SubscribeNotificationObject")
    @JsonProperty("SubscribeNotificationObject")
    private List<SubscribeNotification> subscribeNotificationObject;
    
    public List<SubscribeNotification> getSubscribeNotificationObject() {
        return this.subscribeNotificationObject;
    }
    
    public void setSubscribeNotificationObject(final List<SubscribeNotification> subscribeNotificationObject) {
        this.subscribeNotificationObject = subscribeNotificationObject;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SubscribeNotificationObject)) {
            return false;
        }
        final SubscribeNotificationObject other = (SubscribeNotificationObject)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$subscribeNotificationObject = this.getSubscribeNotificationObject();
        final Object other$subscribeNotificationObject = other.getSubscribeNotificationObject();
        if (this$subscribeNotificationObject == null) {
            if (other$subscribeNotificationObject == null) {
                return true;
            }
        }
        else if (this$subscribeNotificationObject.equals(other$subscribeNotificationObject)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof SubscribeNotificationObject;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $subscribeNotificationObject = this.getSubscribeNotificationObject();
        result = result * 59 + (($subscribeNotificationObject == null) ? 0 : $subscribeNotificationObject.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "SubscribeNotificationObject(subscribeNotificationObject=" + this.getSubscribeNotificationObject() + ")";
    }
}
