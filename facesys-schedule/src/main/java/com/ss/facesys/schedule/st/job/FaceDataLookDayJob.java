package com.ss.facesys.schedule.st.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ss.facesys.data.statistic.client.IdataLookService;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.SpringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据看板定时任务
 * @author 毕继龙 maomaochong
 * @create 2019/10/29
 * @email bijilong@ss-cas.com
 **/
public class FaceDataLookDayJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(FaceDataLookDayJob.class);
    private IdataLookService idataLookService = SpringUtil.getBean(IdataLookService.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        LOG.info("定时任务FaceDataLookDayJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        int n=1;//n天前
        for(int dayNum=1;dayNum <= n;dayNum++){
            //每天数据
            this.idataLookService.selThisThirtyDayFace(dayNum);
        }

    }
}
