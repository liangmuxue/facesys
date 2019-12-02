package com.ss.facesys.data.trail.client;

import com.ss.facesys.data.trail.common.dto.VehicleRecordDTO;
import com.ss.facesys.data.trail.common.dto.WifiCollectDataDTO;
import com.ss.facesys.data.trail.common.web.VehicleRecordVO;
import com.ss.facesys.data.trail.common.web.WifiCollectDataVO;
import java.util.List;

public interface ITrailService {
  List<VehicleRecordDTO> vehicleTrack(VehicleRecordVO paramVehicleRecordVO);
  
  List<WifiCollectDataDTO> macTrajectory(WifiCollectDataVO paramWifiCollectDataVO);
}
