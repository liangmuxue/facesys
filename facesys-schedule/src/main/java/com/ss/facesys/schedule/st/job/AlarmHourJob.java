package com.ss.facesys.schedule.st.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ss.facesys.data.statistic.client.AlarmHourService;
import com.ss.facesys.schedule.collect.job.IpJob;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.SpringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AlarmHourJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(IpJob.class);
    private AlarmHourService alarmHourService = SpringUtil.getBean(AlarmHourService.class);

    @Override
    public void execute(ShardingContext shardingContext) {

        LOG.info("定时任务AlarmHourJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        int n=1;//n天前
        for(int dayNum=1;dayNum <= n;dayNum++){
            //每天数据
            this.alarmHourService.selAlarmHourJob(dayNum);
        }
    }
}
