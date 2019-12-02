package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
* VIID-告警对象集合
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class DispositionNotificationObjectList
{
    @JSONField(name = "DispositionNotificationObjectList")
    @JsonProperty("DispositionNotificationObjectList")
    private DispositionNotificationObject dispositionNotificationObjectList;
    
    public DispositionNotificationObject getDispositionNotificationObjectList() {
        return this.dispositionNotificationObjectList;
    }
    
    public void setDispositionNotificationObjectList(final DispositionNotificationObject dispositionNotificationObjectList) {
        this.dispositionNotificationObjectList = dispositionNotificationObjectList;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DispositionNotificationObjectList)) {
            return false;
        }
        final DispositionNotificationObjectList other = (DispositionNotificationObjectList)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$dispositionNotificationObjectList = this.getDispositionNotificationObjectList();
        final Object other$dispositionNotificationObjectList = other.getDispositionNotificationObjectList();
        if (this$dispositionNotificationObjectList == null) {
            if (other$dispositionNotificationObjectList == null) {
                return true;
            }
        }
        else if (this$dispositionNotificationObjectList.equals(other$dispositionNotificationObjectList)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DispositionNotificationObjectList;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $dispositionNotificationObjectList = this.getDispositionNotificationObjectList();
        result = result * 59 + (($dispositionNotificationObjectList == null) ? 0 : $dispositionNotificationObjectList.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "DispositionNotificationObjectList(dispositionNotificationObjectList=" + this.getDispositionNotificationObjectList() + ")";
    }
}
