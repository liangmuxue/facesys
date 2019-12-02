package com.ss.facesys.data.archives.client;

import com.ss.facesys.data.archives.common.model.Vehicle;
import com.ss.facesys.data.archives.common.model.VehicleRecord;
import java.util.List;

public interface IPersonVehicleService {
  /**
   * 一车一档信息分页查询
   * @param paramVehicle
   * @param paramInt1
   * @param paramInt2
   * @return
   */
  List<Vehicle> select(Vehicle paramVehicle, int paramInt1, int paramInt2);

  /**
   * 过车记录查询
   * @param paramVehicleRecord
   * @return
   */
  List<VehicleRecord> list(VehicleRecord paramVehicleRecord);

  /**
   * 查询车辆详情
   * @param paramVehicle
   * @return
   */
  List<Vehicle> selectById(Vehicle paramVehicle);

  /**
   * 新增车辆
   * @param param
   * @return
   */
  String save(Vehicle param);

  /**
   * 修改车辆信息
   * @param param
   * @return
   */
  String edit(Vehicle param);

  /**
   * 删除车辆信息
   * @param param
   * @return
   */
  String delete(Vehicle param);

  /**
   * 车辆信息详情查询
   * @param paramVehicle
   * @return
   */
  List<Vehicle> detail(Vehicle paramVehicle);
}
