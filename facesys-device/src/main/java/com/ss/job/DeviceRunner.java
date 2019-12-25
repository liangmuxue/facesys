//package com.ss.job;
//
//import com.ss.facesys.util.DateUtil;
//import com.ss.facesys.util.autoconfigure.JobProperties;
//import com.ss.facesys.util.constant.CacheConstant;
//import com.ss.facesys.util.jedis.JedisUtil;
//import com.ss.utils.DeviceUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
///**
// * 定时任务启动类
// *
// * @author chao
// * @create 2019/9/10
// * @email lishuangchao@ss-cas.com
// **/
//@Component
//public class DeviceRunner implements ApplicationRunner {
//
//    @Autowired
//    private DeviceStockSimpleJob stockSimpleJob;
//    @Autowired
//    public JedisUtil jedisUtil;
//
//    @Override
//    public void run(ApplicationArguments var1) throws Exception {
//        int tc = JobProperties.getShardingTotalCount();
//        String sp = JobProperties.getShardingItemParameters();
//
//        //照片删除定时任务
////        this.stockSimpleJob.addSimpleJobScheduler(DeletePictruesJob.class, DeviceUtil.getDeletePictruesJob(), tc, sp, true);
//    }
//
//}
