package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
* VIID-通知对象集合
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class SubscribeNotificationListObject
{
    @JSONField(name = "SubscribeNotificationListObject")
    @JsonProperty("SubscribeNotificationListObject")
    private SubscribeNotificationObject subscribeNotificationListObject;
    private String deviceId;
    
    public SubscribeNotificationObject getSubscribeNotificationListObject() {
        return this.subscribeNotificationListObject;
    }
    
    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setSubscribeNotificationListObject(final SubscribeNotificationObject subscribeNotificationListObject) {
        this.subscribeNotificationListObject = subscribeNotificationListObject;
    }
    
    public void setDeviceId(final String deviceId) {
        this.deviceId = deviceId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SubscribeNotificationListObject)) {
            return false;
        }
        final SubscribeNotificationListObject other = (SubscribeNotificationListObject)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$subscribeNotificationListObject = this.getSubscribeNotificationListObject();
        final Object other$subscribeNotificationListObject = other.getSubscribeNotificationListObject();
        Label_0065: {
            if (this$subscribeNotificationListObject == null) {
                if (other$subscribeNotificationListObject == null) {
                    break Label_0065;
                }
            }
            else if (this$subscribeNotificationListObject.equals(other$subscribeNotificationListObject)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$deviceId = this.getDeviceId();
        final Object other$deviceId = other.getDeviceId();
        if (this$deviceId == null) {
            if (other$deviceId == null) {
                return true;
            }
        }
        else if (this$deviceId.equals(other$deviceId)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof SubscribeNotificationListObject;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $subscribeNotificationListObject = this.getSubscribeNotificationListObject();
        result = result * 59 + (($subscribeNotificationListObject == null) ? 0 : $subscribeNotificationListObject.hashCode());
        final Object $deviceId = this.getDeviceId();
        result = result * 59 + (($deviceId == null) ? 0 : $deviceId.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "SubscribeNotificationListObject(subscribeNotificationListObject=" + this.getSubscribeNotificationListObject() + ", deviceId=" + this.getDeviceId() + ")";
    }
}
