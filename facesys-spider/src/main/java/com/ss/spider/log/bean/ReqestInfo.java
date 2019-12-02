package com.ss.spider.log.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class ReqestInfo {

    private String remoteIp;
    private String path;
    private String baseUrl;

    public String getRemoteIp() {
        return this.remoteIp;
    }


    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }


    public String getPath() {
        return this.path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public String getBaseUrl() {
        return this.baseUrl;
    }


    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public String getFullUrl() {
        if (getBaseUrl() == null) {
            return null;
        }
        if (getPath() == null) {
            return getBaseUrl();
        }
        return getBaseUrl().concat(getPath());
    }


    public String toString() {
        return (new ToStringBuilder(this))
                .append("remoteIp", this.remoteIp)
                .append("path", this.path)
                .append("baseUrl", this.baseUrl)
                .toString();
    }

}
