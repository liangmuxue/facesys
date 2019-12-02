package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.AddPersonCollect;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddPersonCollectMapper {
  List<AddPersonCollect> queryList(@Param("addPersonIds") List<String> paramList);
  
  AddPersonCollect getCollect(String paramString);
  
  int batchCompareCollect(List<AddPersonCollect> paramList);
  
  AddPersonCollect findCollect(@Param("addPersonId") String paramString, @Param("addPersonJudgedDays") Integer paramInteger);
}
