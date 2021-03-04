package com.ss.facesys.data.statistic.service;

import com.ss.facesys.data.collect.common.model.AlarmRecord;
import com.ss.facesys.data.statistic.client.AlarmHourService;
import com.ss.facesys.data.statistic.common.dto.AlarmHour;
import com.ss.facesys.data.statistic.mapper.AlarmHourMapper;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.MonitorTypeEnum;
import com.ss.tools.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class AlarmHourServiceImpl implements AlarmHourService {

    @Resource
    private AlarmHourMapper alarmHourMapper;

    @Override
    public void selAlarmHourJob(int dayNum) {
        //获取起始时间和今天的日期，小时
        Long endTime = System.currentTimeMillis();
        Long startTime = endTime - 3600000;
        Date dates = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(dates);
        String date = times.replaceAll("[[\\s-:punct:]]","").substring(0,8);
        String hour = times.replaceAll("[[\\s-:punct:]]","").substring(8,10);
        String zero = "00";
        //零时转成24时，日期为昨天
        if(zero.equals(hour)){
            hour = "24";
            date = DateUtils.getPastDate(1).replaceAll("[[\\s-:punct:]]","");
        }
        //插零
        AlarmHour alarmHour = new AlarmHour();
        alarmHour.setBlackCount(CommonConstant.COMMON_0);
        alarmHour.setStrangerCount(CommonConstant.COMMON_0);
        alarmHour.setGatherCount(CommonConstant.COMMON_0);
        alarmHour.setInconformityCount(CommonConstant.COMMON_0);
        alarmHour.setHour(Integer.valueOf(hour));
        alarmHour.setDate(Integer.valueOf(date));
        alarmHour.setCreateTime(System.currentTimeMillis());
        alarmHourMapper.insertSelective(alarmHour);
        //查询数据
        AlarmHour alarmHourUpd = new AlarmHour();
        alarmHourUpd.setId(alarmHour.getId());
        List<AlarmRecord> alarmRecordList = alarmHourMapper.selAlarmRecord(startTime, endTime);
        for (AlarmRecord alarmRecord : alarmRecordList) {
            if(alarmRecord.getMonitorType().equals(MonitorTypeEnum.BLACK.getCode())){
                alarmHourUpd.setBlackCount(alarmRecord.getCnt());
            }
            if(alarmRecord.getMonitorType().equals(MonitorTypeEnum.STRANGER.getCode())){
                alarmHourUpd.setStrangerCount(alarmRecord.getCnt());
            }
            if(alarmRecord.getMonitorType().equals(MonitorTypeEnum.GATHER.getCode())){
                alarmHourUpd.setGatherCount(alarmRecord.getCnt());
            }
            if(alarmRecord.getMonitorType().equals(MonitorTypeEnum.INCONFORMITY.getCode())){
                alarmHourUpd.setInconformityCount(alarmRecord.getCnt());
            }
        }
        //修改数据
        alarmHourMapper.updateByPrimaryKeySelective(alarmHourUpd);
    }
}
