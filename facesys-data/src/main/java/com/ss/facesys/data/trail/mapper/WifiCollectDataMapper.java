package com.ss.facesys.data.trail.mapper;

import com.ss.facesys.data.trail.common.dto.WifiCollectDataDTO;
import com.ss.facesys.data.trail.common.model.WifiCollectData;
import com.ss.facesys.data.trail.common.web.WifiCollectDataVO;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WifiCollectDataMapper extends SsMapper<WifiCollectData> {
  List<WifiCollectDataDTO> macTrajectory(WifiCollectDataVO paramWifiCollectDataVO);
}
