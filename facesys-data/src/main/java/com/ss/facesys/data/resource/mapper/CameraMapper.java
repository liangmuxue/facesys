package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.dto.CameraCaptureDTO;
import com.ss.facesys.data.resource.common.dto.ImportCamera;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.web.CameraQueryVO;
import com.ss.facesys.data.statistic.common.dto.CameraFaceCaptureDTO;
import com.ss.mapper.SsMapper;
import com.ss.spider.system.organization.model.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* 相机设备
* @author chao
* @create 2019/12/6
* @email lishuangchao@ss-cas.com
**/
@Component
@Mapper
public interface CameraMapper extends SsMapper<Camera> {

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
     * 通过ip,编号查询像机
     * @param paramCamera
     * @return
     */
    ImportCamera checkCamera(Camera paramCamera);

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

    /**
     * 切换状态
     * @param paramCamera
     * @return
     * @throws Exception
     */
    int opStatus(Camera paramCamera) throws Exception;

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

    /**
     * 修改设备在线离线状态
     * @param paramCamera
     * @return
     */
    int updateCameraState(List<Camera> paramCamera);

    List<Organization> findTreeCameras(CameraQueryVO queryVO);
}
