package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.collect.client.IPeopleService;
import com.ss.isc.data.collect.common.model.FrequencyRecord;
import com.ss.isc.data.collect.common.web.AddPersonDetailQuery;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.SpringUtil;
import com.ss.isc.util.jedis.JedisUtil;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AnalysisFrequencyPersonJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(AnalysisFrequencyPersonJob.class);


    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private IPeopleService peopleService = SpringUtil.getBean(IPeopleService.class);


    public void execute(ShardingContext shardingContext) {
        LOG.info("高频陌生人分析定时任务开始" + DateUtil.getCurrentDayTime());
        Date endTime = DateUtil.getEndDayOfYesterDay();

        int days = PropertiesUtil.getFrequencyPersonDays().intValue();
        String beginDateStr = DateUtil.addDays(DateUtil.getCurrentDay(), -days);
        Date beginTime = null;
        try {
            beginTime = DateUtil.formatDate(beginDateStr);
        } catch (ParseException e) {
            LOG.error(e.toString(), e);
        }
        AddPersonDetailQuery query = new AddPersonDetailQuery();
        query.setCaptureTimeB(beginTime);
        query.setCaptureTimeE(endTime);
        query.setAmount(PropertiesUtil.getFrequencyPersonDaysAmount());
        List<FrequencyRecord> list = this.peopleService.analysisFrequeryRecord(query);

        List<FrequencyRecord> frequencyRecords = new ArrayList<FrequencyRecord>();

        for (FrequencyRecord entity : list) {

            FrequencyRecord fr = this.peopleService.findFrequencyRecordById(entity.getAddPersonId());
            if (fr != null) {
                if (DateUtil.compareDate(fr.getEndTime(), endTime) &&
                        DateUtil.compareDate(beginTime, fr.getBeginTime())) {
                    fr.setBeginTime(beginTime);
                    fr.setEndTime(endTime);
                    fr.setAmount(entity.getAmount());
                    frequencyRecords.add(fr);
                }
                continue;
            }
            frequencyRecords.add(entity);
        }

        if (frequencyRecords.size() > 0) {
            this.peopleService.batchCompareFrequencyRecord(frequencyRecords);
        }


        this.jedisUtil.del(new String[]{"REDIS_KEY_PROCESSTYPE_FREQUENCY"});

        LOG.info("高频陌生人分析定时任务结束" + DateUtil.getCurrentDayTime());
    }

}
