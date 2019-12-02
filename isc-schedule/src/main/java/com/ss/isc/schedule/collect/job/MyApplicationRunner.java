package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.baseinfo.common.job.StockSimpleJob;
import com.ss.isc.schedule.st.job.PeopleDataLookDayJob;
import com.ss.isc.schedule.st.job.PerceptDataLookDayJob;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.autoconfigure.JobProperties;
import com.ss.isc.util.constant.CacheConstant;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.utils.DeviceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
/**
* 定时任务启动类
* @author chao
* @create 2019/9/10
* @email lishuangchao@ss-cas.com
**/
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private StockSimpleJob stockSimpleJob;
    @Autowired
    public JedisUtil jedisUtil;

    @Override
    public void run(ApplicationArguments var1) throws Exception {
        int tc = JobProperties.getShardingTotalCount();
        String sp = JobProperties.getShardingItemParameters();

        this.jedisUtil.del(CacheConstant.REDIS_KEY_DEVICEIDS_LEAVE);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_DEVICEIDS_ADD);
        this.jedisUtil.del(CacheConstant.TASK_KEY + DateUtil.getYesterdayCurrentDay());
        this.jedisUtil.del(CacheConstant.REDIS_KEY_DEVICEIDS_ADD);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_REGION_TREE);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_VEHICLE_TOLLGATE);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_VILLAGE_FACEDB);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_CAMERA);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_ALL_VILLAGE);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_USERPERMISSION);
        this.jedisUtil.del(CacheConstant.REDIS_KEY_USER_AUTHORITY);


        this.stockSimpleJob.addSimpleJobScheduler(AnalysisTaskJob.class, JobProperties.getAnalysisTaskJob(), tc, sp, true);


        this.stockSimpleJob.addSimpleJobScheduler(AnalysisTaskStateJob.class, JobProperties.getAnalysisTaskStateJob(), tc, sp, false);

        //疑似新增数据同步定时任务
        this.stockSimpleJob.addSimpleJobScheduler(AnalysisAddJob.class, JobProperties.getAnalysisAddJob(), tc, sp, true);

        //疑似离开数据同步定时任务
        this.stockSimpleJob.addSimpleJobScheduler(PeopleDiscoveryLeave.class, JobProperties.getPeopleDiscoveryLeave(), tc, sp, true);

        //同行人员数据同步定时任务
        this.stockSimpleJob.addSimpleJobScheduler(PeopleWithJob.class, JobProperties.getPeopleWithJob(), tc, sp, true);

        //夜间高频数据同步定时任务
        this.stockSimpleJob.addSimpleJobScheduler(AnalysisFrequencyNightJob.class, JobProperties.getAnalysisFrequencyNightJob(), tc, sp, true);

        //敏感通行数据同步定时任务
        this.stockSimpleJob.addSimpleJobScheduler(AnalysisSensitiveJob.class, JobProperties.getAnalysisSensitiveJob(), tc, sp, true);

        // 人车7天数据
        //this.stockSimpleJob.addSimpleJobScheduler(HomePagePushJob.class, JobProperties.getHomePagePushJob(), tc, sp, false);

        // 实时统计页面
        //this.stockSimpleJob.addSimpleJobScheduler(HomePageRealTimeJob.class, JobProperties.getHomePageRealTimeJob(), tc, sp, false);

        // 实时预警统计推送
        //this.stockSimpleJob.addSimpleJobScheduler(EventHandlingJob.class, JobProperties.getEventHandlingJob(), tc, sp, false);


        this.stockSimpleJob.addSimpleJobScheduler(DataCleanJob.class, JobProperties.getDataCleanJob(), tc, sp, true);


        this.stockSimpleJob.addSimpleJobScheduler(DeleteRedisJob.class, JobProperties.getDeleteRedisJob(), tc, sp, true);


        this.stockSimpleJob.addSimpleJobScheduler(CaptureStatisticsJob.class, JobProperties.getCaptureStatisticsJob(), tc, sp, true);


        this.stockSimpleJob.addSimpleJobScheduler(AnalysisFrequencyPersonJob.class, JobProperties.getAnalysisFrequencyPersonJob(), tc, sp, true);


        this.stockSimpleJob.addSimpleJobScheduler(VehicleDiscoveryJob.class, JobProperties.getVehicleDiscovery(), tc, sp, true);


        this.stockSimpleJob.addSimpleJobScheduler(VehicleLeaveJob.class, JobProperties.getVehicleLeaveJob(), tc, sp, true);


        this.stockSimpleJob.addSimpleJobScheduler(VehicleRetationJob.class, JobProperties.getVehicleRetationJob(), tc, sp, true);

        //照片删除定时任务
        this.stockSimpleJob.addSimpleJobScheduler(DeletePictruesJob.class, DeviceUtil.getDeletePictruesJob(), tc, sp, true);

        // 视图库连接注册保活任务
        //this.stockSimpleJob.addSimpleJobScheduler(ViidKeepConnectJob.class, ViidProperties.getKeepConnectJob(), tc, sp, false);

        /*************实有人口*************************/
        //实有人口-日统计
        this.stockSimpleJob.addSimpleJobScheduler(PeopleDataLookDayJob.class, JobProperties.getDataLookDay(), tc, sp, true);

        //实有人口-周统计
        //this.stockSimpleJob.addSimpleJobScheduler(PeopleDataLookWeekJob.class, JobProperties.getDataLookWeek(), tc, sp, true);

        //实有人口-月统计
        //this.stockSimpleJob.addSimpleJobScheduler(PeopleDataLookMouthJob.class, JobProperties.getDataLookMouth(), tc, sp, true);

        /*************实有房屋*************************/
        //实有房屋-日统计
        //this.stockSimpleJob.addSimpleJobScheduler(HouseDataLookDayJob.class, JobProperties.getDataLookDay(), tc, sp, true);

        //实有房屋-周统计
        //this.stockSimpleJob.addSimpleJobScheduler(HouseDataLookWeekJob.class, JobProperties.getDataLookWeek(), tc, sp, true);

        //实有房屋-月统计
        //this.stockSimpleJob.addSimpleJobScheduler(HouseDataLookMouthJob.class, JobProperties.getDataLookMouth(), tc, sp, true);

        /*************感知增量*************************/
        //过车-日统计
        this.stockSimpleJob.addSimpleJobScheduler(PerceptDataLookDayJob.class, JobProperties.getDataLookDay(), tc, sp, true);

        //过车-周统计
        //this.stockSimpleJob.addSimpleJobScheduler(PerceptDataLookWeekJob.class, JobProperties.getDataLookWeek(), tc, sp, true);

        //过车-月统计
        //this.stockSimpleJob.addSimpleJobScheduler(PerceptDataLookMouthJob.class, JobProperties.getDataLookMouth(), tc, sp, true);

        /*************感知增量*************************/
        //人脸-统计
        //this.stockSimpleJob.addSimpleJobScheduler(FaceDataLookDayJob.class, JobProperties.getDataLookDay(), tc, sp, true);
    }

}
