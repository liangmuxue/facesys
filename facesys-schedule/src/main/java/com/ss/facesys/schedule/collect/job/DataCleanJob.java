package com.ss.facesys.schedule.collect.job;

import com.ss.facesys.util.SpringUtil;
import com.ss.facesys.util.jedis.JedisUtil;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;


public class DataCleanJob implements SimpleJob {

    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);


    public void execute(ShardingContext shardingContext) {
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_ADDPERSON"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_LEAVEPERSON"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_FREQUENCY"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_OLDMAN"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_VEHICLE_RETENTION"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_VEHICLE_DISCOVERY"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_VEHICLE_LEAVE"});
        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_ALARM"});
    }

}
