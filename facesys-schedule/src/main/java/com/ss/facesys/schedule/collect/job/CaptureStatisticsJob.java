package com.ss.facesys.schedule.collect.job;

import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.web.CaptureStatistics;
import com.ss.facesys.data.collect.client.ICaptureService;
import com.ss.facesys.data.collect.common.model.CaptureCount;
import com.ss.facesys.data.resource.client.ICommunityResourceService;
import com.ss.facesys.data.resource.common.dto.CameraCaptureDTO;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.SpringUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.jedis.JedisUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.j7cai.common.util.JsonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CaptureStatisticsJob implements SimpleJob {

    private static final Logger log = LoggerFactory.getLogger(CaptureStatisticsJob.class);


    private ICommunityResourceService communityResourceService = SpringUtil.getBean(ICommunityResourceService.class);
    private IAccessService accessService = SpringUtil.getBean(IAccessService.class);
    private ICaptureService captureService = SpringUtil.getBean(ICaptureService.class);
    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);


    public void execute(ShardingContext shardingContext) {
        log.info("抓拍定时任务开始" + DateUtil.getCurrentSqlTimestampString());
        List<CameraCaptureDTO> list = this.communityResourceService.findCapturedeviceIds();

        Date beginDayOfYesterday = DateUtil.getBeginDayOfYesterday();
        Date endDayOfYesterDay = DateUtil.getEndDayOfYesterDay();

        List<CaptureCount> captureCounts = new ArrayList<CaptureCount>();


        CaptureStatistics captureStatistics = new CaptureStatistics();
        captureStatistics.setCaptureTimeStart(beginDayOfYesterday);
        captureStatistics.setCaptureTimeEnd(endDayOfYesterDay);

        int captureCount = NumberConstant.ZERO.intValue();
        for (CameraCaptureDTO cameraCaptureDTO : list) {
            if (StringUtils.isNotBlank(cameraCaptureDTO.getDeviceIds())) {
                captureStatistics.setDeviceIds(cameraCaptureDTO.getDeviceIds().split(","));

                JSONObject jsonObject = this.accessService.captureStatistics(JsonUtils.getFastjsonFromObject(captureStatistics));
                log.info("欧神抓拍统计接口" + cameraCaptureDTO.getVillageCode() + "小区" + jsonObject.toJSONString());
                JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);
                        captureCount += job.getInteger("captureCount").intValue();
                    }
                }
            }
            captureCounts.add(new CaptureCount(cameraCaptureDTO.getVillageCode(), Integer.valueOf(captureCount), beginDayOfYesterday));
            captureCount = NumberConstant.ZERO.intValue();
        }


        this.captureService.batchInsertCaptureCount(captureCounts);


        this.jedisUtil.del(new String[]{"VILLAGES_CAPTURETOTAL"});


        this.jedisUtil.del(new String[]{"KEY_CAPTURE_ALLDAY"});


        this.jedisUtil.del(new String[]{"CAPTURE_REGISTER_STATISTICS"});

        log.info("抓拍统计定时任务结束" + DateUtil.getCurrentSqlTimestampString());
    }

}
