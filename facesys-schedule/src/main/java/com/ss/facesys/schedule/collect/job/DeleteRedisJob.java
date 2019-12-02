package com.ss.facesys.schedule.collect.job;

import com.ss.facesys.util.SpringUtil;
import com.ss.facesys.util.jedis.JedisUtil;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;


public class DeleteRedisJob implements SimpleJob {

    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);


    public void execute(ShardingContext shardingContext) {
        this.jedisUtil.del(new String[]{"KEY_VEHICLE_TODAY"});
        this.jedisUtil.del(new String[]{"KEY_VEHICLE_REALTIME_COUNT"});
        this.jedisUtil.del(new String[]{"KEY_VEHICLE_SEVEN"});
        this.jedisUtil.del(new String[]{"KEY_CAPTURE_TODAY"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_CAPTURE_PEOPLETODAY"});
        this.jedisUtil.del(new String[]{"KEY_CAPTURE_SEVEN"});
        this.jedisUtil.del(new String[]{"KEY_DOORFLOW_TODAY"});
        this.jedisUtil.del(new String[]{"KEY_DOORFLOW_TODAYCOUNT"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_ALARM"});
        this.jedisUtil.del(new String[]{"KEY_WIFI_TODAY"});
        this.jedisUtil.del(new String[]{"KEY_WIFI_SEVEN"});
        this.jedisUtil.del(new String[]{"KEY_WIFI_PEOPLETODAY"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_ADDPERSON"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_LEAVEPERSON"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_FREQUENCY"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_OLDMAN"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_VEHICLE_RETENTION"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_VEHICLE_DISCOVERY"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_VEHICLE_LEAVE"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_ALARM"});
        this.jedisUtil.del(new String[]{"VEHICLE_TODAY_STATISTICS"});
    }

}
