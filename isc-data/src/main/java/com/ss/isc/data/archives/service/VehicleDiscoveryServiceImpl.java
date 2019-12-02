package com.ss.isc.data.archives.service;

import com.ss.isc.data.archives.client.IVehicleDiscoveryService;
import com.ss.isc.data.archives.common.model.VehicleDiscovery;
import com.ss.isc.data.archives.common.model.VehicleRecord;
import com.ss.isc.data.archives.mapper.ArchivesVehicleDiscoveryMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;













@Service
@Transactional(rollbackFor = {Exception.class})
public class VehicleDiscoveryServiceImpl
  implements IVehicleDiscoveryService
{
  @Autowired
  private ArchivesVehicleDiscoveryMapper discoveryMapper;
  
  public String getDiscoveryDetailId(VehicleDiscovery vehicleDiscovery) { return this.discoveryMapper.getDiscoveryDetailId(vehicleDiscovery); }



  
  public void updateDiscoveryFirstMaster(List<VehicleDiscovery> firstStatusList) { this.discoveryMapper.updateDiscoveryFirstMaster(firstStatusList); }



  
  public void updateDiscoveryFirstSlave(List<VehicleDiscovery> firstStatusList) { this.discoveryMapper.updateDiscoveryFirstSlave(firstStatusList); }



  
  public void updateDiscoveryThirdMaster(List<VehicleDiscovery> thirdStatusList) { this.discoveryMapper.updateDiscoveryThirdMaster(thirdStatusList); }



  
  public void insertsDiscoveryThirdSlave(List<VehicleDiscovery> thirdStatusList) { this.discoveryMapper.insertsDiscoveryThirdSlave(thirdStatusList); }



  
  public void insertsDiscovery(List<VehicleRecord> addList) { this.discoveryMapper.insertsDiscovery(addList); }



  
  public void insertsDiscoveryRecordSlave(List<VehicleRecord> addList) { this.discoveryMapper.insertsDiscoveryRecordSlave(addList); }



  
  public void updateDiscoveryByFlag(List<VehicleDiscovery> dList) { this.discoveryMapper.updateDiscoveryByFlag(dList); }
}
