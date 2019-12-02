package com.ss.isc.util.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ftp")
public class FtpProperties {

    private static boolean enabled;
    private static int initialSize;
    private static int bufferSize;
    private static int port;
    private static String localFilePath;
    private static String intranetHost;
    private static String intranetPort;
    private static String intranetUser;
    private static String intranetPwd;
    private static String intranetFilePath;
    private static boolean isActiveModel;

    public static boolean isEnabled() {
        return enabled;
    }


    public static void setEnabled(boolean enabled) {
        FtpProperties.enabled = enabled;
    }


    public static int getInitialSize() {
        return initialSize;
    }


    public static void setInitialSize(int initialSize) {
        FtpProperties.initialSize = initialSize;
    }


    public static int getBufferSize() {
        return bufferSize;
    }


    public static void setBufferSize(int bufferSize) {
        FtpProperties.bufferSize = bufferSize;
    }


    public static int getPort() {
        return port;
    }


    public static void setPort(int port) {
        FtpProperties.port = port;
    }


    public static String getLocalFilePath() {
        return localFilePath;
    }


    public static void setLocalFilePath(String localFilePath) {
        FtpProperties.localFilePath = localFilePath;
    }


    public static String getIntranetHost() {
        return intranetHost;
    }


    public static void setIntranetHost(String intranetHost) {
        FtpProperties.intranetHost = intranetHost;
    }


    public static String getIntranetPort() {
        return intranetPort;
    }


    public static void setIntranetPort(String intranetPort) {
        FtpProperties.intranetPort = intranetPort;
    }


    public static String getIntranetUser() {
        return intranetUser;
    }


    public static void setIntranetUser(String intranetUser) {
        FtpProperties.intranetUser = intranetUser;
    }


    public static String getIntranetPwd() {
        return intranetPwd;
    }


    public static void setIntranetPwd(String intranetPwd) {
        FtpProperties.intranetPwd = intranetPwd;
    }


    public static String getIntranetFilePath() {
        return intranetFilePath;
    }


    public static void setIntranetFilePath(String intranetFilePath) {
        FtpProperties.intranetFilePath = intranetFilePath;
    }


    public static boolean isActiveModel() {
        return isActiveModel;
    }


    public static void setActiveModel(boolean isActiveModel) {
        FtpProperties.isActiveModel = isActiveModel;
    }

}
