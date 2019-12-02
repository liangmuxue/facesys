package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
* 告警实体类
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class DispositionNotification
{
    @JSONField(name = "NotificationID")
    @JsonProperty("NotificationID")
    private String notificationID;
    @JSONField(name = "DispositionID")
    @JsonProperty("DispositionID")
    private String dispositionID;
    @JSONField(name = "Title")
    @JsonProperty("Title")
    private String title;
    @JSONField(name = "TriggerTime")
    @JsonProperty("TriggerTime")
    private String triggerTime;
    @JSONField(name = "CntObjectID")
    @JsonProperty("CntObjectID")
    private String cntObjectID;
    @JSONField(name = "PersonObject")
    @JsonProperty("PersonObject")
    private Person personObject;
    @JSONField(name = "MotorVehicleObject")
    @JsonProperty("MotorVehicleObject")
    private MotorVehicle motorVehicleObject;
    
    public String getNotificationID() {
        return this.notificationID;
    }
    
    public String getDispositionID() {
        return this.dispositionID;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getTriggerTime() {
        return this.triggerTime;
    }
    
    public String getCntObjectID() {
        return this.cntObjectID;
    }
    
    public Person getPersonObject() {
        return this.personObject;
    }
    
    public MotorVehicle getMotorVehicleObject() {
        return this.motorVehicleObject;
    }
    
    public void setNotificationID(final String notificationID) {
        this.notificationID = notificationID;
    }
    
    public void setDispositionID(final String dispositionID) {
        this.dispositionID = dispositionID;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setTriggerTime(final String triggerTime) {
        this.triggerTime = triggerTime;
    }
    
    public void setCntObjectID(final String cntObjectID) {
        this.cntObjectID = cntObjectID;
    }
    
    public void setPersonObject(final Person personObject) {
        this.personObject = personObject;
    }
    
    public void setMotorVehicleObject(final MotorVehicle motorVehicleObject) {
        this.motorVehicleObject = motorVehicleObject;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DispositionNotification)) {
            return false;
        }
        final DispositionNotification other = (DispositionNotification)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$notificationID = this.getNotificationID();
        final Object other$notificationID = other.getNotificationID();
        Label_0065: {
            if (this$notificationID == null) {
                if (other$notificationID == null) {
                    break Label_0065;
                }
            }
            else if (this$notificationID.equals(other$notificationID)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$dispositionID = this.getDispositionID();
        final Object other$dispositionID = other.getDispositionID();
        Label_0102: {
            if (this$dispositionID == null) {
                if (other$dispositionID == null) {
                    break Label_0102;
                }
            }
            else if (this$dispositionID.equals(other$dispositionID)) {
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
        final Object this$cntObjectID = this.getCntObjectID();
        final Object other$cntObjectID = other.getCntObjectID();
        Label_0213: {
            if (this$cntObjectID == null) {
                if (other$cntObjectID == null) {
                    break Label_0213;
                }
            }
            else if (this$cntObjectID.equals(other$cntObjectID)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$personObject = this.getPersonObject();
        final Object other$personObject = other.getPersonObject();
        Label_0250: {
            if (this$personObject == null) {
                if (other$personObject == null) {
                    break Label_0250;
                }
            }
            else if (this$personObject.equals(other$personObject)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$motorVehicleObject = this.getMotorVehicleObject();
        final Object other$motorVehicleObject = other.getMotorVehicleObject();
        if (this$motorVehicleObject == null) {
            if (other$motorVehicleObject == null) {
                return true;
            }
        }
        else if (this$motorVehicleObject.equals(other$motorVehicleObject)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof DispositionNotification;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $notificationID = this.getNotificationID();
        result = result * 59 + (($notificationID == null) ? 0 : $notificationID.hashCode());
        final Object $dispositionID = this.getDispositionID();
        result = result * 59 + (($dispositionID == null) ? 0 : $dispositionID.hashCode());
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 0 : $title.hashCode());
        final Object $triggerTime = this.getTriggerTime();
        result = result * 59 + (($triggerTime == null) ? 0 : $triggerTime.hashCode());
        final Object $cntObjectID = this.getCntObjectID();
        result = result * 59 + (($cntObjectID == null) ? 0 : $cntObjectID.hashCode());
        final Object $personObject = this.getPersonObject();
        result = result * 59 + (($personObject == null) ? 0 : $personObject.hashCode());
        final Object $motorVehicleObject = this.getMotorVehicleObject();
        result = result * 59 + (($motorVehicleObject == null) ? 0 : $motorVehicleObject.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "DispositionNotification(notificationID=" + this.getNotificationID() + ", dispositionID=" + this.getDispositionID() + ", title=" + this.getTitle() + ", triggerTime=" + this.getTriggerTime() + ", cntObjectID=" + this.getCntObjectID() + ", personObject=" + this.getPersonObject() + ", motorVehicleObject=" + this.getMotorVehicleObject() + ")";
    }
}
