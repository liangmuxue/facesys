package com.ss.isc.sync.data.schedule;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "elaticjob")
public class SyncJobProperties {

    private static int shardingTotalCount;
    private static String shardingItemParameters;
    private static String intranetNetWorkJob;

    public static int getShardingTotalCount() {
        return shardingTotalCount;
    }


    public static void setShardingTotalCount(int shardingTotalCount) {
        SyncJobProperties.shardingTotalCount = shardingTotalCount;
    }


    public static String getShardingItemParameters() {
        return shardingItemParameters;
    }


    public static void setShardingItemParameters(String shardingItemParameters) {
        SyncJobProperties.shardingItemParameters = shardingItemParameters;
    }


    public static String getIntranetNetWorkJob() {
        return intranetNetWorkJob;
    }


    public static void setIntranetNetWorkJob(String intranetNetWorkJob) {
        SyncJobProperties.intranetNetWorkJob = intranetNetWorkJob;
    }

}
