package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SnapRecordMapper extends SsMapper<SnapRecord> {

}
