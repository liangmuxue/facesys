package com.ss.facesys.data.access.service;

import com.ss.facesys.data.access.common.web.AnalysisCountVO;
import com.ss.facesys.data.statistic.common.dto.AlarmHour;
import com.ss.facesys.data.statistic.common.dto.SnapHour;
import com.ss.facesys.data.statistic.mapper.AlarmHourMapper;
import com.ss.facesys.data.statistic.mapper.SnapHourMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalysisCountServiceImpl {

    @Resource
    private AlarmHourMapper alarmHourMapper;
    @Resource
    private SnapHourMapper snapHourMapper;

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

   public Map<String,Object> selSnapCount(AnalysisCountVO para){
       Map<String,Object> map = new HashMap<>();
       List<SnapHour> snapHours = snapHourMapper.selSnapCount(para);
       map.put("snapRecord",snapHours);
       List<SnapHour> snapHoursList = snapHourMapper.selDeviceSnapCount(para);
       snapHoursList = snapHoursList.stream().sorted(Comparator.comparing(SnapHour::getCount).reversed()).collect(Collectors.toList());
       if(snapHoursList.size() > 10){
           map.put("Top",snapHoursList.subList(0,10));
       }else{
           map.put("Top",snapHoursList.subList(0,snapHoursList.size()));
       }
       return map;
   }
}
