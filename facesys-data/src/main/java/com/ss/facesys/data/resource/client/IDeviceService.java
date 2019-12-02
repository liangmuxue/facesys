package com.ss.facesys.data.resource.client;

import com.ss.facesys.data.collect.common.dto.AlarmDTO;
import com.ss.facesys.data.collect.common.web.AlarmRecordVO;
import com.ss.facesys.data.resource.common.dto.RegionTree;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.model.Region;
import java.util.List;
import java.util.Map;
/**
 * com.ss.facesys.web.manage.resource.controller
 * 实时监控
 * @author 李爽超 chao
 * @create 2019/08/26
 * @email lishuangchao@ss-cas.com
 **/
public interface IDeviceService {
    /**
     * 查询设备列表
     * @param paramRegion
     * @param paramArrayOfString
     * @return
     */
    List<RegionTree> getTreeData(Region paramRegion, String[] paramArrayOfString);

    /**
     * 查询视频监控信息
     * @param camera
     * @return
     * @throws Exception
     */
    Map<String, Object> cameraPreview(List<Camera> camera) throws Exception;

    /**
     * 查询布控预警信息
     * @param paramAlarmDTO
     * @return
     */
    List<AlarmRecordVO> findAlarm(AlarmDTO paramAlarmDTO);

}
