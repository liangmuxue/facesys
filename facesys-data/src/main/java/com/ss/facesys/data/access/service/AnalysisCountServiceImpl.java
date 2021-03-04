package com.ss.facesys.data.access.service;

import com.ss.facesys.data.access.common.web.AlarmRecordsVO;
import com.ss.facesys.data.access.common.web.AnalysisCountVO;
import com.ss.facesys.data.collect.common.model.AlarmRecord;
import com.ss.facesys.data.statistic.common.dto.AlarmHour;
import com.ss.facesys.data.statistic.mapper.AlarmHourMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisCountServiceImpl {

    @Resource
    private AlarmHourMapper alarmHourMapper;

   public Map<String,Object> selAlarmCount(AnalysisCountVO para){

       int blackSum = 0;
       int strangerSum = 0;
       int gatherSum = 0;
       int inconformitySum = 0;
       Map<String,Object> map = new HashMap<>();
       List<AlarmHour> alarmHours = alarmHourMapper.selAlarmCount(para);
       for (AlarmHour alarmHour : alarmHours) {
           blackSum = alarmHour.getBlackCount() + blackSum;
           strangerSum = alarmHour.getStrangerCount() + strangerSum;
           gatherSum = alarmHour.getGatherCount() + gatherSum;
           inconformitySum = alarmHour.getInconformityCount() + inconformitySum;
       }
       map.put("alarmRecord",alarmHours);
       map.put("blackSum",blackSum);
       map.put("strangerSum",strangerSum);
       map.put("gatherSum",gatherSum);
       map.put("inconformitySum",inconformitySum);
       return map;
   }
}
