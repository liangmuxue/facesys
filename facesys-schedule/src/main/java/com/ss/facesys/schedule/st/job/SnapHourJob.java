package com.ss.facesys.schedule.st.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ss.facesys.data.statistic.client.SnapHourService;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.SpringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SnapHourJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(SnapHourJob.class);
    private SnapHourService snapHourService = SpringUtil.getBean(SnapHourService.class);

    @Override
    public void execute(ShardingContext shardingContext) {

        LOG.info("定时任务SnapHourJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        int n=1;//n天前
        for(int dayNum=1;dayNum <= n;dayNum++){
            //每天数据
            this.snapHourService.selSnapHourJob(dayNum);
        }
    }
}
