package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.dto.CaptureSumDTO;
import com.ss.facesys.data.collect.common.dto.RegisterStatisticsDTO;
import com.ss.facesys.data.collect.common.model.Camera;
import com.ss.facesys.data.collect.common.model.CaptureCount;

import java.util.List;

/**
 * ICaptureService
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
public interface ICaptureService {

    boolean batchInsertCaptureCount(List<CaptureCount> paramList);

    int todayCount();

    List<CaptureSumDTO> getCaptureSum();

    List<CaptureSumDTO> findVillageCaptureTotal();

    List<RegisterStatisticsDTO> registerStatistics();

    List<Camera> getCamera(String paramString);

    List<Camera> getCamera1(String paramString);

    /**
     * 查询指定全部小区下的设备列表
     * @param paramArrayOfString
     * @return
     */
    List<Camera> getAllCamera(String[] paramArrayOfString);

    //查询所有小区下的所有设备
    List<Camera> getAllCamera();

    List<Camera> getAllCamera1(String[] paramArrayOfString);

    List<CaptureSumDTO> getCaptureAllSum();

}
