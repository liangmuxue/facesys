package com.ss.spider.viid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 1400接口拦截配置
 *
 * @author FrancisYs
 * @date 2019/10/25
 * @email yaoshuai@ss-cas.com
 */
@Component
@ConfigurationProperties(prefix = "ss.viid")
public class ViidProperties {

    private static String anonUrls;
    private static String deviceId;
    private static String httpProtocol;
    private static String ip;
    private static String port;
    private static String sendUserName;
    private static String sendPassWord;
    private static String keepConnectJob;

    public static String getAnonUrls() {
        return anonUrls;
    }

    public static void setAnonUrls(String anonUrls) {
        ViidProperties.anonUrls = anonUrls;
    }

    public static String getDeviceId() {
        return deviceId;
    }

    public static void setDeviceId(String deviceId) {
        ViidProperties.deviceId = deviceId;
    }

    public static String getHttpProtocol() {
        return httpProtocol;
    }

    public static void setHttpProtocol(String httpProtocol) {
        ViidProperties.httpProtocol = httpProtocol;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        ViidProperties.ip = ip;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        ViidProperties.port = port;
    }

    public static String getSendUserName() {
        return sendUserName;
    }

    public static void setSendUserName(String sendUserName) {
        ViidProperties.sendUserName = sendUserName;
    }

    public static String getSendPassWord() {
        return sendPassWord;
    }

    public static void setSendPassWord(String sendPassWord) {
        ViidProperties.sendPassWord = sendPassWord;
    }

    public static String getKeepConnectJob() {
        return keepConnectJob;
    }

    public static void setKeepConnectJob(String keepConnectJob) {
        ViidProperties.keepConnectJob = keepConnectJob;
    }
}
