package com.ss.spider.log.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "cw.log.job")
public class CwLogJobConfig {

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Integer keepAliveSeconds;
    private Integer queueCapacity;

    public Integer getCorePoolSize() {
        return this.corePoolSize;
    }


    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }


    public Integer getMaxPoolSize() {
        return this.maxPoolSize;
    }


    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }


    public Integer getKeepAliveSeconds() {
        return this.keepAliveSeconds;
    }


    public void setKeepAliveSeconds(Integer keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }


    public Integer getQueueCapacity() {
        return this.queueCapacity;
    }


    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

}
