package com.ss.isc.sync.data;

import com.ss.isc.sync.data.job.IntranetNetWork;
import com.ss.isc.sync.data.schedule.StockSimpleJob;
import com.ss.isc.sync.data.schedule.SyncJobProperties;
import com.ss.isc.util.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private StockSimpleJob stockSimpleJob;
    @Autowired
    public JedisUtil jedisUtil;

    public void run(ApplicationArguments var1) throws Exception {
        this.jedisUtil.del(new String[]{"ISC_SYNC_DOOR_FLOW"});
        this.jedisUtil.del(new String[]{"ALL_CAMERA"});

        this.stockSimpleJob.addSimpleJobScheduler((new IntranetNetWork()).getClass(),
                SyncJobProperties.getIntranetNetWorkJob(),
                SyncJobProperties.getShardingTotalCount(),
                SyncJobProperties.getShardingItemParameters(), new Boolean[]{Boolean.valueOf(false)});
    }

}
