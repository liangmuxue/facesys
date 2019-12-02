package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.dto.SpecialPersonDTO;
import com.ss.isc.data.collect.common.model.SpecialPerson;
import com.ss.isc.data.collect.common.web.SpecialPersonQuery;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SpecialPersonMapper extends CWMapper<SpecialPerson> {
  int batchCompareSpecialPerson(List<SpecialPerson> paramList);
  
  List<SpecialPerson> querySpecialListByState(@Param("state") Integer paramInteger, @Param("specialType") int paramInt);
  
  List<SpecialPersonDTO> pages(SpecialPersonQuery paramSpecialPersonQuery);
  
  SpecialPerson selectById(@Param("id") String paramString);
  
  Integer update(SpecialPerson paramSpecialPerson);
  
  Integer updateByRecordId(SpecialPerson paramSpecialPerson);
  
  int updateSpecialState(@Param("pId") String paramString, @Param("state") int paramInt);
}
