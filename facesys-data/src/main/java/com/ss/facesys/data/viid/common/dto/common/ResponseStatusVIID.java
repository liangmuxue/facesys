package com.ss.facesys.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import static com.ss.facesys.util.OapDateUtil.getFormat;

public class ResponseStatusVIID {

    @JSONField(name = "RequestURL")
    private String RequestURL;
    @JSONField(name = "StatusCode")
    private String StatusCode;
    @JSONField(name = "StatusString")
    private String StatusString;
    @JSONField(name = "Id")
    private String Id;
    @JSONField(name = "LocalTime")
    private String LocalTime;

    public ResponseStatusVIID() {
    }

    public ResponseStatusVIID(String RequestURL, String StatusCode, String StatusString, String Id) {
        this.RequestURL = RequestURL;
        this.StatusCode = StatusCode;
        this.StatusString = StatusString;
        this.Id = Id;
        Date date = new Date(System.currentTimeMillis());
        this.LocalTime = getFormat("yyyyMMddHHmmss").format(date);
    }

    public ResponseStatusVIID(String RequestURL, String StatusCode, String StatusString) {
        this.RequestURL = RequestURL;
        this.StatusCode = StatusCode;
        this.StatusString = StatusString;
        Date date = new Date(System.currentTimeMillis());
        this.LocalTime = getFormat("yyyyMMddHHmmss").format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResponseStatusVIID)) {
            return false;
        }
        ResponseStatusVIID other = (ResponseStatusVIID) o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$RequestURL = getRequestURL(), other$RequestURL = other.getRequestURL();
        if ((this$RequestURL == null) ? (other$RequestURL != null)
                : !this$RequestURL.equals(other$RequestURL)) {
            return false;
        }
        Object this$StatusCode = getStatusCode(), other$StatusCode = other.getStatusCode();
        if ((this$StatusCode == null) ? (other$StatusCode != null)
                : !this$StatusCode.equals(other$StatusCode)) {
            return false;
        }
        Object this$StatusString = getStatusString(), other$StatusString = other.getStatusString();
        if ((this$StatusString == null) ? (other$StatusString != null)
                : !this$StatusString.equals(other$StatusString)) {
            return false;
        }
        Object this$Id = getId(), other$Id = other.getId();
        if ((this$Id == null) ? (other$Id != null) : !this$Id.equals(other$Id)) {
            return false;
        }
        Object this$LocalTime = getLocalTime(), other$LocalTime = other.getLocalTime();
        return !((this$LocalTime == null) ? (other$LocalTime != null)
                : !this$LocalTime.equals(other$LocalTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResponseStatusVIID;
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $RequestURL = getRequestURL();
        result = result * 59 + (($RequestURL == null) ? 0 : $RequestURL.hashCode());
        Object $StatusCode = getStatusCode();
        result = result * 59 + (($StatusCode == null) ? 0 : $StatusCode.hashCode());
        Object $StatusString = getStatusString();
        result = result * 59 + (($StatusString == null) ? 0 : $StatusString.hashCode());
        Object $Id = getId();
        result = result * 59 + (($Id == null) ? 0 : $Id.hashCode());
        Object $LocalTime = getLocalTime();
        return result * 59 + (($LocalTime == null) ? 0 : $LocalTime.hashCode());
    }

    @Override
    public String toString() {
        return "ResponseStatusVIID(RequestURL=" + getRequestURL() + ", StatusCode=" + getStatusCode()
                + ", StatusString=" + getStatusString() + ", Id=" + getId() + ", LocalTime="
                + getLocalTime() + ")";
    }

    public String getRequestURL() {
        return this.RequestURL;
    }

    public void setRequestURL(String RequestURL) {
        this.RequestURL = RequestURL;
    }

    public String getStatusCode() {
        return this.StatusCode;
    }

    public void setStatusCode(String StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getStatusString() {
        return this.StatusString;
    }

    public void setStatusString(String StatusString) {
        this.StatusString = StatusString;
    }

    public String getId() {
        return this.Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getLocalTime() {
        return this.LocalTime;
    }

    public void setLocalTime(String LocalTime) {
        this.LocalTime = LocalTime;
    }
}
