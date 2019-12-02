package com.ss.isc.data.trail.mapper;

import com.ss.isc.data.trail.common.dto.VehicleRecordDTO;
import com.ss.isc.data.trail.common.web.VehicleRecordVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehicleRecordMapper {
  List<VehicleRecordDTO> vehicleTrack(VehicleRecordVO paramVehicleRecordVO);
}
