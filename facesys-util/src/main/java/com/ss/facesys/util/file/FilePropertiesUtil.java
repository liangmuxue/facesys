package com.ss.facesys.util.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "nginx.file")
public class FilePropertiesUtil {

    private static String httpUrl;
    private static String location;
    private static String maxSize;
    private static String nginxImgUrl;
    private static String storageRelativePath;

    public static String getHttpUrl() {
        return httpUrl;
    }


    public static void setHttpUrl(String httpUrl) {
        FilePropertiesUtil.httpUrl = httpUrl;
    }


    public static String getLocation() {
        return location;
    }


    public static void setLocation(String location) {
        FilePropertiesUtil.location = location;
    }


    public static String getMaxSize() {
        return maxSize;
    }


    public static void setMaxSize(String maxSize) {
        FilePropertiesUtil.maxSize = maxSize;
    }


    public static String getNginxImgUrl() {
        return nginxImgUrl;
    }


    public static void setNginxImgUrl(String nginxImgUrl) {
        FilePropertiesUtil.nginxImgUrl = nginxImgUrl;
    }


    public static String getStorageRelativePath() {
        return storageRelativePath;
    }


    public static void setStorageRelativePath(String storageRelativePath) {
        FilePropertiesUtil.storageRelativePath = storageRelativePath;
    }

}
