package com.ss.isc.schedule.collect.job;

import com.ss.isc.util.PropertiesUtil;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;


public class TestJob implements SimpleJob {

    public void execute(ShardingContext shardingContext) {
        System.out.println("Oshttp = " + PropertiesUtil.getOshttp());
    }

}
