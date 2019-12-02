package com.ss.isc.data.trail.mapper;

import com.ss.isc.data.trail.common.dto.WifiCollectDataDTO;
import com.ss.isc.data.trail.common.model.WifiCollectData;
import com.ss.isc.data.trail.common.web.WifiCollectDataVO;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WifiCollectDataMapper extends CWMapper<WifiCollectData> {
  List<WifiCollectDataDTO> macTrajectory(WifiCollectDataVO paramWifiCollectDataVO);
}
