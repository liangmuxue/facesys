package com.ss.facesys.data.viid.common.dto;

public class ViidRegisterSendDTO {
    private String registerId;
    private String deviceId;
    private String appId;
    private String systemName;
    private String httpProtocol;
    private String ip;
    private String port;
    private Short authType;
    private Short status;
    private String sendUserName;
    private String sendPassWord;
    private Short sendRegisterStatus;
    private Short sendKeepaliveStatus;
    private Long sendRegisterTime;
    private Long sendKeepaliveTime;
    private String receiveUserName;
    private String receivePassWord;
    private Short receiveRegisterStatus;
    private Short receiveKeepaliveStatus;
    private Long receiveRegisterTime;
    private Long receiveKeepaliveTime;
    private String registerExtField1;
    private String registerExtField2;
    private String registerExtField3;
    private Long createTime;
    private Long updateTime;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ViidRegisterSendDTO)) return false;
        ViidRegisterSendDTO other = (ViidRegisterSendDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$registerId = getRegisterId(), other$registerId = other.getRegisterId();
        if ((this$registerId == null) ? (other$registerId != null) : !this$registerId.equals(other$registerId))
            return false;
        Object this$deviceId = getDeviceId(), other$deviceId = other.getDeviceId();
        if ((this$deviceId == null) ? (other$deviceId != null) : !this$deviceId.equals(other$deviceId)) return false;
        Object this$appId = getAppId(), other$appId = other.getAppId();
        if ((this$appId == null) ? (other$appId != null) : !this$appId.equals(other$appId)) return false;
        Object this$systemName = getSystemName(), other$systemName = other.getSystemName();
        if ((this$systemName == null) ? (other$systemName != null) : !this$systemName.equals(other$systemName))
            return false;
        Object this$httpProtocol = getHttpProtocol(), other$httpProtocol = other.getHttpProtocol();
        if ((this$httpProtocol == null) ? (other$httpProtocol != null) : !this$httpProtocol.equals(other$httpProtocol))
            return false;
        Object this$ip = getIp(), other$ip = other.getIp();
        if ((this$ip == null) ? (other$ip != null) : !this$ip.equals(other$ip)) return false;
        Object this$port = getPort(), other$port = other.getPort();
        if ((this$port == null) ? (other$port != null) : !this$port.equals(other$port)) return false;
        Object this$authType = getAuthType(), other$authType = other.getAuthType();
        if ((this$authType == null) ? (other$authType != null) : !this$authType.equals(other$authType)) return false;
        Object this$status = getStatus(), other$status = other.getStatus();
        if ((this$status == null) ? (other$status != null) : !this$status.equals(other$status)) return false;
        Object this$sendUserName = getSendUserName(), other$sendUserName = other.getSendUserName();
        if ((this$sendUserName == null) ? (other$sendUserName != null) : !this$sendUserName.equals(other$sendUserName))
            return false;
        Object this$sendPassWord = getSendPassWord(), other$sendPassWord = other.getSendPassWord();
        if ((this$sendPassWord == null) ? (other$sendPassWord != null) : !this$sendPassWord.equals(other$sendPassWord))
            return false;
        Object this$sendRegisterStatus = getSendRegisterStatus(), other$sendRegisterStatus = other.getSendRegisterStatus();
        if ((this$sendRegisterStatus == null) ? (other$sendRegisterStatus != null) : !this$sendRegisterStatus.equals(other$sendRegisterStatus))
            return false;
        Object this$sendKeepaliveStatus = getSendKeepaliveStatus(), other$sendKeepaliveStatus = other.getSendKeepaliveStatus();
        if ((this$sendKeepaliveStatus == null) ? (other$sendKeepaliveStatus != null) : !this$sendKeepaliveStatus.equals(other$sendKeepaliveStatus))
            return false;
        Object this$sendRegisterTime = getSendRegisterTime(), other$sendRegisterTime = other.getSendRegisterTime();
        if ((this$sendRegisterTime == null) ? (other$sendRegisterTime != null) : !this$sendRegisterTime.equals(other$sendRegisterTime))
            return false;
        Object this$sendKeepaliveTime = getSendKeepaliveTime(), other$sendKeepaliveTime = other.getSendKeepaliveTime();
        if ((this$sendKeepaliveTime == null) ? (other$sendKeepaliveTime != null) : !this$sendKeepaliveTime.equals(other$sendKeepaliveTime))
            return false;
        Object this$receiveUserName = getReceiveUserName(), other$receiveUserName = other.getReceiveUserName();
        if ((this$receiveUserName == null) ? (other$receiveUserName != null) : !this$receiveUserName.equals(other$receiveUserName))
            return false;
        Object this$receivePassWord = getReceivePassWord(), other$receivePassWord = other.getReceivePassWord();
        if ((this$receivePassWord == null) ? (other$receivePassWord != null) : !this$receivePassWord.equals(other$receivePassWord))
            return false;
        Object this$receiveRegisterStatus = getReceiveRegisterStatus(), other$receiveRegisterStatus = other.getReceiveRegisterStatus();
        if ((this$receiveRegisterStatus == null) ? (other$receiveRegisterStatus != null) : !this$receiveRegisterStatus.equals(other$receiveRegisterStatus))
            return false;
        Object this$receiveKeepaliveStatus = getReceiveKeepaliveStatus(), other$receiveKeepaliveStatus = other.getReceiveKeepaliveStatus();
        if ((this$receiveKeepaliveStatus == null) ? (other$receiveKeepaliveStatus != null) : !this$receiveKeepaliveStatus.equals(other$receiveKeepaliveStatus))
            return false;
        Object this$receiveRegisterTime = getReceiveRegisterTime(), other$receiveRegisterTime = other.getReceiveRegisterTime();
        if ((this$receiveRegisterTime == null) ? (other$receiveRegisterTime != null) : !this$receiveRegisterTime.equals(other$receiveRegisterTime))
            return false;
        Object this$receiveKeepaliveTime = getReceiveKeepaliveTime(), other$receiveKeepaliveTime = other.getReceiveKeepaliveTime();
        if ((this$receiveKeepaliveTime == null) ? (other$receiveKeepaliveTime != null) : !this$receiveKeepaliveTime.equals(other$receiveKeepaliveTime))
            return false;
        Object this$registerExtField1 = getRegisterExtField1(), other$registerExtField1 = other.getRegisterExtField1();
        if ((this$registerExtField1 == null) ? (other$registerExtField1 != null) : !this$registerExtField1.equals(other$registerExtField1))
            return false;
        Object this$registerExtField2 = getRegisterExtField2(), other$registerExtField2 = other.getRegisterExtField2();
        if ((this$registerExtField2 == null) ? (other$registerExtField2 != null) : !this$registerExtField2.equals(other$registerExtField2))
            return false;
        Object this$registerExtField3 = getRegisterExtField3(), other$registerExtField3 = other.getRegisterExtField3();
        if ((this$registerExtField3 == null) ? (other$registerExtField3 != null) : !this$registerExtField3.equals(other$registerExtField3))
            return false;
        Object this$createTime = getCreateTime(), other$createTime = other.getCreateTime();
        if ((this$createTime == null) ? (other$createTime != null) : !this$createTime.equals(other$createTime))
            return false;
        Object this$updateTime = getUpdateTime(), other$updateTime = other.getUpdateTime();
        return !((this$updateTime == null) ? (other$updateTime != null) : !this$updateTime.equals(other$updateTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViidRegisterSendDTO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $registerId = getRegisterId();
        result = result * 59 + (($registerId == null) ? 0 : $registerId.hashCode());
        Object $deviceId = getDeviceId();
        result = result * 59 + (($deviceId == null) ? 0 : $deviceId.hashCode());
        Object $appId = getAppId();
        result = result * 59 + (($appId == null) ? 0 : $appId.hashCode());
        Object $systemName = getSystemName();
        result = result * 59 + (($systemName == null) ? 0 : $systemName.hashCode());
        Object $httpProtocol = getHttpProtocol();
        result = result * 59 + (($httpProtocol == null) ? 0 : $httpProtocol.hashCode());
        Object $ip = getIp();
        result = result * 59 + (($ip == null) ? 0 : $ip.hashCode());
        Object $port = getPort();
        result = result * 59 + (($port == null) ? 0 : $port.hashCode());
        Object $authType = getAuthType();
        result = result * 59 + (($authType == null) ? 0 : $authType.hashCode());
        Object $status = getStatus();
        result = result * 59 + (($status == null) ? 0 : $status.hashCode());
        Object $sendUserName = getSendUserName();
        result = result * 59 + (($sendUserName == null) ? 0 : $sendUserName.hashCode());
        Object $sendPassWord = getSendPassWord();
        result = result * 59 + (($sendPassWord == null) ? 0 : $sendPassWord.hashCode());
        Object $sendRegisterStatus = getSendRegisterStatus();
        result = result * 59 + (($sendRegisterStatus == null) ? 0 : $sendRegisterStatus.hashCode());
        Object $sendKeepaliveStatus = getSendKeepaliveStatus();
        result = result * 59 + (($sendKeepaliveStatus == null) ? 0 : $sendKeepaliveStatus.hashCode());
        Object $sendRegisterTime = getSendRegisterTime();
        result = result * 59 + (($sendRegisterTime == null) ? 0 : $sendRegisterTime.hashCode());
        Object $sendKeepaliveTime = getSendKeepaliveTime();
        result = result * 59 + (($sendKeepaliveTime == null) ? 0 : $sendKeepaliveTime.hashCode());
        Object $receiveUserName = getReceiveUserName();
        result = result * 59 + (($receiveUserName == null) ? 0 : $receiveUserName.hashCode());
        Object $receivePassWord = getReceivePassWord();
        result = result * 59 + (($receivePassWord == null) ? 0 : $receivePassWord.hashCode());
        Object $receiveRegisterStatus = getReceiveRegisterStatus();
        result = result * 59 + (($receiveRegisterStatus == null) ? 0 : $receiveRegisterStatus.hashCode());
        Object $receiveKeepaliveStatus = getReceiveKeepaliveStatus();
        result = result * 59 + (($receiveKeepaliveStatus == null) ? 0 : $receiveKeepaliveStatus.hashCode());
        Object $receiveRegisterTime = getReceiveRegisterTime();
        result = result * 59 + (($receiveRegisterTime == null) ? 0 : $receiveRegisterTime.hashCode());
        Object $receiveKeepaliveTime = getReceiveKeepaliveTime();
        result = result * 59 + (($receiveKeepaliveTime == null) ? 0 : $receiveKeepaliveTime.hashCode());
        Object $registerExtField1 = getRegisterExtField1();
        result = result * 59 + (($registerExtField1 == null) ? 0 : $registerExtField1.hashCode());
        Object $registerExtField2 = getRegisterExtField2();
        result = result * 59 + (($registerExtField2 == null) ? 0 : $registerExtField2.hashCode());
        Object $registerExtField3 = getRegisterExtField3();
        result = result * 59 + (($registerExtField3 == null) ? 0 : $registerExtField3.hashCode());
        Object $createTime = getCreateTime();
        result = result * 59 + (($createTime == null) ? 0 : $createTime.hashCode());
        Object $updateTime = getUpdateTime();
        return result * 59 + (($updateTime == null) ? 0 : $updateTime.hashCode());
    }

    public String toString() {
        return "ViidRegisterSendDTO(registerId=" + getRegisterId() + ", deviceId=" + getDeviceId() + ", appId=" + getAppId() + ", systemName=" + getSystemName() + ", httpProtocol=" + getHttpProtocol() + ", ip=" + getIp() + ", port=" + getPort() + ", authType=" + getAuthType() + ", status=" + getStatus() + ", sendUserName=" + getSendUserName() + ", sendPassWord=" + getSendPassWord() + ", sendRegisterStatus=" + getSendRegisterStatus() + ", sendKeepaliveStatus=" + getSendKeepaliveStatus() + ", sendRegisterTime=" + getSendRegisterTime() + ", sendKeepaliveTime=" + getSendKeepaliveTime() + ", receiveUserName=" + getReceiveUserName() + ", receivePassWord=" + getReceivePassWord() + ", receiveRegisterStatus=" + getReceiveRegisterStatus() + ", receiveKeepaliveStatus=" + getReceiveKeepaliveStatus() + ", receiveRegisterTime=" + getReceiveRegisterTime() + ", receiveKeepaliveTime=" + getReceiveKeepaliveTime() + ", registerExtField1=" + getRegisterExtField1() + ", registerExtField2=" + getRegisterExtField2() + ", registerExtField3=" + getRegisterExtField3() + ", createTime=" + getCreateTime() + ", updateTime=" + getUpdateTime() + ")";
    }

    public String getRegisterId() {
        return this.registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getHttpProtocol() {
        return this.httpProtocol;
    }

    public void setHttpProtocol(String httpProtocol) {
        this.httpProtocol = httpProtocol;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public String getSendUserName() {
        return this.sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getSendPassWord() {
        return this.sendPassWord;
    }

    public void setSendPassWord(String sendPassWord) {
        this.sendPassWord = sendPassWord;
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

    public Long getSendRegisterTime() {
        return this.sendRegisterTime;
    }

    public void setSendRegisterTime(Long sendRegisterTime) {
        this.sendRegisterTime = sendRegisterTime;
    }

    public Long getSendKeepaliveTime() {
        return this.sendKeepaliveTime;
    }

    public void setSendKeepaliveTime(Long sendKeepaliveTime) {
        this.sendKeepaliveTime = sendKeepaliveTime;
    }

    public String getReceiveUserName() {
        return this.receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getReceivePassWord() {
        return this.receivePassWord;
    }

    public void setReceivePassWord(String receivePassWord) {
        this.receivePassWord = receivePassWord;
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

    public Long getReceiveRegisterTime() {
        return this.receiveRegisterTime;
    }

    public void setReceiveRegisterTime(Long receiveRegisterTime) {
        this.receiveRegisterTime = receiveRegisterTime;
    }

    public Long getReceiveKeepaliveTime() {
        return this.receiveKeepaliveTime;
    }

    public void setReceiveKeepaliveTime(Long receiveKeepaliveTime) {
        this.receiveKeepaliveTime = receiveKeepaliveTime;
    }

    public String getRegisterExtField1() {
        return this.registerExtField1;
    }

    public void setRegisterExtField1(String registerExtField1) {
        this.registerExtField1 = registerExtField1;
    }

    public String getRegisterExtField2() {
        return this.registerExtField2;
    }

    public void setRegisterExtField2(String registerExtField2) {
        this.registerExtField2 = registerExtField2;
    }

    public String getRegisterExtField3() {
        return this.registerExtField3;
    }

    public void setRegisterExtField3(String registerExtField3) {
        this.registerExtField3 = registerExtField3;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
