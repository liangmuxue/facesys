package com.ss.facesys.data.viid.common.dto;


public class ViidRegisterQueryDTO {
    private String deviceId;
    private String systemName;
    private Short authType;
    private Short status;
    private Short modelType;
    private Short structDirect;
    private Short sendRegisterStatus;
    private Short sendKeepaliveStatus;
    private Short receiveRegisterStatus;
    private Short receiveKeepaliveStatus;
    private Long createTimeBegin;
    private Long createTimeEnd;
    private String appId;
    private String ip;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ViidRegisterQueryDTO)) return false;
        ViidRegisterQueryDTO other = (ViidRegisterQueryDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$deviceId = getDeviceId(), other$deviceId = other.getDeviceId();
        if ((this$deviceId == null) ? (other$deviceId != null) : !this$deviceId.equals(other$deviceId)) return false;
        Object this$systemName = getSystemName(), other$systemName = other.getSystemName();
        if ((this$systemName == null) ? (other$systemName != null) : !this$systemName.equals(other$systemName))
            return false;
        Object this$authType = getAuthType(), other$authType = other.getAuthType();
        if ((this$authType == null) ? (other$authType != null) : !this$authType.equals(other$authType)) return false;
        Object this$status = getStatus(), other$status = other.getStatus();
        if ((this$status == null) ? (other$status != null) : !this$status.equals(other$status)) return false;
        Object this$modelType = getModelType(), other$modelType = other.getModelType();
        if ((this$modelType == null) ? (other$modelType != null) : !this$modelType.equals(other$modelType))
            return false;
        Object this$structDirect = getStructDirect(), other$structDirect = other.getStructDirect();
        if ((this$structDirect == null) ? (other$structDirect != null) : !this$structDirect.equals(other$structDirect))
            return false;
        Object this$sendRegisterStatus = getSendRegisterStatus(), other$sendRegisterStatus = other.getSendRegisterStatus();
        if ((this$sendRegisterStatus == null) ? (other$sendRegisterStatus != null) : !this$sendRegisterStatus.equals(other$sendRegisterStatus))
            return false;
        Object this$sendKeepaliveStatus = getSendKeepaliveStatus(), other$sendKeepaliveStatus = other.getSendKeepaliveStatus();
        if ((this$sendKeepaliveStatus == null) ? (other$sendKeepaliveStatus != null) : !this$sendKeepaliveStatus.equals(other$sendKeepaliveStatus))
            return false;
        Object this$receiveRegisterStatus = getReceiveRegisterStatus(), other$receiveRegisterStatus = other.getReceiveRegisterStatus();
        if ((this$receiveRegisterStatus == null) ? (other$receiveRegisterStatus != null) : !this$receiveRegisterStatus.equals(other$receiveRegisterStatus))
            return false;
        Object this$receiveKeepaliveStatus = getReceiveKeepaliveStatus(), other$receiveKeepaliveStatus = other.getReceiveKeepaliveStatus();
        if ((this$receiveKeepaliveStatus == null) ? (other$receiveKeepaliveStatus != null) : !this$receiveKeepaliveStatus.equals(other$receiveKeepaliveStatus))
            return false;
        Object this$createTimeBegin = getCreateTimeBegin(), other$createTimeBegin = other.getCreateTimeBegin();
        if ((this$createTimeBegin == null) ? (other$createTimeBegin != null) : !this$createTimeBegin.equals(other$createTimeBegin))
            return false;
        Object this$createTimeEnd = getCreateTimeEnd(), other$createTimeEnd = other.getCreateTimeEnd();
        if ((this$createTimeEnd == null) ? (other$createTimeEnd != null) : !this$createTimeEnd.equals(other$createTimeEnd))
            return false;
        Object this$appId = getAppId(), other$appId = other.getAppId();
        if ((this$appId == null) ? (other$appId != null) : !this$appId.equals(other$appId)) return false;
        Object this$ip = getIp(), other$ip = other.getIp();
        return !((this$ip == null) ? (other$ip != null) : !this$ip.equals(other$ip));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViidRegisterQueryDTO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $deviceId = getDeviceId();
        result = result * 59 + (($deviceId == null) ? 0 : $deviceId.hashCode());
        Object $systemName = getSystemName();
        result = result * 59 + (($systemName == null) ? 0 : $systemName.hashCode());
        Object $authType = getAuthType();
        result = result * 59 + (($authType == null) ? 0 : $authType.hashCode());
        Object $status = getStatus();
        result = result * 59 + (($status == null) ? 0 : $status.hashCode());
        Object $modelType = getModelType();
        result = result * 59 + (($modelType == null) ? 0 : $modelType.hashCode());
        Object $structDirect = getStructDirect();
        result = result * 59 + (($structDirect == null) ? 0 : $structDirect.hashCode());
        Object $sendRegisterStatus = getSendRegisterStatus();
        result = result * 59 + (($sendRegisterStatus == null) ? 0 : $sendRegisterStatus.hashCode());
        Object $sendKeepaliveStatus = getSendKeepaliveStatus();
        result = result * 59 + (($sendKeepaliveStatus == null) ? 0 : $sendKeepaliveStatus.hashCode());
        Object $receiveRegisterStatus = getReceiveRegisterStatus();
        result = result * 59 + (($receiveRegisterStatus == null) ? 0 : $receiveRegisterStatus.hashCode());
        Object $receiveKeepaliveStatus = getReceiveKeepaliveStatus();
        result = result * 59 + (($receiveKeepaliveStatus == null) ? 0 : $receiveKeepaliveStatus.hashCode());
        Object $createTimeBegin = getCreateTimeBegin();
        result = result * 59 + (($createTimeBegin == null) ? 0 : $createTimeBegin.hashCode());
        Object $createTimeEnd = getCreateTimeEnd();
        result = result * 59 + (($createTimeEnd == null) ? 0 : $createTimeEnd.hashCode());
        Object $appId = getAppId();
        result = result * 59 + (($appId == null) ? 0 : $appId.hashCode());
        Object $ip = getIp();
        return result * 59 + (($ip == null) ? 0 : $ip.hashCode());
    }

    public String toString() {
        return "ViidRegisterQueryDTO(deviceId=" + getDeviceId() + ", systemName=" + getSystemName() + ", authType=" + getAuthType() + ", status=" + getStatus() + ", modelType=" + getModelType() + ", structDirect=" + getStructDirect() + ", sendRegisterStatus=" + getSendRegisterStatus() + ", sendKeepaliveStatus=" + getSendKeepaliveStatus() + ", receiveRegisterStatus=" + getReceiveRegisterStatus() + ", receiveKeepaliveStatus=" + getReceiveKeepaliveStatus() + ", createTimeBegin=" + getCreateTimeBegin() + ", createTimeEnd=" + getCreateTimeEnd() + ", appId=" + getAppId() + ", ip=" + getIp() + ")";
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Short getAuthType() {
        return this.authType;
    }

    public void setAuthType(Short authType) {
        this.authType = authType;
    }

    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getModelType() {
        return this.modelType;
    }

    public void setModelType(Short modelType) {
        this.modelType = modelType;
    }

    public Short getStructDirect() {
        return this.structDirect;
    }

    public void setStructDirect(Short structDirect) {
        this.structDirect = structDirect;
    }

    public Short getSendRegisterStatus() {
        return this.sendRegisterStatus;
    }

    public void setSendRegisterStatus(Short sendRegisterStatus) {
        this.sendRegisterStatus = sendRegisterStatus;
    }

    public Short getSendKeepaliveStatus() {
        return this.sendKeepaliveStatus;
    }

    public void setSendKeepaliveStatus(Short sendKeepaliveStatus) {
        this.sendKeepaliveStatus = sendKeepaliveStatus;
    }

    public Short getReceiveRegisterStatus() {
        return this.receiveRegisterStatus;
    }

    public void setReceiveRegisterStatus(Short receiveRegisterStatus) {
        this.receiveRegisterStatus = receiveRegisterStatus;
    }

    public Short getReceiveKeepaliveStatus() {
        return this.receiveKeepaliveStatus;
    }

    public void setReceiveKeepaliveStatus(Short receiveKeepaliveStatus) {
        this.receiveKeepaliveStatus = receiveKeepaliveStatus;
    }

    public Long getCreateTimeBegin() {
        return this.createTimeBegin;
    }

    public void setCreateTimeBegin(Long createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Long getCreateTimeEnd() {
        return this.createTimeEnd;
    }

    public void setCreateTimeEnd(Long createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
