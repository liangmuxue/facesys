package com.ss.isc.data.archives.service;

import com.ss.isc.data.archives.client.IVehicleRetentionService;
import com.ss.isc.data.archives.common.model.VehicleRecord;
import com.ss.isc.data.archives.common.model.VehicleRetation;
import com.ss.isc.data.archives.mapper.ArchivesVehicleRetationMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;













@Service
@Transactional(rollbackFor = {Exception.class})
public class VehicleRetentionServiceImpl
  implements IVehicleRetentionService
{
  @Autowired
  private ArchivesVehicleRetationMapper retentionMapper;
  
  public String getRetentionDetailId(VehicleRetation vehicleRetation) { return this.retentionMapper.getRetentionDetailId(vehicleRetation); }



  
  public void updateRetentionFirstMaster(List<VehicleRetation> firstStatusList) { this.retentionMapper.updateRetentionFirstMaster(firstStatusList); }



  
  public void updateRetentionFirstSlave(List<VehicleRetation> firstStatusList) { this.retentionMapper.updateRetentionFirstSlave(firstStatusList); }



  
  public void updateRetentionThirdMaster(List<VehicleRetation> thirdStatusList) { this.retentionMapper.updateRetentionThirdMaster(thirdStatusList); }



  
  public void insertsRetentionThirdSlave(List<VehicleRetation> thirdStatusList) { this.retentionMapper.insertsRetentionThirdSlave(thirdStatusList); }



  
  public void insertsRetention(List<VehicleRecord> addList) { this.retentionMapper.insertsRetention(addList); }



  
  public void insertsRetentionRecordSlave(List<VehicleRecord> addList) { this.retentionMapper.insertsRetentionRecordSlave(addList); }
}
