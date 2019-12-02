package com.ss.facesys.data.archives.mapper;

import com.ss.facesys.data.archives.common.dto.VehicleDTO;
import com.ss.facesys.data.archives.common.model.VehicleDiscovery;
import com.ss.facesys.data.archives.common.model.VehicleLeave;
import com.ss.facesys.data.archives.common.model.VehicleRecord;
import com.ss.mapper.SsMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArchivesVehicleRecordMapper extends SsMapper<VehicleRecord> {
  List<VehicleRecord> inOutType(VehicleRecord paramVehicleRecord);
  
  List<VehicleRecord> unregisterList(Map<String, String> paramMap);
  
  List<VehicleRecord> unregisterDays(Map<String, String> paramMap);
  
  void addOneDay();
  
  List<VehicleRecord> selectByParam(VehicleRecord paramVehicleRecord);
  
  List<VehicleRecord> getLeaveList(Map<String, String> paramMap);
  
  void leaveAddOneDay();
  
  void insertsLeave(List<VehicleRecord> paramList);
  
  List<VehicleRecord> getRetationList(Map<String, String> paramMap);
  
  void retationAddOneDay();
  
  void insertsRetation(List<VehicleRecord> paramList);
  
  VehicleDiscovery discoveryExist(VehicleRecord paramVehicleRecord);
  
  void updateLeaveFirstMaster(List<VehicleLeave> paramList);
  
  void updateLeaveFirstSlave(List<VehicleLeave> paramList);
  
  void updateLeaveThirdMaster(List<VehicleLeave> paramList);
  
  void insertsLeaveThirdSlave(List<VehicleLeave> paramList);
  
  void insertsLeaveRecordSlave(List<VehicleRecord> paramList);
  
  void updateLeaveMaster(List<VehicleRecord> paramList);
  
  String getLeaveDetailId(VehicleLeave paramVehicleLeave);
  
  List<VehicleRecord> recordYesToday(VehicleDTO paramVehicleDTO);
  
  VehicleRecord getVehicleRecord(VehicleDTO paramVehicleDTO);
}
