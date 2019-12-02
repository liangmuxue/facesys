package com.ss.facesys.data.resource.client;

import com.alibaba.fastjson.JSONObject;
import com.ss.facesys.data.resource.common.dto.ImportCamera;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.web.CameraQueryVO;

import java.util.List;
import java.util.Map;

/**
 * ICameraService
 * @author FrancisYs
 * @date 2019/8/19
 * @email yaoshuai@ss-cas.com
 */
public interface ICameraService {

    /**
     * 查询像机详情
     * @param paramCamera
     * @return
     */
    Camera selectOne(Camera paramCamera);

    List<Camera> findCameras(Camera paramCamera);

    /**
     * 根据设备ID查找设备
     * @param paramString
     * @return
     */
    Camera findDevice(String paramString);

    /**
     * 新增像机
     * @param paramCamera
     * @return
     * @throws Exception
     */
    int insertCamera(Camera paramCamera) throws Exception;

    /**
     * 修改像机信息
     * @param paramCamera
     * @return
     * @throws Exception
     */
    int updateCamera(Camera paramCamera) throws Exception;

    /**
     * 删除设备
     * @param paramCamera
     * @return
     * @throws Exception
     */
    int deleteCamera(Camera paramCamera) throws Exception;

    /**
     * 查询像机分页列表
     * @param queryVO
     * @return
     */
    List<ImportCamera> findAllCameras(CameraQueryVO queryVO);

    String batchImport(List<ImportCamera> paramList) throws Exception;

    /**
     * 根据像机类型[cameraType]查询像机[cameraId]集合
     * @param paramCamera
     * @return
     */
    List<String> cameraIdList(Camera paramCamera);

    List<String> findCameraIds(Camera paramCamera);

    Map<String, Object> playVideo(Camera paramCamera);

    JSONObject vmsPreview(String paramString) throws Exception;

}
