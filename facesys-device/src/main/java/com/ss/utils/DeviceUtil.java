package com.ss.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "local.camera")
@PropertySource("classpath:device.properties")
public class DeviceUtil {

    private static String remoteUrl;
    private static String ffmpegCommandPath;
    private static String ffprobeCommandPath;
    private static String threadPoolSize;
    private static String threadMaxPoolSize;
    private static String threadAliveTime;
    private static String cutFlowUrl;
    private static String cutFlowAgentUrl;
    private static String deletePictruesJob;
    private static String kafka;

    public static String getRemoteUrl() {
        return remoteUrl;
    }

    public static void setRemoteUrl(String remoteUrl) {
        DeviceUtil.remoteUrl = remoteUrl;
    }

    public static String getFfmpegCommandPath() {
        return ffmpegCommandPath;
    }

    public static void setFfmpegCommandPath(String ffmpegCommandPath) {
        DeviceUtil.ffmpegCommandPath = ffmpegCommandPath;
    }

    public static String getFfprobeCommandPath() {
        return ffprobeCommandPath;
    }

    public static void setFfprobeCommandPath(String ffprobeCommandPath) {
        DeviceUtil.ffprobeCommandPath = ffprobeCommandPath;
    }

    public static String getThreadPoolSize() {
        return threadPoolSize;
    }

    public static void setThreadPoolSize(String threadPoolSize) {
        DeviceUtil.threadPoolSize = threadPoolSize;
    }

    public static String getThreadMaxPoolSize() {
        return threadMaxPoolSize;
    }

    public static void setThreadMaxPoolSize(String threadMaxPoolSize) {
        DeviceUtil.threadMaxPoolSize = threadMaxPoolSize;
    }

    public static String getThreadAliveTime() {
        return threadAliveTime;
    }

    public static void setThreadAliveTime(String threadAliveTime) {
        DeviceUtil.threadAliveTime = threadAliveTime;
    }

    public static String getCutFlowUrl() {
        return cutFlowUrl;
    }

    public static void setCutFlowUrl(String cutFlowUrl) {
        DeviceUtil.cutFlowUrl = cutFlowUrl;
    }

    public static String getCutFlowAgentUrl() {
        return cutFlowAgentUrl;
    }

    public static void setCutFlowAgentUrl(String cutFlowAgentUrl) {
        DeviceUtil.cutFlowAgentUrl = cutFlowAgentUrl;
    }

    public static String getDeletePictruesJob() {
        return deletePictruesJob;
    }

    public static void setDeletePictruesJob(String deletePictruesJob) {
        DeviceUtil.deletePictruesJob = deletePictruesJob;
    }

    public static String getKafka() {
        return kafka;
    }

    public static void setKafka(String kafka) {
        DeviceUtil.kafka = kafka;
    }
}
