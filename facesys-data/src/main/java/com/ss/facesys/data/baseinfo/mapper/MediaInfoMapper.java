package com.ss.facesys.data.baseinfo.mapper;

import com.ss.facesys.data.baseinfo.common.model.MediaInfo;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MediaInfoMapper extends SsMapper<MediaInfo> {}
