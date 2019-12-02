package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.dto.CaptureSumDTO;
import com.ss.facesys.data.collect.common.dto.VehicleDTO;
import com.ss.facesys.data.collect.common.model.VehicleRecord;
import com.ss.facesys.data.collect.common.web.VehicleQueryVO;
import com.ss.facesys.data.collect.common.web.VehicleVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * VehicleMapper
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
@Repository
@Mapper
public interface VehicleMapper extends SsMapper<VehicleRecord> {

    List<VehicleDTO> findList(VehicleVO paramVehicleVO);

    List<VehicleDTO> page(VehicleVO paramVehicleVO);

    Integer findVehicleStatistics(@Param("villageCode") String paramString, @Param("beginTime") Date paramDate, @Param("inOutType") Integer paramInteger);

    Integer findPerceptualDiscovery(@Param("villageCode") String paramString);

    Integer findPerceptualLeave(@Param("villageCode") String paramString);

    List<CaptureSumDTO> findVehicleList();

    List<CaptureSumDTO> findSixCount();

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
    String selectVillageName(@Param("villageCode") String paramString);

    int findRegisterVehicle(@Param("villageCode") String paramString);

    int batchInsertRecord(List<VehicleRecord> paramList);

}
