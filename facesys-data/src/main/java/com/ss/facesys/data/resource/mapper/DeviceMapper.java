package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.collect.common.dto.AlarmDTO;
import com.ss.facesys.data.collect.common.web.AlarmRecordVO;
import com.ss.facesys.data.resource.common.dto.RegionTree;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;
/**
* 实时监控
* @author chao
* @create 2019/9/24
* @email lishuangchao@ss-cas.com
**/
@Component
@Mapper
public interface DeviceMapper extends SsMapper<Region> {

    /**
     * 查询包含设备的区划树信息
     * @return
     */
    List<RegionTree> findDeviceRegion();

    /**
     * 查询存在设备的小区编号
     * @return
     */
    String findVillageCodes();

    /**
     * 查询存在设备的行政区划编号
     * @return
     */
    String findOrgCodes(@Param("villageCodes") String villageCodes);
    /**
     * 查询存在设备的市编号
     * @return
     */
    String findCityCodes(@Param("orgCodes") String orgCodes);

    /**
     * 查询存在设备的小区信息
     * @return
     */
    List<RegionTree> findVillageRegion(@Param("villageCodes") String villageCodes);

    /**
     * 查询存在设备的区划树信息
     * @return
     */
    List<RegionTree> findRegion(@Param("regions") String regions);
    /**
     * 查询存在设备的市信息
     * @return
     */
    List<RegionTree> findCity(@Param("regionIds") String regionIds);

    /**
     * 查询布控预警信息
     * @param paramAlarmDTO
     * @return
     */
    List<AlarmRecordVO> findAlarm(AlarmDTO paramAlarmDTO);

    /**
     * 查询设备信息
     * @param cameraId
     * @return
     */
    Camera findCameraUrl(@Param("cameraId") String cameraId);

}
