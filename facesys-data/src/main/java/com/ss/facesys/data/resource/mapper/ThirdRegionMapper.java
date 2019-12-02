package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.ThirdRegion;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ThirdRegionMapper extends SsMapper<ThirdRegion> {
  int batchCompareThirdRegion(List<ThirdRegion> paramList);
  
  Integer getRegionTypeByThirdId(@Param("thirdId") String paramString);
}
