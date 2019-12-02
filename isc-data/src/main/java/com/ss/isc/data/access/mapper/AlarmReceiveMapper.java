package com.ss.isc.data.access.mapper;

import com.ss.isc.data.access.common.web.AlarmReceive;
import com.ss.isc.data.access.common.web.RecordAlarmReceive;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmReceiveMapper {

    int insertAlarmReceive(AlarmReceive paramAlarmReceive);

    int insertRecordAlarmTopns(List<RecordAlarmReceive> paramList);

}
