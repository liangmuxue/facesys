package com.ss.isc.util.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "elaticjob")
public class JobProperties {

    private static int shardingTotalCount;
    private static String shardingItemParameters;
    private static String analysisAddJob;
    private static String analysisFrequencyNightJob;
    private static String analysisSensitiveJob;
    private static String analysisFrequencyPersonJob;
    private static String analysisTaskJob;
    private static String analysisTaskStateJob;
    private static String captureStatisticsJob;
    private static String dataCleanJob;
    private static String deleteRedisJob;
    private static String eventHandlingJob;
    private static String homePagePushJob;
    private static String homePageRealTimeJob;
    private static String peopleDiscoveryLeave;
    private static String peopleWithJob;
    private static String vehicleDiscovery;
    private static String vehicleLeaveJob;
    private static String vehicleRetationJob;
    private static String dataLookDay;
    private static String dataLookMouth;
    private static String dataLookWeek;

    public static int getShardingTotalCount() {
        return shardingTotalCount;
    }


    public static void setShardingTotalCount(int shardingTotalCount) {
        JobProperties.shardingTotalCount = shardingTotalCount;
    }


    public static String getShardingItemParameters() {
        return shardingItemParameters;
    }


    public static void setShardingItemParameters(String shardingItemParameters) {
        JobProperties.shardingItemParameters = shardingItemParameters;
    }


    public static String getAnalysisAddJob() {
        return analysisAddJob;
    }


    public static void setAnalysisAddJob(String analysisAddJob) {
        JobProperties.analysisAddJob = analysisAddJob;
    }


    public static String getAnalysisFrequencyPersonJob() {
        return analysisFrequencyPersonJob;
    }


    public static void setAnalysisFrequencyPersonJob(String analysisFrequencyPersonJob) {
        JobProperties.analysisFrequencyPersonJob = analysisFrequencyPersonJob;
    }


    public static String getAnalysisTaskJob() {
        return analysisTaskJob;
    }


    public static void setAnalysisTaskJob(String analysisTaskJob) {
        JobProperties.analysisTaskJob = analysisTaskJob;
    }


    public static String getAnalysisTaskStateJob() {
        return analysisTaskStateJob;
    }


    public static void setAnalysisTaskStateJob(String analysisTaskStateJob) {
        JobProperties.analysisTaskStateJob = analysisTaskStateJob;
    }


    public static String getCaptureStatisticsJob() {
        return captureStatisticsJob;
    }


    public static void setCaptureStatisticsJob(String captureStatisticsJob) {
        JobProperties.captureStatisticsJob = captureStatisticsJob;
    }


    public static String getDataCleanJob() {
        return dataCleanJob;
    }


    public static void setDataCleanJob(String dataCleanJob) {
        JobProperties.dataCleanJob = dataCleanJob;
    }


    public static String getDeleteRedisJob() {
        return deleteRedisJob;
    }


    public static void setDeleteRedisJob(String deleteRedisJob) {
        JobProperties.deleteRedisJob = deleteRedisJob;
    }


    public static String getEventHandlingJob() {
        return eventHandlingJob;
    }


    public static void setEventHandlingJob(String eventHandlingJob) {
        JobProperties.eventHandlingJob = eventHandlingJob;
    }


    public static String getHomePagePushJob() {
        return homePagePushJob;
    }


    public static void setHomePagePushJob(String homePagePushJob) {
        JobProperties.homePagePushJob = homePagePushJob;
    }


    public static String getHomePageRealTimeJob() {
        return homePageRealTimeJob;
    }


    public static void setHomePageRealTimeJob(String homePageRealTimeJob) {
        JobProperties.homePageRealTimeJob = homePageRealTimeJob;
    }


    public static String getPeopleDiscoveryLeave() {
        return peopleDiscoveryLeave;
    }


    public static void setPeopleDiscoveryLeave(String peopleDiscoveryLeave) {
        JobProperties.peopleDiscoveryLeave = peopleDiscoveryLeave;
    }


    public static String getPeopleWithJob() {
        return peopleWithJob;
    }


    public static void setPeopleWithJob(String peopleWithJob) {
        JobProperties.peopleWithJob = peopleWithJob;
    }


    public static String getVehicleDiscovery() {
        return vehicleDiscovery;
    }


    public static void setVehicleDiscovery(String vehicleDiscovery) {
        JobProperties.vehicleDiscovery = vehicleDiscovery;
    }


    public static String getVehicleLeaveJob() {
        return vehicleLeaveJob;
    }


    public static void setVehicleLeaveJob(String vehicleLeaveJob) {
        JobProperties.vehicleLeaveJob = vehicleLeaveJob;
    }


    public static String getVehicleRetationJob() {
        return vehicleRetationJob;
    }


    public static void setVehicleRetationJob(String vehicleRetationJob) {
        JobProperties.vehicleRetationJob = vehicleRetationJob;
    }

    public static String getAnalysisFrequencyNightJob() {
        return analysisFrequencyNightJob;
    }

    public static void setAnalysisFrequencyNightJob(String analysisFrequencyNightJob) {
        JobProperties.analysisFrequencyNightJob = analysisFrequencyNightJob;
    }

    public static String getAnalysisSensitiveJob() {
        return analysisSensitiveJob;
    }

    public static void setAnalysisSensitiveJob(String analysisSensitiveJob) {
        JobProperties.analysisSensitiveJob = analysisSensitiveJob;
    }

    public static String getDataLookDay() {
        return dataLookDay;
    }

    public static void setDataLookDay(String dataLookDay) {
        JobProperties.dataLookDay = dataLookDay;
    }

    public static String getDataLookMouth() {
        return dataLookMouth;
    }

    public static void setDataLookMouth(String dataLookMouth) {
        JobProperties.dataLookMouth = dataLookMouth;
    }

    public static String getDataLookWeek() {
        return dataLookWeek;
    }

    public static void setDataLookWeek(String dataLookWeek) {
        JobProperties.dataLookWeek = dataLookWeek;
    }
}
