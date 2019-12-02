package com.ss.isc.data.process.mapper;

import com.ss.isc.data.process.common.model.VehicleDiscoveryDetail;
import com.ss.mapper.CWMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VehicleDiscoveryDetailMapper extends CWMapper<VehicleDiscoveryDetail> {
  VehicleDiscoveryDetail selectDetail(VehicleDiscoveryDetail paramVehicleDiscoveryDetail);
  
  void updateVehicleRecord(VehicleDiscoveryDetail paramVehicleDiscoveryDetail);
  
  VehicleDiscoveryDetail selectVehicleLeaveOne(VehicleDiscoveryDetail paramVehicleDiscoveryDetail);
  
  VehicleDiscoveryDetail selectVehicleRetenOne(VehicleDiscoveryDetail paramVehicleDiscoveryDetail);
}
