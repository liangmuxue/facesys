package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.model.AddPersonWith;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddPersonWithMapper extends CWMapper<AddPersonWith> {
  int batchInsertAddPersonWith(List<AddPersonWith> paramList);
  
  int batchUpdateWithState(List<String> paramList);
  
  int updateWithState(@Param("id") String paramString);
}
