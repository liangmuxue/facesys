package com.ss.facesys.data.system.mapper;

import com.ss.facesys.data.system.common.model.Region;
import com.ss.mapper.SsMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemRegionMapper extends SsMapper<Region> {
  List<Region> findParentList(@Param("regionCode") String paramString);
  
  List<String> getCameraIds(@Param("userId") String paramString);
}
