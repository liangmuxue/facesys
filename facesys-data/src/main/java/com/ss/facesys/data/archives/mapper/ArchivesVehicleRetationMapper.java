package com.ss.facesys.data.archives.mapper;

import com.ss.facesys.data.archives.common.model.VehicleRecord;
import com.ss.facesys.data.archives.common.model.VehicleRetation;
import com.ss.mapper.SsMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArchivesVehicleRetationMapper extends SsMapper<VehicleRetation> {
  String getRetentionDetailId(VehicleRetation paramVehicleRetation);
  
  void updateRetentionFirstMaster(List<VehicleRetation> paramList);
  
  void updateRetentionFirstSlave(List<VehicleRetation> paramList);
  
  void updateRetentionThirdMaster(List<VehicleRetation> paramList);
  
  void insertsRetentionThirdSlave(List<VehicleRetation> paramList);
  
  void insertsRetention(List<VehicleRecord> paramList);
  
  void insertsRetentionRecordSlave(List<VehicleRecord> paramList);
}
