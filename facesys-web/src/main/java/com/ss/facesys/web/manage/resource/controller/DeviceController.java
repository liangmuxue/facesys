package com.ss.facesys.web.manage.resource.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.FaceDbDTO;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.collect.client.ICaptureService;
import com.ss.facesys.data.collect.common.dto.AlarmDTO;
import com.ss.facesys.data.collect.common.web.AlarmRecordVO;
import com.ss.facesys.data.collect.common.web.CaptureQueryVO;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.client.ICommunityResourceService;
import com.ss.facesys.data.resource.client.IDeviceService;
import com.ss.facesys.data.resource.common.dto.RegionTree;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.data.system.common.dto.UserPermission;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.http.BaseFormatJsonUtil;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 实时监控
 * @author 李爽超 chao
 * @create 2019/08/26
 * @email lishuangchao@ss-cas.com
 **/
@RestController
@RequestMapping({"/device"})
public class DeviceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Resource
    private IDeviceService deviceService;
    @Resource
    private IOrganizationRegionService organizationRegionService;
    @Resource
    private ICommunityResourceService communityResourceService;
    @Resource
    private IAccessService accessService;
    @Resource
    private ICaptureService caputerService;
    @Resource
    private ICameraService cameraService;

    /**
     * 查询设备列表
     * @param region
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/deviceTree"}, method = {RequestMethod.POST})
    public ResponseEntity<List<RegionTree>> treeData(@RequestBody Region region, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<RegionTree>> resp = createSuccResponse();
        try {
            // 参数校验
            if (StringUtils.isBlank(region.getUserIds())) {
                resp = createFailResponse();
                resp.setCode(ResultCode.INVALID_PARAMETER);
                resp.setMessage(ResultCode.INVALID_PARAMETER_MESSAGE);
                return resp;
            }
            // 查询当前用户的权限小区
            User user = new User();
            user.setUserId(region.getUserIds());
            String[] villageCodes = null;
            if (!user.isAdmin()) {
                UserPermission userPermission = this.organizationRegionService.findCurrentPersion(user.getUserId());
                if (userPermission != null) {
                    villageCodes = userPermission.getVillageCodes();
                }
            }
            // 查询设备列表处理
            List<RegionTree> list = this.deviceService.getTreeData(region, villageCodes);
            resp.setData(list);
        } catch (Exception e) {
            // 查询设备列表失败处理
            resp = createFailResponse();
            resp.setMessage("设备列表查询失败");
            this.logger.error("设备列表查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询视频监控信息
     * @param request
     * @param response
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/preview"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Camera>> cameraPreview(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Camera>> resp = validite(bindingResult);
        try {
            List<Camera> camera = (List<Camera>) para.get("cameras");
            String json = JSON.toJSONString(camera);
            List<Camera> camera1 = JSON.parseArray(json,Camera.class);
            //查询视频监控信息处理
            Map<String, Object> maps = this.deviceService.cameraPreview(camera1);
            resp.setData((List<Camera>) maps.get("cameras"));
            resp.setMessage((String) maps.get("message"));
        } catch (Exception e) {
            //查询视频监控信息失败处理
            resp = createFailResponse();
            resp.setMessage("查询失败");
            this.logger.error("查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询布控预警信息
     * @param request
     * @param reqDTO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/findAlarm"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "布控预警信息", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<AlarmRecordVO>> findAlarm(HttpServletRequest request, @RequestBody AlarmDTO reqDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<AlarmRecordVO>> resp = validite(bindingResult);
        try {
            reqDTO.setMonitorType(Integer.valueOf(1));
            reqDTO.setTopSeq(Integer.valueOf(0));
            if (StringUtils.isNotBlank(reqDTO.getMinSim())) {
                float f = Float.parseFloat(reqDTO.getMinSim());
                f /= 100.0F;
                reqDTO.setMinSim(String.valueOf(f));
            }
            if (StringUtils.isNotBlank(reqDTO.getMaxSim())) {
                float f = Float.parseFloat(reqDTO.getMaxSim());
                f /= 100.0F;
                reqDTO.setMaxSim(String.valueOf(f));
            }
            List<Camera> cList = new ArrayList<Camera>();
            List<String> sList = new ArrayList<String>();
            if (CollectionUtils.isEmpty(reqDTO.getCameraIds())) {
                Camera camera = new Camera();
                camera.setUserIds(reqDTO.getUserIds());
                camera.setVillageCode(reqDTO.getVillageCode());
                List<Integer> typeList = new ArrayList<Integer>();
                typeList.add(NumberConstant.ONE);
                typeList.add(NumberConstant.TWO);
                typeList.add(NumberConstant.FIVE);
                typeList.add(NumberConstant.SIX);
                typeList.add(NumberConstant.SEVEN);
                camera.setCameraList(typeList);
                cList = this.communityResourceService.findCameras(camera);
                for (Camera camera1 : cList) {
                    sList.add(camera1.getCameraId());
                }
                reqDTO.setCameraIdsString(StringUtils.join(sList.toArray(), ","));
            } else {
                List<String> sList2 = reqDTO.getCameraIds();
                reqDTO.setCameraIdsString(StringUtils.join(sList2.toArray(), ","));
            }
            if (CollectionUtils.isEmpty(reqDTO.getLibraryIds())) {
                JSONObject jsonobj = this.accessService.facedblist();
                if (!StringUtils.checkSuccess(jsonobj)) {
                    resp = createFailResponse();
                    resp.setCode(ResultCode.OCEAN_FACEDB_CODE);
                    resp.setMessage("查询欧神人像库失败");
                }
                this.logger.info("facedblist ----------------- 人脸底库列表 result-------------:" + jsonobj);
                List<FaceDbDTO> faceList = BaseFormatJsonUtil.formatList(jsonobj.get("data"), FaceDbDTO.class);
                List<String> addList = new ArrayList<String>();
                if (CollectionUtils.isNotEmpty(faceList)) {
                    for (FaceDbDTO faceDbDTO : faceList) {
                        if (faceDbDTO.getType() != null) {
                            addList.add(faceDbDTO.getId());
                        }
                    }
                }
                reqDTO.setLibraryIdsString(StringUtils.join(addList.toArray(), ","));
            } else {
                reqDTO.setLibraryIdsString(StringUtils.join(reqDTO.getLibraryIds().toArray(), ","));
            }
            //布控预警信息查询
            List<AlarmRecordVO> data = this.deviceService.findAlarm(reqDTO);
            resp.setData(data);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_ALARM_CODE);
            resp.setMessage("布控预警信息查询失败");
            this.logger.error("布控预警信息查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 实时抓拍
     * @param captureQueryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/snap"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONArray> snap(@RequestBody CaptureQueryVO captureQueryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONArray> resp = validite(bindingResult);
        try {
            SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=format.parse("2002-01-01 08:00:00");
            captureQueryVO.setCaptureTimeB(date);
            captureQueryVO.setCaptureTimeE(new Date());
            captureQueryVO.setCurrentPage(NumberConstant.ONE);
            captureQueryVO.setPageSize(NumberConstant.SIX);
            // 搜索条件满足的标识
            boolean flag = true;
            // 社区编号数组
            String[] villages = StringUtils.isNotBlank(captureQueryVO.getVillageCodes()) ? captureQueryVO.getVillageCodes().split(CommonConstant.SPLIT_COMMA) : null;
            // 设备编号数组
            String[] deviceIds = StringUtils.isNotBlank(captureQueryVO.getDeviceIdStr()) ? captureQueryVO.getDeviceIdStr().split(CommonConstant.SPLIT_COMMA) : null;
            captureQueryVO.setDeviceIds(deviceIds);
            if (villages == null || villages.length == 0) {
                // 未选择社区时查询当前用户权限下的全部小区
                String userIds = captureQueryVO.getUserIds();
                UserPermission userPermission = this.organizationRegionService.findCurrentPersion(userIds);
                if (userPermission != null) {
                    villages = userPermission.getVillageCodes();
                }
                // 查询小区下的全部设备
                if (null != villages && villages.length > 0) {
                    List<com.ss.facesys.data.collect.common.model.Camera> cameras = this.caputerService.getAllCamera(villages);
                    List<String> list = new ArrayList<>();
                    for (com.ss.facesys.data.collect.common.model.Camera camera : cameras) {
                        list.add(camera.getCameraId());
                    }
                    deviceIds = list.toArray(new String[0]);
                    captureQueryVO.setDeviceIds(deviceIds);
                    if (deviceIds.length == 0) {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            } else if (null == deviceIds || deviceIds.length == 0) {
                // 未选择设备时查询小区下的全部设备
                List<com.ss.facesys.data.collect.common.model.Camera> cameras = this.caputerService.getAllCamera(villages);
                List<String> list = new ArrayList<>();
                for (com.ss.facesys.data.collect.common.model.Camera camera : cameras) {
                    list.add(camera.getCameraId());
                }
                deviceIds = list.toArray(new String[0]);
                captureQueryVO.setDeviceIds(deviceIds);
                if (deviceIds.length == 0) {
                    flag = false;
                }
            }
            // 若请求参数满足，则调用欧神查询抓拍数据
            if (flag) {
                String parmJson = JsonUtils.getFastjsonFromObject(captureQueryVO);
                JSONObject data = null;
                JSONArray datas = null;
                JSONObject jsonObject;
                // 2 - 调用欧神终端注册接口查找人脸抓拍;其他-本地调用人脸抓拍库
                if (PropertiesUtil.getCaptureOcean().equals(CommonConstant.COMMON_2)) {
                    jsonObject = this.accessService.getCollectPages(parmJson);
                } else {
                    jsonObject = this.accessService.getCapturePages(parmJson);
                }
                this.logger.info("人脸抓拍返回数据：" + jsonObject.toJSONString());
                // 请求成功封装响应数据（主要是处理设备信息）
                if (StringUtils.checkSuccess(jsonObject)) {
                    data = (JSONObject) jsonObject.get("data");
                    datas = data.getJSONArray("datas");
                    String deviceId = "";
                    Camera device = null;
                    for (int i = 0; i < datas.size(); i++) {
                        JSONObject cp = datas.getJSONObject(i);
                        if (!deviceId.equals(cp.getString("deviceId"))) {
                            deviceId = cp.getString("deviceId");
                            device = this.cameraService.findDevice(deviceId);
                        }
                        if (device != null) {
                            cp.put("deviceName", device.getCameraName());
                            cp.put("deviceAddress", (device.getInstallAdd() == null) ? device.getCameraName() : device.getInstallAdd());
                            cp.put("lat", String.valueOf(device.getLat()));
                            cp.put("lng", String.valueOf(device.getLon()));
                        }
                    }
                }
                resp.setData(datas);
            }
        } catch (Exception e) {
            this.logger.error("实施抓拍查询失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.PAGE_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }
}
