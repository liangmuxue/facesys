package com.ss.util.redis.cluster.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterProperties {

    private String nodes;
    private int timeout;
    private int maxAttempts;
    private String state;

    public String getNodes() {
        return this.nodes;
    }


    public void setNodes(String nodes) {
        this.nodes = nodes;
    }


    public int getTimeout() {
        return this.timeout;
    }


    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }


    public int getMaxAttempts() {
        return this.maxAttempts;
    }


    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }


    public String getState() {
        return this.state;
    }


    public void setState(String state) {
        this.state = state;
    }

}
