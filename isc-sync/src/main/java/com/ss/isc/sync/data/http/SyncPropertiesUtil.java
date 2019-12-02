package com.ss.isc.sync.data.http;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "system.pram")
public class SyncPropertiesUtil {

    private static String oshttp;
    private static String appId;
    private static String appSecret;
    private static String terminalhttp;
    private static String ischttp;

    public static String getOshttp() {
        return oshttp;
    }


    public static void setOshttp(String oshttp) {
        SyncPropertiesUtil.oshttp = oshttp;
    }


    public static String getAppId() {
        return appId;
    }


    public static void setAppId(String appId) {
        SyncPropertiesUtil.appId = appId;
    }


    public static String getAppSecret() {
        return appSecret;
    }


    public static void setAppSecret(String appSecret) {
        SyncPropertiesUtil.appSecret = appSecret;
    }


    public static String getTerminalhttp() {
        return terminalhttp;
    }


    public static void setTerminalhttp(String terminalhttp) {
        SyncPropertiesUtil.terminalhttp = terminalhttp;
    }


    public static String getIschttp() {
        return ischttp;
    }


    public static void setIschttp(String ischttp) {
        SyncPropertiesUtil.ischttp = ischttp;
    }

}
