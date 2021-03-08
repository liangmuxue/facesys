package com.ss.facesys.data.statistic.mapper;

import com.ss.facesys.data.access.common.web.AnalysisCountVO;
import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.facesys.data.statistic.common.dto.SnapHour;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SnapHourMapper extends SsMapper<SnapHour> {

    Integer selSnapRecord(SnapRecord snapRecord);

    List<SnapHour> selSnapCount(AnalysisCountVO para);
    List<SnapHour> selDeviceSnapCount(AnalysisCountVO para);

}
