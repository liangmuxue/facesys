package com.ss.isc.web.manage.resource.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.j7cai.common.util.JsonUtils;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.resource.client.ICameraService;
import com.ss.isc.data.resource.client.IRegionService;
import com.ss.isc.data.resource.common.dto.ImportCamera;
import com.ss.isc.data.resource.common.model.Camera;
import com.ss.isc.data.resource.common.web.CameraQueryVO;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.autoconfigure.DeviceProperties;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.export.excel.ImportExcel;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CameraController 设备管理web请求
 * @author FrancisYs
 * @date 2019/8/19
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/camera"})
public class CameraController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CameraController.class);

    @Resource
    private IAccessService accessService;
    @Resource
    private ICameraService cameraService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IOrganizationRegionService organizationRegionService;


    @RequestMapping(value = {"/preview"}, method = {RequestMethod.POST})
    public ResponseEntity<Object> cameraPreview(HttpServletRequest request, HttpServletResponse response, @RequestBody Camera camera, BindingResult bindingResult) throws Exception {
        ResponseEntity<Object> resp = createSuccResponse();
        try {
            Map<String, String> map = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            if (PropertiesUtil.isVmsVideo()) {
                if (StringUtils.isBlank(camera.getStandardCameraId())) {
                    resp = createFailResponse();
                    resp.setMessage("国标摄像机ID不能为空");
                } else {

                    JSONObject jsonObject = this.cameraService.vmsPreview(camera.getStandardCameraId());
                    if (jsonObject == null) {
                        resp = createFailResponse();
                        resp.setMessage("VMS相机信息返回为空");
                    } else {

                        resp.setData(jsonObject);
                    }

                }

            } else if (StringUtils.isBlank(camera.getCameraId())) {
                resp = createFailResponse();
                resp.setMessage("相机cameraId不能为空");
            } else {

                map.put("deviceId", camera.getCameraId());
                String parmJson = JsonUtils.getFastjsonFromObject(map);
                JSONObject jsonObject = this.accessService.getCameraPreview(parmJson);
                this.logger.info("相机预览" + jsonObject.toString());
                String code = jsonObject.getString("code");
                if ("00000000".equals(code)) {
                    map.put("RtspUrlMain", jsonObject.getString("data"));
                    map.put("LiveRtspUrlMain", jsonObject.getString("data"));
                    resp.setData(map);
                } else {
                    resp = createFailResponse();
                    resp.setMessage(jsonObject.getString("message"));
                }

            }

        } catch (Exception e) {
            this.logger.error("获取相机VMS信息失败，原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("获取相机VMS信息失败");
        }
        return resp;
    }

    /**
     * 新增像机
     * @param camera
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "新增相机", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, String>> insertCamera(@RequestBody @Validated({APIAddGroup.class}) Camera camera, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            // 校验视频流地址格式
            String streamSource = camera.getStreamSource();
            if (StringUtils.isNotBlank(streamSource)) {
                boolean startsWith = streamSource.startsWith("rtsp://");
                if (!startsWith) {
                    resp = createFailResponse();
                    resp.setMessage("视频流地址请以rtsp://开头");
                    return resp;
                }
            } else {
                streamSource = "rtsp://";
                camera.setStreamSource(streamSource);
            }
            // 新增设备
            int num = this.cameraService.insertCamera(camera);
            //设备推流，抽帧启动
            if(num > CommonConstant.COMMON_0 && CommonConstant.COMMON_1.equals(camera.getCameraState()) && StringUtils.isNotBlank(camera.getCameraIp()) && !"rtsp://".equals(camera.getStreamSource())){
                com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDevicePushFlowUrl(), camera.getCameraId(), camera.getCameraName());
                if (!CommonConstant.COMMON_2.equals(camera.getCameraType())){
                    com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDeviceCutFlowUrl(), camera.getCameraId(), camera.getCameraName());
                }
            }
            if (num > 0) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("result", "添加成功");
                resp.setData(map);
            } else {
                resp = createFailResponse();
                resp.setMessage("添加失败，请联系管理员");
            }
        } catch (Exception e) {
            this.logger.error("新增相机失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.CAMERAINSERT_FAILED_CODE);
            resp.setMessage("新增相机失败！");
        }
        return resp;
    }

    /**
     * 查询设备详情
     * @param camera
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查询像机详情", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Camera> detail(@RequestBody @Validated({APIGetsGroup.class}) Camera camera, BindingResult bindingResult) throws Exception {
        ResponseEntity<Camera> resp = validite(bindingResult);
        try {
            Camera data = this.cameraService.selectOne(camera);
            resp.setData(data);
        } catch (Exception e) {
            this.logger.error("查找相机失败的原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.FINDCAMERAS_FAILED_CODE);
            resp.setMessage("查找相机失败");
        }
        return resp;
    }

    /**
     * 修改设备信息
     * @param camera
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "相机修改", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, String>> updateCamera(@RequestBody @Validated({APIEditGroup.class}) Camera camera, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            // 校验视频流地址格式
            String streamSource = camera.getStreamSource();
            if (StringUtils.isNotBlank(streamSource)) {
                boolean startsWith = streamSource.startsWith("rtsp://");
                if (!startsWith) {
                    resp = createFailResponse();
                    resp.setMessage("视频流地址请以rtsp://开头");
                    return resp;
                }
            } else {
                streamSource = "rtsp://";
                camera.setStreamSource(streamSource);
            }
            Camera temp = new Camera();
            temp.setId(camera.getId());
            Camera cameraOld = this.cameraService.selectOne(temp);
            int num = this.cameraService.updateCamera(camera);
            if (num > CommonConstant.COMMON_0){
                if(CommonConstant.COMMON_0.equals(cameraOld.getCameraState())
                        && CommonConstant.COMMON_1.equals(camera.getCameraState())
                        && StringUtils.isNotBlank(camera.getCameraIp())
                        && !"rtsp://".equals(camera.getStreamSource())){
                    //设备推流，抽帧启动
                    com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDevicePushFlowUrl(), camera.getCameraId(), camera.getCameraName());
                    if (!CommonConstant.COMMON_2.equals(camera.getCameraType())){
                        com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDeviceCutFlowUrl(), camera.getCameraId(), camera.getCameraName());
                    }
                } else if (CommonConstant.COMMON_1.equals(cameraOld.getCameraState()) && CommonConstant.COMMON_0.equals(camera.getCameraState())){
                    //设备停止推流，停止抽帧
                    com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDevicePushFlowStopUrl(), camera.getCameraId(), camera.getCameraName());
                    if (!CommonConstant.COMMON_2.equals(cameraOld.getCameraType())){
                        com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDeviceCutFlowStopUrl(), camera.getCameraId(), camera.getCameraName());
                    }
                }
            }
            Map<String, String> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            if (num > 0) {
                map.put("result", "更新成功");
                resp.setData(map);
            } else {
                map.put("result", "更新失败");
                resp.setData(map);
            }
        } catch (Exception e) {
            this.logger.error("更新相机失败的原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.CAMERAUPDATE_FAILED_CODE);
            resp.setMessage("更新相机失败！");
        }
        return resp;
    }

    /**
     * 删除设备
     * @param camera
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "删除一个相机", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, String>> deleteCamera(@RequestBody @Validated({APIDeltGroup.class}) Camera camera, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            int num = this.cameraService.deleteCamera(camera);
            Map<String, String> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            if (num > 0) {
                map.put("result", "删除成功");
                resp.setData(map);
            } else {
                map.put("result", "删除失败");
                resp.setData(map);
            }
        } catch (Exception e) {
            this.logger.error("删除相机失败的原因+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.CAMERADELETE_FAILED_CODE);
            resp.setMessage("删除相机失败");
        }
        return resp;
    }

    /**
     * 查找像机分页列表
     * @param queryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/findAllCameras"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查找相机分页列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<ImportCamera>> findAllCameras(@RequestBody @Validated({APIPageGroup.class}) CameraQueryVO queryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<ImportCamera>> resp = validite(bindingResult);
        try {
            queryVO.setVillageCodes(getVillageCodes(queryVO.getVillageCodes()));
            Page<ImportCamera> data = (Page) this.cameraService.findAllCameras(queryVO);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            this.logger.error("查找相机失败的原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.FINDALLCAMERAS_FAILED_CODE);
            resp.setMessage("查找相机失败");
        }
        return resp;
    }


    @RequestMapping(value = {"/importCameraData"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "导入相机设备", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Object> importCameraData(HttpServletRequest request, MultipartFile file) throws Exception {
        ResponseEntity<Object> resp = createSuccResponse();
        try {
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<ImportCamera> tempList = ei.getDataList(ImportCamera.class, new int[0]);

            String message = this.cameraService.batchImport(tempList);
            resp.setData(message);
        } catch (Exception e) {
            this.logger.error("导入相机失败的原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70806040");
            resp.setMessage("导入相机失败");
        }
        return resp;
    }


    @RequestMapping(value = {"/playVideo"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "播放录像", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, Object>> playVideo(HttpServletRequest request, HttpServletResponse response, @RequestBody Camera camera, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> map = this.cameraService.playVideo(camera);
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("播放录像失败");
            this.logger.error("相机播放录像失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
