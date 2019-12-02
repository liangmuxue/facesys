package com.ss.isc.data.archives.client;

import com.ss.isc.data.archives.common.model.VehicleRecord;
import com.ss.isc.data.archives.common.model.VehicleRetation;
import java.util.List;

public interface IVehicleRetentionService {
  String getRetentionDetailId(VehicleRetation paramVehicleRetation);
  
  void updateRetentionFirstMaster(List<VehicleRetation> paramList);
  
  void updateRetentionFirstSlave(List<VehicleRetation> paramList);
  
  void updateRetentionThirdMaster(List<VehicleRetation> paramList);
  
  void insertsRetentionThirdSlave(List<VehicleRetation> paramList);
  
  void insertsRetention(List<VehicleRecord> paramList);
  
  void insertsRetentionRecordSlave(List<VehicleRecord> paramList);
}
