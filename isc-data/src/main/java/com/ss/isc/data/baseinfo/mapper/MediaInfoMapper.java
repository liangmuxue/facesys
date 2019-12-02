package com.ss.isc.data.baseinfo.mapper;

import com.ss.isc.data.baseinfo.common.model.MediaInfo;
import com.ss.mapper.CWMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MediaInfoMapper extends CWMapper<MediaInfo> {}
