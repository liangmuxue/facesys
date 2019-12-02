package com.ss.facesys.schedule.st.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ss.facesys.data.statistic.client.IdataLookService;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.SpringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据看板年度数据定时任务
 * @author 毕继龙 maomaochong
 * @create 2019/10/29
 * @email bijilong@ss-cas.com
 **/
public class PeopleDataLookMouthJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(PeopleDataLookMouthJob.class);
    private IdataLookService idataLookService = SpringUtil.getBean(IdataLookService.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        LOG.info("PeopleDataLookMouthJob" + DateUtil.getCurrentSqlTimestampString());
        int n = 1;
        for(int mouthNum = 1;mouthNum <= n;mouthNum++){
            //本月数据
            this.idataLookService.selthisMouthPeopel(mouthNum);
        }
    }
}
