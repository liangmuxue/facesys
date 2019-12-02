package com.ss.isc.data.viid.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AbstractVIIDNotifyDataDTO {
    @JSONField(name = "NotificationID")
    @JsonProperty("NotificationID")
    @NotNull
    private String notificationId;
    @JSONField(name = "SubscribeID")
    @JsonProperty("SubscribeID")
    @NotNull
    private String subscribeId;
    @JSONField(name = "Title")
    @JsonProperty("Title")
    @NotNull
    private String title;
    @JSONField(name = "TriggerTime")
    @JsonProperty("TriggerTime")
    @NotNull
    private String triggerTime;
    @JSONField(name = "InfoIDs")
    @JsonProperty("InfoIDs")
    @NotNull
    private String infoIds;
    
    public String getNotificationId() {
        return this.notificationId;
    }
    
    public String getSubscribeId() {
        return this.subscribeId;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getTriggerTime() {
        return this.triggerTime;
    }
    
    public String getInfoIds() {
        return this.infoIds;
    }
    
    public void setNotificationId(final String notificationId) {
        this.notificationId = notificationId;
    }
    
    public void setSubscribeId(final String subscribeId) {
        this.subscribeId = subscribeId;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setTriggerTime(final String triggerTime) {
        this.triggerTime = triggerTime;
    }
    
    public void setInfoIds(final String infoIds) {
        this.infoIds = infoIds;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AbstractVIIDNotifyDataDTO)) {
            return false;
        }
        final AbstractVIIDNotifyDataDTO other = (AbstractVIIDNotifyDataDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$notificationId = this.getNotificationId();
        final Object other$notificationId = other.getNotificationId();
        Label_0065: {
            if (this$notificationId == null) {
                if (other$notificationId == null) {
                    break Label_0065;
                }
            }
            else if (this$notificationId.equals(other$notificationId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$subscribeId = this.getSubscribeId();
        final Object other$subscribeId = other.getSubscribeId();
        Label_0102: {
            if (this$subscribeId == null) {
                if (other$subscribeId == null) {
                    break Label_0102;
                }
            }
            else if (this$subscribeId.equals(other$subscribeId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        Label_0139: {
            if (this$title == null) {
                if (other$title == null) {
                    break Label_0139;
                }
            }
            else if (this$title.equals(other$title)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$triggerTime = this.getTriggerTime();
        final Object other$triggerTime = other.getTriggerTime();
        Label_0176: {
            if (this$triggerTime == null) {
                if (other$triggerTime == null) {
                    break Label_0176;
                }
            }
            else if (this$triggerTime.equals(other$triggerTime)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$infoIds = this.getInfoIds();
        final Object other$infoIds = other.getInfoIds();
        if (this$infoIds == null) {
            if (other$infoIds == null) {
                return true;
            }
        }
        else if (this$infoIds.equals(other$infoIds)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof AbstractVIIDNotifyDataDTO;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $notificationId = this.getNotificationId();
        result = result * 59 + (($notificationId == null) ? 0 : $notificationId.hashCode());
        final Object $subscribeId = this.getSubscribeId();
        result = result * 59 + (($subscribeId == null) ? 0 : $subscribeId.hashCode());
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 0 : $title.hashCode());
        final Object $triggerTime = this.getTriggerTime();
        result = result * 59 + (($triggerTime == null) ? 0 : $triggerTime.hashCode());
        final Object $infoIds = this.getInfoIds();
        result = result * 59 + (($infoIds == null) ? 0 : $infoIds.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "AbstractVIIDNotifyDataDTO(notificationId=" + this.getNotificationId() + ", subscribeId=" + this.getSubscribeId() + ", title=" + this.getTitle() + ", triggerTime=" + this.getTriggerTime() + ", infoIds=" + this.getInfoIds() + ")";
    }
}
