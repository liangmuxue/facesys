package com.ss.spider.interceptor.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "cw.access.resource.filter")
public class CwResourceFilterProperties {

    private String url;
    private String publicUrl;
    private boolean enable = false;

    public String getUrl() {
        return this.url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public boolean isEnable() {
        return this.enable;
    }


    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    public String getPublicUrl() {
        return this.publicUrl;
    }


    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

}
