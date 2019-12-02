package com.ss.isc.data.process.client;

import com.ss.isc.data.process.common.dto.VehicleDTO;
import com.ss.isc.data.process.common.model.*;
import com.ss.isc.data.process.common.web.PeopleProcessVO;
import com.ss.isc.data.process.common.web.VehicleVO;

import java.text.ParseException;
import java.util.List;

/**
 * IVehicleProcessService
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
public interface IVehicleProcessService {

    /**
     * 车辆感知发现分页列表
     * @param vehicle
     * @return
     * @throws ParseException
     */
    List<VehicleDiscovery> discoveryList(VehicleVO vehicle) throws ParseException;

    /**
     * 感知发现处置
     * @param paramVehicle
     */
    void discoveryHandle(Vehicle paramVehicle);

    /**
     * 车辆感知离开分页列表
     * @param vehicle
     * @return
     */
    List<VehicleLeave> leavePage(VehicleVO vehicle);

    /**
     * 车辆滞留分页
     * @param vehicle
     * @return
     */
    List<VehicleRetation> retationPage(VehicleVO vehicle);

    /**
     * 感知离开处置
     * @param paramVehicle
     */
    void leaveHandle(Vehicle paramVehicle);

    /**
     * 车辆滞留处置
     * @param paramVehicle
     */
    void retationHandle(Vehicle paramVehicle);

    int discoveryDays(VehicleDTO paramVehicleDTO);

    List<VehicleRecord> lastInOutTime(VehicleDTO paramVehicleDTO);

    VehicleDiscovery discoveryDetail(VehicleDTO paramVehicleDTO);

    /**
     * 车辆预警一键处置有效
     * @param processVO
     * @return
     */
    String handleUntreated(PeopleProcessVO processVO);

}
