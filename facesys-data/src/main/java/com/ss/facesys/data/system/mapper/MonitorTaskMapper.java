package com.ss.facesys.data.system.mapper;

import com.ss.facesys.data.access.common.dto.MonitorTask;
import com.ss.facesys.data.system.common.model.AlarmMessage;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonitorTaskMapper extends SsMapper<MonitorTask> {

}
