package com.ss.isc.data.archives.client;

import com.ss.isc.data.archives.common.model.VehicleDiscovery;
import com.ss.isc.data.archives.common.model.VehicleRecord;
import java.util.List;

public interface IVehicleDiscoveryService {
  String getDiscoveryDetailId(VehicleDiscovery paramVehicleDiscovery);
  
  void updateDiscoveryFirstMaster(List<VehicleDiscovery> paramList);
  
  void updateDiscoveryFirstSlave(List<VehicleDiscovery> paramList);
  
  void updateDiscoveryThirdMaster(List<VehicleDiscovery> paramList);
  
  void insertsDiscoveryThirdSlave(List<VehicleDiscovery> paramList);
  
  void insertsDiscovery(List<VehicleRecord> paramList);
  
  void insertsDiscoveryRecordSlave(List<VehicleRecord> paramList);
  
  void updateDiscoveryByFlag(List<VehicleDiscovery> paramList);
}
