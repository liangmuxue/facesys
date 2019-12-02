package com.ss.isc.schedule.st.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ss.isc.data.statistic.client.IdataLookService;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.SpringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据看板定时任务
 * @author 毕继龙 maomaochong
 * @create 2019/10/29
 * @email bijilong@ss-cas.com
 **/
public class PeopleDataLookDayJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(PeopleDataLookDayJob.class);
    private IdataLookService idataLookService = SpringUtil.getBean(IdataLookService.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        LOG.info("定时任务PeopleDataLookDayJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        int n=0;//n天前
        for(int dayNum=0;dayNum <= n;dayNum++){
            //每天数据(近30天)
            this.idataLookService.selThisThirtyDayPeopel(dayNum);
        }

    }
}
