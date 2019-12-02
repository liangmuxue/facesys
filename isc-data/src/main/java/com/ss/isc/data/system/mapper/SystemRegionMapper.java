package com.ss.isc.data.system.mapper;

import com.ss.isc.data.system.common.model.Region;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemRegionMapper extends CWMapper<Region> {
  List<Region> findParentList(@Param("regionCode") String paramString);
  
  List<String> getCameraIds(@Param("userId") String paramString);
}
