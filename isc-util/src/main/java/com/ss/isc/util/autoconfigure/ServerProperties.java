package com.ss.isc.util.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "server")
public class ServerProperties {

    private String port;
    private String ip;
    private String contextPath;

    public String getPort() {
        return this.port;
    }


    public void setPort(String port) {
        this.port = port;
    }


    public String getIp() {
        return this.ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getContextPath() {
        return this.contextPath;
    }


    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

}
