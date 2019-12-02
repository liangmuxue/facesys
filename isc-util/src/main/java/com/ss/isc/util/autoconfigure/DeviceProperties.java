package com.ss.isc.util.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "local.camera")
public class DeviceProperties {

    private static String socketServerUri;
    private static String cameraCaptureUrl;
    private static String deviceUrl;
    private static String devicePushFlowUrl;
    private static String deviceCutFlowUrl;
    private static String devicePushFlowStopUrl;
    private static String deviceCutFlowStopUrl;
    private static String cutFlowAgentUrl;

    public static String getSocketServerUri() {
        return socketServerUri;
    }

    public static void setSocketServerUri(String socketServerUri) {
        DeviceProperties.socketServerUri = socketServerUri;
    }

    public static String getCameraCaptureUrl() {
        return cameraCaptureUrl;
    }

    public static void setCameraCaptureUrl(String cameraCaptureUrl) {
        DeviceProperties.cameraCaptureUrl = cameraCaptureUrl;
    }

    public static String getDeviceUrl() {
        return deviceUrl;
    }

    public static void setDeviceUrl(String deviceUrl) {
        DeviceProperties.deviceUrl = deviceUrl;
    }

    public static String getDevicePushFlowUrl() {
        return devicePushFlowUrl;
    }

    public static void setDevicePushFlowUrl(String devicePushFlowUrl) {
        DeviceProperties.devicePushFlowUrl = devicePushFlowUrl;
    }

    public static String getDeviceCutFlowUrl() {
        return deviceCutFlowUrl;
    }

    public static void setDeviceCutFlowUrl(String deviceCutFlowUrl) {
        DeviceProperties.deviceCutFlowUrl = deviceCutFlowUrl;
    }

    public static String getDevicePushFlowStopUrl() {
        return devicePushFlowStopUrl;
    }

    public static void setDevicePushFlowStopUrl(String devicePushFlowStopUrl) {
        DeviceProperties.devicePushFlowStopUrl = devicePushFlowStopUrl;
    }

    public static String getDeviceCutFlowStopUrl() {
        return deviceCutFlowStopUrl;
    }

    public static void setDeviceCutFlowStopUrl(String deviceCutFlowStopUrl) {
        DeviceProperties.deviceCutFlowStopUrl = deviceCutFlowStopUrl;
    }

    public static String getCutFlowAgentUrl() {
        return cutFlowAgentUrl;
    }

    public static void setCutFlowAgentUrl(String cutFlowAgentUrl) {
        DeviceProperties.cutFlowAgentUrl = cutFlowAgentUrl;
    }
}
