package com.ss.facesys.data.statistic.mapper;

import com.ss.facesys.data.access.common.web.AnalysisCountVO;
import com.ss.facesys.data.collect.common.model.AlarmRecord;
import com.ss.facesys.data.statistic.common.dto.AlarmHour;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlarmHourMapper extends SsMapper<AlarmHour> {

    List<AlarmRecord> selAlarmRecord(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    List<AlarmHour> selAlarmCount(AnalysisCountVO para);
}
