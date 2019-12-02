package com.ss.facesys.data.process.mapper;

import com.ss.facesys.data.process.common.model.VehicleDiscoveryDetail;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VehicleDiscoveryDetailMapper extends SsMapper<VehicleDiscoveryDetail> {
  VehicleDiscoveryDetail selectDetail(VehicleDiscoveryDetail paramVehicleDiscoveryDetail);
  
  void updateVehicleRecord(VehicleDiscoveryDetail paramVehicleDiscoveryDetail);
  
  VehicleDiscoveryDetail selectVehicleLeaveOne(VehicleDiscoveryDetail paramVehicleDiscoveryDetail);
  
  VehicleDiscoveryDetail selectVehicleRetenOne(VehicleDiscoveryDetail paramVehicleDiscoveryDetail);
}
