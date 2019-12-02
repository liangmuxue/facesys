package com.ss.facesys.data.trail.service;

import com.ss.facesys.data.trail.client.ITrailService;
import com.ss.facesys.data.trail.common.dto.VehicleRecordDTO;
import com.ss.facesys.data.trail.common.dto.WifiCollectDataDTO;
import com.ss.facesys.data.trail.common.web.VehicleRecordVO;
import com.ss.facesys.data.trail.common.web.WifiCollectDataVO;
import com.ss.facesys.data.trail.mapper.VehicleRecordMapper;
import com.ss.facesys.data.trail.mapper.WifiCollectDataMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;









@Service
@Transactional(rollbackFor = {Exception.class})
public class TrailServiceImpl
  implements ITrailService
{
  private Logger logger = LoggerFactory.getLogger(TrailServiceImpl.class);



  
  @Autowired
  private VehicleRecordMapper vehicleRecordMapper;



  
  @Autowired
  private WifiCollectDataMapper wifiCollectDataMapper;



  
  public List<VehicleRecordDTO> vehicleTrack(VehicleRecordVO vehicleRecordVO) { return this.vehicleRecordMapper.vehicleTrack(vehicleRecordVO); }















  
  public List<WifiCollectDataDTO> macTrajectory(WifiCollectDataVO wifiCollectDataVO) { return this.wifiCollectDataMapper.macTrajectory(wifiCollectDataVO); }
}
