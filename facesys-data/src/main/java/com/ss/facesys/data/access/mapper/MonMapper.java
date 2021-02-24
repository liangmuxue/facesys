package com.ss.facesys.data.access.mapper;

import com.ss.facesys.data.access.common.dto.MonitorTask;
import com.ss.facesys.data.access.common.web.MonVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MonMapper extends SsMapper<MonitorTask> {

    //查询此库下未删除的任务是否存在
    List<MonitorTask> selExistMonTask(MonVO para);
}
