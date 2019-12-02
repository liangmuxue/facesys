package com.ss.facesys.data.sequence.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SequenceMapper {
  Long nextvalue(String paramString);
}
