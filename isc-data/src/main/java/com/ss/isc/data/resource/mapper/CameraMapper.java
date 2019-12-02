package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.dto.CameraCaptureDTO;
import com.ss.isc.data.resource.common.dto.ImportCamera;
import com.ss.isc.data.resource.common.model.Camera;
import com.ss.isc.data.resource.common.web.CameraQueryVO;
import com.ss.isc.data.statistic.common.dto.CameraFaceCaptureDTO;
import com.ss.mapper.CWMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * CameraMapper
 * @author FrancisYs
 * @date 2019/8/19
 * @email yaoshuai@ss-cas.com
 */
@Component
@Mapper
public interface CameraMapper extends CWMapper<Camera> {

    List<Camera> findCameras(Camera paramCamera);

    List<CameraCaptureDTO> findCapturedeviceIds();

    //人脸抓拍
    List<CameraFaceCaptureDTO> findFaceCapturedeviceIds();


    Camera selectByCameraId(@Param("cameraId") String paramString);

    /**
     * 新增像机
     * @param paramCamera
     * @return
     */
    int insertCamera(Camera paramCamera);

    /**
     * 通过ip查询像机
     * @param paramCamera
     * @return
     */
    ImportCamera checkIp(Camera paramCamera);

    /**
     * 通过ip、id查询像机
     * @param paramCamera
     * @return
     */
    ImportCamera checkIpById(Camera paramCamera);

    /**
     * 修改像机
     * @param paramCamera
     * @return
     */
    int updateCamera(Camera paramCamera);

    /**
     * 删除设备
     * @param paramCamera
     * @return
     */
    int deleteCamera(Camera paramCamera);

    /**
     * 查询像机分页列表
     * @param queryVO
     * @return
     */
    List<ImportCamera> findAllCameras(CameraQueryVO queryVO);

    ImportCamera check(ImportCamera paramImportCamera);

    int updateBatch(List<ImportCamera> paramList);

    Camera findOneCamera(Camera paramCamera);

    /**
     * 根据主键ID查询像机信息
     * @param paramCamera
     * @return
     */
    Camera selectById(Camera paramCamera);

    void insertImportCamera(ImportCamera paramImportCamera);

    /**
     * 根据像机类型[cameraType]查询像机[cameraId]集合
     * @param paramCamera
     * @return
     */
    List<String> cameraIdList(Camera paramCamera);

    List<String> findCameraIds(Camera paramCamera);

}
