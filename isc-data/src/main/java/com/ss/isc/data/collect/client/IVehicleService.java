package com.ss.isc.data.collect.client;

import com.ss.isc.data.collect.common.dto.CaptureSumDTO;
import com.ss.isc.data.collect.common.dto.VehicleDTO;
import com.ss.isc.data.collect.common.dto.VehicleStatisticsDTO;
import com.ss.isc.data.collect.common.model.VehicleRecord;
import com.ss.isc.data.collect.common.web.VehicleQueryVO;
import com.ss.isc.data.collect.common.web.VehicleVO;

import java.util.List;
import java.util.Map;

/**
 * IVehicleService
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
public interface IVehicleService {

    List<VehicleDTO> findList(VehicleVO paramVehicleVO);

    List<CaptureSumDTO> findSixCount();

    List<CaptureSumDTO> findVehicleList();

    Map<String, Integer> findVehicleStatistics(String paramString);

    VehicleStatisticsDTO findTodayRecordStatistics(String paramString, boolean paramBoolean1, boolean paramBoolean2);

    /**
     * 查询过车记录列表
     * @param queryVO
     * @return
     */
    List<VehicleDTO> findCarList(VehicleQueryVO queryVO);

    List<CaptureSumDTO> getTenCountCar(Map paramMap);

    /**
     * 查询小区名称
     * @param paramString
     * @return
     */
    String selectVillageName(String paramString);

    /**
     * 新增过车信息
     * @param paramList
     * @return
     */
    String batchInsertRecord(List<VehicleRecord> paramList);

    VehicleRecord saveVehicleRecordImage(VehicleRecord paramVehicleRecord);

}
