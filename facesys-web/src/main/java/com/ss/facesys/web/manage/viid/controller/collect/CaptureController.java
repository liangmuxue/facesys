package com.ss.facesys.web.manage.viid.controller.collect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import com.ss.annotation.OpLog;
import com.ss.common.Constants;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.baseinfo.common.dto.PersonCaptureDTO;
import com.ss.facesys.data.collect.client.ICaptureService;
import com.ss.facesys.data.collect.common.dto.CaptureCompareDTO;
import com.ss.facesys.data.collect.common.dto.RegisterDTO;
import com.ss.facesys.data.collect.common.model.Camera;
import com.ss.facesys.data.collect.common.model.VehicleRecord;
import com.ss.facesys.data.collect.common.web.CaptureCompareVO;
import com.ss.facesys.data.collect.common.web.CaptureImgVO;
import com.ss.facesys.data.collect.common.web.CaptureQueryVO;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.client.IVillageService;
import com.ss.facesys.data.resource.common.web.CameraQueryVO;
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
import com.ss.tools.Base64ImageUtils;
import com.ss.valide.APIPageGroup;
import com.ss.valide.APIRegisterRecogGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 1400接口抓拍相关请求
 *
 * @author FrancisYs
 * @date 2019/10/25
 * @email yaoshuai@ss-cas.com
 */
@RestController("viidCaptureController")
@RequestMapping({"/viid/collect/capture"})
public class CaptureController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CaptureController.class);

    @Resource
    private IAccessService accessService;
    @Resource
    private ICaptureService caputerService;
    @Resource
    private IOrganizationRegionService organizationRegionService;
    @Resource
    private IVillageService villageService;
    @Resource
    private ICameraService cameraService;

    /**
     * 查询人脸抓拍分页列表
     *
     * @param captureQueryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> peers(@RequestBody CaptureQueryVO captureQueryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONObject> resp = validite(bindingResult);
        JSONObject temp = new JSONObject();
        resp.setMessage("1400拦截器生效");
        resp.setData(temp);
        return resp;
//        try {
//            // 搜索条件满足的标识
//            boolean flag = true;
//            // 社区编号数组
//            captureQueryVO.setVillageCodes(getVillageCodes(captureQueryVO.getVillageCodes()));
//            String[] villages = StringUtils.isNotBlank(captureQueryVO.getVillageCodes()) ? captureQueryVO.getVillageCodes().split(CommonConstant.SPLIT_COMMA) : null;
//            // 设备编号数组
//            String[] deviceIds = StringUtils.isNotBlank(captureQueryVO.getDeviceIdStr()) ? captureQueryVO.getDeviceIdStr().split(CommonConstant.SPLIT_COMMA) : null;
//
//            if (villages == null || villages.length == 0) {
//                // 未选择社区时查询当前用户权限下的全部小区
//                String userIds = captureQueryVO.getUserIds();
//                UserPermission userPermission = this.organizationRegionService.findCurrentPersion(userIds);
//                if (userPermission != null) {
//                    villages = userPermission.getVillageCodes();
//                }
//                // 查询小区下的全部设备
//                if (null != villages && villages.length > 0) {
//                    List<Camera> cameras = this.caputerService.getAllCamera(villages);
//                    List<String> list = new ArrayList<>();
//                    for (Camera camera : cameras) {
//                        list.add(camera.getCameraId());
//                    }
//                    deviceIds = list.toArray(new String[0]);
//                    captureQueryVO.setDeviceIds(deviceIds);
//                    if (deviceIds.length == 0) {
//                        flag = false;
//                    }
//                } else {
//                    flag = false;
//                }
//            } else if (null == deviceIds || deviceIds.length == 0) {
//                // 未选择设备时查询小区下的全部设备
//                List<Camera> cameras = this.caputerService.getAllCamera(villages);
//                List<String> list = new ArrayList<>();
//                for (Camera camera : cameras) {
//                    list.add(camera.getCameraId());
//                }
//                deviceIds = list.toArray(new String[0]);
//                captureQueryVO.setDeviceIds(deviceIds);
//                if (deviceIds.length == 0) {
//                    flag = false;
//                }
//            }
//            // 若请求参数满足，则调用欧神查询抓拍数据
//            if (flag) {
//                String parmJson = JsonUtils.getFastjsonFromObject(captureQueryVO);
//                JSONObject data = null;
//                JSONObject jsonObject;
//                // 2-调用欧神终端注册接口查找人脸抓拍;其他-本地调用人脸抓拍库
//                if (PropertiesUtil.getCaptureOcean().equals(CommonConstant.COMMON_2)) {
//                    jsonObject = this.accessService.getCollectPages(parmJson);
//                } else {
//                    jsonObject = this.accessService.getCapturePages(parmJson);
//                }
//                this.logger.info("人脸抓拍返回数据：" + jsonObject.toJSONString());
//                // 请求成功封装响应数据（主要是处理设备信息）
//                if (StringUtils.checkSuccess(jsonObject)) {
//                    data = (JSONObject) jsonObject.get("data");
//                    JSONArray datas = data.getJSONArray("datas");
//                    String deviceId = "";
//                    com.ss.facesys.data.resource.common.model.Camera device = null;
//                    for (int i = 0; i < datas.size(); i++) {
//                        JSONObject cp = datas.getJSONObject(i);
//                        if (!deviceId.equals(cp.getString("deviceId"))) {
//                            deviceId = cp.getString("deviceId");
//                            device = this.cameraService.findDevice(deviceId);
//                        }
//                        if (device != null) {
//                            cp.put("deviceName", device.getCameraName());
//                            cp.put("deviceAddress", (device.getInstallAdd() == null) ? device.getCameraName() : device.getInstallAdd());
//                            cp.put("lat", String.valueOf(device.getLat()));
//                            cp.put("lng", String.valueOf(device.getLon()));
//                        }
//                    }
//                    data.put("datas", datas);
//                }
//                resp.setData(data);
//            }
//        } catch (Exception e) {
//            this.logger.error("人脸抓拍分页查询失败原因：+" + e.toString(), e);
//            resp = createFailResponse();
//            resp.setCode(ResultCode.PAGE_FAILED_CODE);
//            resp.setMessage("操作失败！请联系管理员！");
//        }
//        return resp;
    }

    /**
     * 查询开门记录分页列表
     *
     * @param captureQueryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/openDoor"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> openDoor(@RequestBody @Validated({APIPageGroup.class}) CaptureQueryVO captureQueryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONObject> resp = validite(bindingResult);
        JSONObject temp = new JSONObject();
        resp.setMessage("1400拦截器生效");
        resp.setData(temp);
        return resp;
//        try {
//            // 搜索条件满足的标识
//            boolean flag = true;
//            // 社区编号数组
//            captureQueryVO.setVillageCodes(getVillageCodes(captureQueryVO.getVillageCodes()));
//            String[] villages = StringUtils.isNotBlank(captureQueryVO.getVillageCodes()) ? captureQueryVO.getVillageCodes().split(CommonConstant.SPLIT_COMMA) : null;
//            // 设备编号数组
//            String[] deviceIds = StringUtils.isNotBlank(captureQueryVO.getDeviceIdStr()) ? captureQueryVO.getDeviceIdStr().split(CommonConstant.SPLIT_COMMA) : null;
//
//            if (null == villages || villages.length == 0) {
//                // 未选择社区时查询当前用户权限下的全部小区
//                String userIds = captureQueryVO.getUserIds();
//                UserPermission userPermission = this.organizationRegionService.findCurrentPersion(userIds);
//                villages = userPermission.getVillageCodes();
//                // 查询小区下的全部设备
//                if (null != villages && villages.length > 0) {
//                    List<Camera> cameras = this.caputerService.getAllCamera1(villages);
//                    List<String> list = new ArrayList<>();
//                    for (Camera camera : cameras) {
//                        list.add(camera.getCameraId());
//                    }
//                    deviceIds = list.toArray(new String[0]);
//                    captureQueryVO.setDeviceIds(deviceIds);
//                    if (deviceIds.length == 0) {
//                        flag = false;
//                    }
//                } else {
//                    flag = false;
//                }
//            } else if (null == deviceIds || deviceIds.length == 0) {
//                // 未选择设备时查询小区下的全部设备
//                List<Camera> cameras = this.caputerService.getAllCamera1(villages);
//                List<String> list = new ArrayList<>();
//                for (Camera camera : cameras) {
//                    list.add(camera.getCameraId());
//                }
//                deviceIds = list.toArray(new String[0]);
//                captureQueryVO.setDeviceIds(deviceIds);
//                if (deviceIds.length == 0) {
//                    flag = false;
//                }
//            }
//            // 若请求参数满足，则调用欧神查询抓拍数据
//            if (flag) {
//                String parmJson = JsonUtils.getFastjsonFromObject(captureQueryVO);
//                JSONObject data = null;
//                JSONObject jsonObject;
//                // 2-调用欧神终端注册接口查找人脸抓拍;其他-本地调用人脸抓拍库
//                if (PropertiesUtil.getCaptureOcean().equals(CommonConstant.COMMON_2)) {
//                    jsonObject = this.accessService.getCollectPages(parmJson);
//                } else {
//                    jsonObject = this.accessService.getCapturePages(parmJson);
//                }
//                this.logger.info("开门记录返回数据" + jsonObject.toJSONString());
//                // 请求成功封装响应数据（主要是处理设备信息）
//                if (StringUtils.checkSuccess(jsonObject)) {
//                    data = (JSONObject) jsonObject.get("data");
//                    JSONArray datas = data.getJSONArray("datas");
//                    String deviceId = "";
//                    com.ss.facesys.data.resource.common.model.Camera device = null;
//                    for (int i = 0; i < datas.size(); i++) {
//                        JSONObject op = datas.getJSONObject(i);
//                        if (!deviceId.equals(op.getString("deviceId"))) {
//                            deviceId = op.getString("deviceId");
//                            device = this.cameraService.findDevice(deviceId);
//                        }
//                        if (device != null) {
//                            op.put("deviceName", device.getCameraName());
//                            op.put("deviceAddress", (device.getInstallAdd() == null) ? device.getCameraName() : device.getInstallAdd());
//                            op.put("lat", String.valueOf(device.getLat()));
//                            op.put("lng", String.valueOf(device.getLon()));
//                        }
//                    }
//                    data.put("datas", datas);
//                }
//                resp.setData(data);
//            }
//        } catch (Exception e) {
//            this.logger.error("开门记录分页查询失败原因：+" + e.toString(), e);
//            resp = createFailResponse();
//            resp.setCode(ResultCode.OPENDOOR_FAILED_CODE);
//            resp.setMessage("操作失败！请联系管理员！");
//        }
//        return resp;
    }


    @RequestMapping(value = {"/compare"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "底库对比", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<CaptureCompareDTO> captureCompare(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) CaptureCompareVO compareVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<CaptureCompareDTO> resp = validite(bindingResult);
        try {
            CaptureCompareDTO compareDTO = new CaptureCompareDTO();
            BeanUtils.copyProperties(compareVO, compareDTO);

            String fadbId = this.villageService.getVillageFacedbId(compareVO.getVillageCode());
            if (StringUtils.isNotBlank(fadbId)) {
                Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

                String captureUrlFull = compareVO.getCaptureUrlFull().replace("http://", "");
                map.put("img", captureUrlFull.substring(captureUrlFull.indexOf("/", 1), captureUrlFull.length()));
                map.put("imgType", CommonConstant.IMGTYPE_URL);
                map.put("topN", NumberConstant.ONE);
                map.put("thresholdMin", Float.valueOf(PropertiesUtil.getThreShold()));
                map.put("facedbIds", new String[]{fadbId});
                String parmJson = JsonUtils.getFastjsonFromObject(map);
                this.logger.info("底库检索请求参数" + parmJson);
                JSONObject jsonObject = this.accessService.getRecogRegisterDb(parmJson);
                this.logger.info("底库检索返回结果" + jsonObject.toString());
                List<RegisterDTO> registerDTOs = new ArrayList<RegisterDTO>();

                if (StringUtils.checkSuccess(jsonObject)) {
                    JSONArray data = (JSONArray) jsonObject.get("data");
                    registerDTOs = JSONArray.parseArray(data.toString(), RegisterDTO.class);
                    Iterator iterator = registerDTOs.iterator();
                    if (iterator.hasNext()) {
                        RegisterDTO re = (RegisterDTO) iterator.next();
                        compareDTO.setCardId(re.getCardId());
                        compareDTO.setCardPathFull(re.getCardPathFull());
                        compareDTO.setName(re.getName());
                        String facedbName = re.getFaceDbName();
                        if (!facedbName.contains("注册库")) {
                            facedbName = facedbName + "注册库";
                        }
                        compareDTO.setFaceDbName(facedbName);
                        compareDTO.setRecogScore(re.getRecogScore());
                    }

                }
            }

            resp.setData(compareDTO);
        } catch (Exception e) {
            resp = createFailResponse();
            this.logger.error("底库对比失败，原因：" + e.toString(), e);
            resp.setMessage("底库对比失败");
        }
        return resp;
    }

    /**
     * 以图搜图
     *
     * @param captureImgVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/searchCaptureImg"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> searchCaptureImg(@RequestBody @Validated({APIPageGroup.class, APIRegisterRecogGroup.class}) CaptureImgVO captureImgVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 图片内容校验-当前默认采用base64图片类型
            captureImgVO.setImgType(CommonConstant.IMGTYPE_BASE64);
            if (!Base64ImageUtils.isCheckBase64(captureImgVO.getImg(), Constants.IMAGE_TYPE.split(CommonConstant.SPLIT_COMMA))) {
                return createFailResponse(ResultCode.PAGE_FAILED_CODE, "图片格式有误");
            }
            // 图片结果列表
            List<PersonCaptureDTO> pList = new ArrayList<>();
            boolean flag = true;
            // 社区编号数组
            captureImgVO.setVillageCodes(getVillageCodes(captureImgVO.getVillageCodes()));
            String[] villages = StringUtils.isNotBlank(captureImgVO.getVillageCodes()) ? captureImgVO.getVillageCodes().split(CommonConstant.SPLIT_COMMA) : null;
            // 设备编号数组
            String[] deviceIds = StringUtils.isNotBlank(captureImgVO.getDeviceIdStr()) ? captureImgVO.getDeviceIdStr().split(CommonConstant.SPLIT_COMMA) : null;
            Map<String, Object> restMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            // 请求类型[1-人脸抓拍以图搜图,2-开门记录以图搜图]
            if (CommonConstant.COMMON_1.equals(captureImgVO.getRequestType())) {
                flag = checkDeviceIds(deviceIds, villages, captureImgVO);
            } else if (CommonConstant.COMMON_2.equals(captureImgVO.getRequestType())) {
                flag = checkDeviceIds(deviceIds, villages, captureImgVO);
            }
            // 调用欧神请求
            if (flag) {
                String parmJson = JsonUtils.getFastjsonFromObject(captureImgVO);
                this.logger.info("以图搜图请求参数：" + parmJson);
                JSONObject jsonObject = this.accessService.getRecogCameraDb(parmJson);
                this.logger.info("以图搜图返回数据：" + jsonObject.toJSONString());
                // 将结果转换为数据传输对象
                List<PersonCaptureDTO> serachImgList = BaseFormatJsonUtil.formatList(jsonObject.get("data"), PersonCaptureDTO.class);
                // 结果分页
                int pageSize = captureImgVO.getPageSize();
                int currentPage = (captureImgVO.getCurrentPage() - 1) * pageSize;
                int totalRows = 0;
                if (null != serachImgList) {
                    totalRows = serachImgList.size();
                    pList = (currentPage + pageSize > totalRows) ? serachImgList.subList(currentPage, totalRows) : serachImgList.subList(currentPage, currentPage + pageSize);
                }
                // 处理抓拍数据中的设备信息
                String deviceId = "";
                com.ss.facesys.data.resource.common.model.Camera device = null;
                for (PersonCaptureDTO captureDTO : pList) {
                    if (!deviceId.equals(captureDTO.getDeviceId())) {
                        deviceId = captureDTO.getDeviceId();
                        device = this.cameraService.findDevice(deviceId);
                    }
                    if (device != null) {
                        captureDTO.setLat(String.valueOf(device.getLat()));
                        captureDTO.setLng(String.valueOf(device.getLon()));
                        captureDTO.setDeviceName(device.getCameraName());
                        captureDTO.setDeviceAddress((device.getInstallAdd() == null) ? device.getCameraName() : device.getInstallAdd());
                    }
                }
                // 封装结果
                restMap.put("currentPage", captureImgVO.getCurrentPage());
                restMap.put("pageSize", pageSize);
                restMap.put("totalRows", totalRows);
                restMap.put("pList", pList);
                resp.setData(restMap);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
            resp.setCode(ResultCode.PAGE_FAILED_CODE);
            this.logger.error("以图搜图查询失败原因：" + e.toString(), e);
        }
        return resp;
    }

    private boolean checkDeviceIds(String[] deviceIds, String[] villages, CaptureImgVO captureImgVO) {
        if (null != villages && villages.length > 0) {
            if (deviceIds == null || deviceIds.length == 0) {
                getVillageDeviceIds(deviceIds, villages, captureImgVO);
                return (captureImgVO.getDeviceIds().length > 0);
            }
        } else {
            // 未选择小区时查询当前用户权限下的数据
            String userIds = captureImgVO.getUserIds();
            UserPermission userPermission = this.organizationRegionService.findCurrentPersion(userIds);
            villages = userPermission.getVillageCodes();
            if (null == villages || villages.length == 0) {
                this.logger.error("以图搜图失败：用户{}没有任何小区权限", userIds);
                return false;
            }
            getVillageDeviceIds(deviceIds, villages, captureImgVO);
            return (captureImgVO.getDeviceIds().length > 0);
        }
        return true;
    }

    private void getVillageDeviceIds(String[] deviceIds, String[] villages, CaptureImgVO captureImgVO) {
        if (null != villages && villages.length > 0) {
            List<Camera> cameras = this.caputerService.getAllCamera(villages);
            List<String> list = new ArrayList<>();
            for (Camera camera : cameras) {
                list.add(camera.getCameraId());
            }
            deviceIds = list.toArray(new String[0]);
        }
        captureImgVO.setDeviceIds(deviceIds);
    }

    /**
     * 查询小区下的抓拍像机列表
     *
     * @param queryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/searchDevices"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Camera>> searchDevices(@RequestBody CameraQueryVO queryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Camera>> resp = validite(bindingResult);
        try {
            resp.setData(null);
            String[] villages;
            queryVO.setVillageCodes(getVillageCodes(queryVO.getVillageCodes()));
            if (StringUtils.isNotBlank(queryVO.getVillageCodes()) && (villages = queryVO.getVillageCodes().split(CommonConstant.SPLIT_COMMA)).length > 0) {
                resp.setData(this.caputerService.getAllCamera(villages));
            }
        } catch (Exception e) {
            this.logger.error("根据小区编号查询所有的普通摄像机失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/searchOpenDoorDevices"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Camera>> searchOpenDoorDevices(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) VehicleRecord vehicleRecord, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Camera>> resp = validite(bindingResult);

        try {
            String[] villages = vehicleRecord.getVillages();
            List<Camera> cameras = null;
            if (null != villages) {
                cameras = this.caputerService.getAllCamera1(villages);
            }
            resp.setData(cameras);
        } catch (Exception e) {
            this.logger.error("根据小区code查询所有的大眼睛相机失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

}
