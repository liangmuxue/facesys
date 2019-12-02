package com.ss.facesys.data.archives.client;

import com.ss.facesys.data.archives.common.dto.VehicleDTO;
import com.ss.facesys.data.archives.common.model.VehicleDiscovery;
import com.ss.facesys.data.archives.common.model.VehicleLeave;
import com.ss.facesys.data.archives.common.model.VehicleRecord;
import com.ss.facesys.data.archives.common.model.VehicleRetation;
import java.util.List;
import java.util.Map;

public interface IArchiveVehicleService {
  List<VehicleRecord> unregisterList(Map<String, String> paramMap);
  
  List<VehicleRecord> unregisterDays(Map<String, String> paramMap);
  
  void addOneDay();

  /**
   * 感知发现查询
   * @param paramVehicleDiscovery
   * @return
   */
  List<VehicleDiscovery> getDiscovery(VehicleDiscovery paramVehicleDiscovery);

  /**
   * 感知离开查询
   * @param paramVehicleLeave
   * @return
   */
  List<VehicleLeave> getLeave(VehicleLeave paramVehicleLeave);

  /**
   * 车辆滞留查询
   * @param paramVehicleRetation
   * @return
   */
  List<VehicleRetation> getRetation(VehicleRetation paramVehicleRetation);
  
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
  
  void addRecordList(List<VehicleRecord> paramList);
}
