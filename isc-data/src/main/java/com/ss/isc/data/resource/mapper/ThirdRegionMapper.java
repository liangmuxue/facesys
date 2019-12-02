package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.model.ThirdRegion;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ThirdRegionMapper extends CWMapper<ThirdRegion> {
  int batchCompareThirdRegion(List<ThirdRegion> paramList);
  
  Integer getRegionTypeByThirdId(@Param("thirdId") String paramString);
}
