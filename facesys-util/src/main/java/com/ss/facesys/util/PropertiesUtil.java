package com.ss.facesys.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "system.pram")
public class PropertiesUtil {

    private static boolean local;
    private static String oshttp;
    private static String terminalhttp;
    private static String appId;
    private static String appSecret;
    private static String ischttp;
    private static String vmshttpPort;
    private static String vmsIp;
    private static String vmsPort;
    private static String vmsUsername;
    private static String vmsPassword;
    private static boolean vmsVideo;
    private static boolean addPersonRecogState;
    private static int captureDays;
    private static int personDoorDays;
    private static int warningDays;
    private static int vehicleRrcordDays;
    private static int macRrcordDays;
    private static int vehicleDiscoveryLength;
    private static int vehicleDiscoveryDays;
    private static int vehicleLeaveDays;
    private static int vehicleRetationDays;
    private static String peopleLabel;
    private static int withSecond;
    private static float threShold;
    private static String regionCode;
    private static Integer addPersonJudgedDays;
    private static Integer leaveDays;
    private static Integer frequencyPersonAmount;
    private static Integer frequencyPersonDays;
    private static Integer frequencyPersonDaysAmount;
    private static Integer frequencyNightAmount;
    private static Integer longtimeStayTime;
    private static Integer sensitiveTraffic;
    private static Integer oldMan;
    private static Integer oldManDays;
    private static Long jobSleepTime;
    private static String facedbId;
    private static int taskMaxNum;
    private static Integer housePeoplePageSize;
    private static Integer captureLocal;
    private static Integer captureOcean;
    private static String ftpFile;
    private static String captureFile;
    private static String locelFile;
    private static int threadPoolSize;
    private static int threadMaxPoolSize;
    private static int threadAliveTime;
    private static int oceanOrViid;

    private static int topN;
    private static double threshold;

    private static int dbPrefix;
    private static int dbMagnification;

    private static String sfgohttp;

    private static String captureUrl;

    public static String getCaptureFile() {
        return captureFile;
    }


    public static void setCaptureFile(String captureFile) {
        PropertiesUtil.captureFile = captureFile;
    }


    public static Integer getCaptureLocal() {
        return captureLocal;
    }


    public static void setCaptureLocal(Integer captureLocal) {
        PropertiesUtil.captureLocal = captureLocal;
    }


    public static Integer getCaptureOcean() {
        return captureOcean;
    }


    public static void setCaptureOcean(Integer captureOcean) {
        PropertiesUtil.captureOcean = captureOcean;
    }


    public static boolean isLocal() {
        return local;
    }


    public static void setLocal(boolean local) {
        PropertiesUtil.local = local;
    }


    public static String getOshttp() {
        return oshttp;
    }


    public static void setOshttp(String oshttp) {
        PropertiesUtil.oshttp = oshttp;
    }


    public static String getTerminalhttp() {
        return terminalhttp;
    }


    public static void setTerminalhttp(String terminalhttp) {
        PropertiesUtil.terminalhttp = terminalhttp;
    }


    public static String getAppId() {
        return appId;
    }


    public static void setAppId(String appId) {
        PropertiesUtil.appId = appId;
    }


    public static String getAppSecret() {
        return appSecret;
    }


    public static void setAppSecret(String appSecret) {
        PropertiesUtil.appSecret = appSecret;
    }


    public static String getIschttp() {
        return ischttp;
    }


    public static void setIschttp(String ischttp) {
        PropertiesUtil.ischttp = ischttp;
    }


    public static String getVmshttpPort() {
        return vmshttpPort;
    }


    public static void setVmshttpPort(String vmshttpPort) {
        PropertiesUtil.vmshttpPort = vmshttpPort;
    }


    public static String getVmsIp() {
        return vmsIp;
    }


    public static void setVmsIp(String vmsIp) {
        PropertiesUtil.vmsIp = vmsIp;
    }


    public static String getVmsPort() {
        return vmsPort;
    }


    public static void setVmsPort(String vmsPort) {
        PropertiesUtil.vmsPort = vmsPort;
    }


    public static String getVmsUsername() {
        return vmsUsername;
    }


    public static void setVmsUsername(String vmsUsername) {
        PropertiesUtil.vmsUsername = vmsUsername;
    }


    public static String getVmsPassword() {
        return vmsPassword;
    }


    public static void setVmsPassword(String vmsPassword) {
        PropertiesUtil.vmsPassword = vmsPassword;
    }


    public static boolean isVmsVideo() {
        return vmsVideo;
    }


    public static void setVmsVideo(boolean vmsVideo) {
        PropertiesUtil.vmsVideo = vmsVideo;
    }


    public static boolean isAddPersonRecogState() {
        return addPersonRecogState;
    }


    public static void setAddPersonRecogState(boolean addPersonRecogState) {
        PropertiesUtil.addPersonRecogState = addPersonRecogState;
    }


    public static int getCaptureDays() {
        return captureDays;
    }


    public static void setCaptureDays(int captureDays) {
        PropertiesUtil.captureDays = captureDays;
    }


    public static int getPersonDoorDays() {
        return personDoorDays;
    }


    public static void setPersonDoorDays(int personDoorDays) {
        PropertiesUtil.personDoorDays = personDoorDays;
    }


    public static int getWarningDays() {
        return warningDays;
    }


    public static void setWarningDays(int warningDays) {
        PropertiesUtil.warningDays = warningDays;
    }


    public static int getVehicleRrcordDays() {
        return vehicleRrcordDays;
    }


    public static void setVehicleRrcordDays(int vehicleRrcordDays) {
        PropertiesUtil.vehicleRrcordDays = vehicleRrcordDays;
    }


    public static int getVehicleDiscoveryLength() {
        return vehicleDiscoveryLength;
    }


    public static void setVehicleDiscoveryLength(int vehicleDiscoveryLength) {
        PropertiesUtil.vehicleDiscoveryLength = vehicleDiscoveryLength;
    }


    public static int getVehicleDiscoveryDays() {
        return vehicleDiscoveryDays;
    }


    public static void setVehicleDiscoveryDays(int vehicleDiscoveryDays) {
        PropertiesUtil.vehicleDiscoveryDays = vehicleDiscoveryDays;
    }


    public static int getVehicleLeaveDays() {
        return vehicleLeaveDays;
    }


    public static void setVehicleLeaveDays(int vehicleLeaveDays) {
        PropertiesUtil.vehicleLeaveDays = vehicleLeaveDays;
    }


    public static int getVehicleRetationDays() {
        return vehicleRetationDays;
    }


    public static void setVehicleRetationDays(int vehicleRetationDays) {
        PropertiesUtil.vehicleRetationDays = vehicleRetationDays;
    }


    public static String getPeopleLabel() {
        return peopleLabel;
    }


    public static void setPeopleLabel(String peopleLabel) {
        PropertiesUtil.peopleLabel = peopleLabel;
    }


    public static int getWithSecond() {
        return withSecond;
    }


    public static void setWithSecond(int withSecond) {
        PropertiesUtil.withSecond = withSecond;
    }


    public static String getRegionCode() {
        return regionCode;
    }


    public static void setRegionCode(String regionCode) {
        PropertiesUtil.regionCode = regionCode;
    }


    public static float getThreShold() {
        return threShold;
    }


    public static void setThreShold(float threShold) {
        PropertiesUtil.threShold = threShold;
    }


    public static Integer getAddPersonJudgedDays() {
        return addPersonJudgedDays;
    }


    public static void setAddPersonJudgedDays(Integer addPersonJudgedDays) {
        PropertiesUtil.addPersonJudgedDays = addPersonJudgedDays;
    }


    public static Integer getOldMan() {
        return oldMan;
    }


    public static void setOldMan(Integer oldMan) {
        PropertiesUtil.oldMan = oldMan;
    }


    public static Integer getOldManDays() {
        return oldManDays;
    }


    public static void setOldManDays(Integer oldManDays) {
        PropertiesUtil.oldManDays = oldManDays;
    }


    public static Long getJobSleepTime() {
        return jobSleepTime;
    }


    public static void setJobSleepTime(Long jobSleepTime) {
        PropertiesUtil.jobSleepTime = jobSleepTime;
    }


    public static Integer getLeaveDays() {
        return leaveDays;
    }


    public static void setLeaveDays(Integer leaveDays) {
        PropertiesUtil.leaveDays = leaveDays;
    }


    public static Integer getFrequencyPersonAmount() {
        return frequencyPersonAmount;
    }


    public static void setFrequencyPersonAmount(Integer frequencyPersonAmount) {
        PropertiesUtil.frequencyPersonAmount = frequencyPersonAmount;
    }


    public static Integer getFrequencyPersonDays() {
        return frequencyPersonDays;
    }


    public static void setFrequencyPersonDays(Integer frequencyPersonDays) {
        PropertiesUtil.frequencyPersonDays = frequencyPersonDays;
    }


    public static Integer getFrequencyPersonDaysAmount() {
        return frequencyPersonDaysAmount;
    }


    public static void setFrequencyPersonDaysAmount(Integer frequencyPersonDaysAmount) {
        PropertiesUtil.frequencyPersonDaysAmount = frequencyPersonDaysAmount;
    }


    public static String getFacedbId() {
        return facedbId;
    }


    public static void setFacedbId(String facedbId) {
        PropertiesUtil.facedbId = facedbId;
    }


    public static int getMacRrcordDays() {
        return macRrcordDays;
    }


    public static void setMacRrcordDays(int macRrcordDays) {
        PropertiesUtil.macRrcordDays = macRrcordDays;
    }


    public static int getTaskMaxNum() {
        return taskMaxNum;
    }


    public static void setTaskMaxNum(int taskMaxNum) {
        PropertiesUtil.taskMaxNum = taskMaxNum;
    }


    public static Integer getHousePeoplePageSize() {
        return housePeoplePageSize;
    }


    public static void setHousePeoplePageSize(Integer housePeoplePageSize) {
        PropertiesUtil.housePeoplePageSize = housePeoplePageSize;
    }


    public static String getFtpFile() {
        return ftpFile;
    }


    public static void setFtpFile(String ftpFile) {
        PropertiesUtil.ftpFile = ftpFile;
    }


    public static String getLocelFile() {
        return locelFile;
    }


    public static void setLocelFile(String locelFile) {
        PropertiesUtil.locelFile = locelFile;
    }


    public static int getThreadPoolSize() {
        return threadPoolSize;
    }


    public static void setThreadPoolSize(int threadPoolSize) {
        PropertiesUtil.threadPoolSize = threadPoolSize;
    }


    public static int getThreadMaxPoolSize() {
        return threadMaxPoolSize;
    }


    public static void setThreadMaxPoolSize(int threadMaxPoolSize) {
        PropertiesUtil.threadMaxPoolSize = threadMaxPoolSize;
    }


    public static int getThreadAliveTime() {
        return threadAliveTime;
    }


    public static void setThreadAliveTime(int threadAliveTime) {
        PropertiesUtil.threadAliveTime = threadAliveTime;
    }

    public static Integer getFrequencyNightAmount() {
        return frequencyNightAmount;
    }

    public static void setFrequencyNightAmount(Integer frequencyNightAmount) {
        PropertiesUtil.frequencyNightAmount = frequencyNightAmount;
    }

    public static Integer getLongtimeStayTime() {
        return longtimeStayTime;
    }

    public static void setLongtimeStayTime(Integer longtimeStayTime) {
        PropertiesUtil.longtimeStayTime = longtimeStayTime;
    }

    public static Integer getSensitiveTraffic() {
        return sensitiveTraffic;
    }

    public static void setSensitiveTraffic(Integer sensitiveTraffic) {
        PropertiesUtil.sensitiveTraffic = sensitiveTraffic;
    }

    public static int getOceanOrViid() {
        return oceanOrViid;
    }

    public static void setOceanOrViid(int oceanOrViid) {
        PropertiesUtil.oceanOrViid = oceanOrViid;
    }

    public static int getTopN() {
        return topN;
    }

    public static void setTopN(int topN) {
        PropertiesUtil.topN = topN;
    }

    public static double getThreshold() {
        return threshold;
    }

    public static void setThreshold(double threshold) {
        PropertiesUtil.threshold = threshold;
    }

    public static int getDbPrefix() {
        return dbPrefix;
    }

    public static void setDbPrefix(int dbPrefix) {
        PropertiesUtil.dbPrefix = dbPrefix;
    }

    public static int getDbMagnification() {
        return dbMagnification;
    }

    public static void setDbMagnification(int dbMagnification) {
        PropertiesUtil.dbMagnification = dbMagnification;
    }

    public static String getSfgohttp() {
        return sfgohttp;
    }

    public static void setSfgohttp(String sfgohttp) {
        PropertiesUtil.sfgohttp = sfgohttp;
    }

    public static String getCaptureUrl() {
        return captureUrl;
    }

    public static void setCaptureUrl(String captureUrl) {
        PropertiesUtil.captureUrl = captureUrl;
    }
}
