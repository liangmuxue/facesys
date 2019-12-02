package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.AddPersonWith;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddPersonWithMapper extends SsMapper<AddPersonWith> {
  int batchInsertAddPersonWith(List<AddPersonWith> paramList);
  
  int batchUpdateWithState(List<String> paramList);
  
  int updateWithState(@Param("id") String paramString);
}
