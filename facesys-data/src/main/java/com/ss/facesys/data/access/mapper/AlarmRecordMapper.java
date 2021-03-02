package com.ss.facesys.data.access.mapper;

import com.ss.facesys.data.collect.common.model.AlarmRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmRecordMapper {

    /**
     * 添加陌生人报警记录
     * @param record
     * @return
     */
    Integer insertStrangerRecord(AlarmRecord record);

    /**
     * 添加黑名单报警记录
     * @param record
     * @return
     */
    Integer insertBlackListRecord(AlarmRecord record);
}
