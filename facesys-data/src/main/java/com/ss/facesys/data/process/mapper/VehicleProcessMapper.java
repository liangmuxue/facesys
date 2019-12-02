package com.ss.facesys.data.process.mapper;

import com.github.pagehelper.Page;
import com.ss.facesys.data.process.common.dto.VehicleDTO;
import com.ss.facesys.data.process.common.model.*;
import com.ss.facesys.data.process.common.web.VehicleVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * VehicleProcessMapper
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
@Repository
@Mapper
public interface VehicleProcessMapper extends SsMapper<Vehicle> {

    /**
     * 车辆感知发现分页列表
     * @param vehicle
     * @return
     */
    List<VehicleDiscovery> discoveryList(VehicleVO vehicle);

    int discoveryDays(VehicleDTO paramVehicleDTO);

    /**
     * 查询过车记录
     * @param paramVehicleDTO
     * @return
     */
    List<VehicleRecord> recordList(VehicleDTO paramVehicleDTO);

    void updateDiscovery(Vehicle paramVehicle);

    void insertVehicle(Vehicle paramVehicle);

    /**
     * 车辆感知离开分页列表
     * @param vehicle
     * @return
     */
    List<VehicleLeave> leaveList(VehicleVO vehicle);

    /**
     * 车辆滞留分页列表
     * @param vehicle
     * @return
     */
    Page<VehicleRetation> retationList(VehicleVO vehicle);

    void updateLeave(Vehicle paramVehicle);

    void updateVehicle(Vehicle paramVehicle);

    void updateRetation(Vehicle paramVehicle);

    Vehicle existVehicle(Vehicle paramVehicle);

    List<VehicleRecord> lastInOutTime(VehicleDTO paramVehicleDTO);

    VehicleDiscovery discoveryDetail(VehicleDTO paramVehicleDTO);

    /**
     * 更新未处置的车辆预警信息状态为有效[感知发现]
     */
    void updateUntreatedDiscoveryAsValid();
    /**
     * 更新未处置的车辆预警信息状态为有效[感知离开]
     */
    void updateUntreatedLeaveAsValid();
    /**
     * 更新未处置的车辆预警信息状态为有效[车辆滞留]
     */
    void updateUntreatedRetentionAsValid();

}
