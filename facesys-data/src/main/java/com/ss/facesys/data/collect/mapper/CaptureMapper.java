package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.dto.CaptureCountDTO;
import com.ss.facesys.data.collect.common.dto.CaptureSumDTO;
import com.ss.facesys.data.collect.common.dto.RegisterStatisticsDTO;
import com.ss.facesys.data.collect.common.dto.Tendency;
import com.ss.facesys.data.collect.common.model.Camera;
import com.ss.facesys.data.collect.common.model.CaptureCount;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * CaptureMapper
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
@Mapper
public interface CaptureMapper {

    List<CaptureCountDTO> snapTendency(@Param("villageCode") String paramString);

    int insertCaptureCounts(List<CaptureCount> paramList);

    List<Tendency> queryAddTendency(@Param("villageCode") String paramString, @Param("days") Integer paramInteger);

    List<Tendency> queryLeaveTendency(@Param("villageCode") String paramString, @Param("leaveDays") Integer paramInteger);

    int todayCount();

    List<CaptureSumDTO> findVillageCaptureTotal();

    List<CaptureSumDTO> getCaptureSum();

    List<CaptureSumDTO> getCaptureAllSum();

    List<RegisterStatisticsDTO> registerStatistics();

    List<Camera> getCamera(String paramString);

    List<Camera> getCamera1(String paramString);

    /**
     * 查询指定全部小区下的设备列表
     * @param paramArrayOfString
     * @return
     */
    List<Camera> getAllCamera(String[] paramArrayOfString);


    //查询全部小区下的全部设备列表
    List<Camera> getAllCameras();

    List<Camera> getAllCamera1(String[] paramArrayOfString);

}
