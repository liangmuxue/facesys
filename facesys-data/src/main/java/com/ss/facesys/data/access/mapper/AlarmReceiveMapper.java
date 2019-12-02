package com.ss.facesys.data.access.mapper;

import com.ss.facesys.data.access.common.web.AlarmReceive;
import com.ss.facesys.data.access.common.web.RecordAlarmReceive;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmReceiveMapper {

    int insertAlarmReceive(AlarmReceive paramAlarmReceive);

    int insertRecordAlarmTopns(List<RecordAlarmReceive> paramList);

}
