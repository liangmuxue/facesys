package com.ss.facesys.data.resource.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.j7cai.common.util.JsonUtils;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.common.dto.CameraVmsDTO;
import com.ss.facesys.data.resource.common.dto.ImportCamera;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.web.CameraQueryVO;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.autoconfigure.DeviceProperties;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.constant.HttpConstant;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.http.BaseHttpUtil;
import com.ss.facesys.util.jedis.JedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CameraServiceImpl
 * @author FrancisYs
 * @date 2019/8/19
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class CameraServiceImpl extends BaseServiceImpl implements ICameraService {

    private static final Log LOG = LogFactory.getLog(CameraServiceImpl.class);

    @Autowired
    private CameraMapper cameraMapper;
    @Resource
    public JedisUtil jedisUtil;

    /**
     * 查询像机详情
     * @param camera
     * @return
     */
    @Override
    public Camera selectOne(Camera camera) {
        return this.cameraMapper.selectById(camera);
    }

    @Override
    public List<Camera> findCameras(Camera camera) {
        List<Camera> list = null;
        List<Camera> tempList = null;

        if (this.jedisUtil.hasKey("ALL_CAMERA")) {
            list = (List) this.jedisUtil.get("ALL_CAMERA");
        } else {

            Camera entity = new Camera();
            list = this.cameraMapper.findCameras(entity);

            this.jedisUtil.set("ALL_CAMERA", list);
        }
        if (null != camera) {
            if (!list.isEmpty()) {
                if (StringUtils.isNotBlank(camera.getVillageCode())) {
                    tempList = new ArrayList<Camera>();
                    for (Camera c : list) {
                        if (camera.getVillageCode().equals(c.getVillageCode())) {
                            tempList.add(c);
                        }
                    }
                    list.clear();
                    list.addAll(tempList);
                }
            }
            if (!list.isEmpty()) {
                if (camera.getCameraType() != null && camera.getCameraType().intValue() != 0) {
                    tempList = new ArrayList<Camera>();
                    for (Camera c : list) {
                        if (camera.getCameraType().equals(c.getCameraType())) {
                            tempList.add(c);
                        }
                    }
                    list.clear();
                    list.addAll(tempList);
                }
            }
        }
        return list;
    }

    /**
     * 根据设备ID查找设备
     * @param cameraId
     * @return
     */
    @Override
    public Camera findDevice(String cameraId) {
        boolean searchFlag = true;
        Camera device = null;
        List<Camera> list;
        Object object = this.jedisUtil.get(CacheConstant.REDIS_KEY_CAMERA);
        if (object == null) {
            Camera cm = new Camera();
            list = findCameras(cm);
        } else {
            list = (List) object;
        }
        for (Camera camera : list) {
            if (cameraId.equals(camera.getCameraId())) {
                device = camera;
                searchFlag = false;
                break;
            }
        }
        if (searchFlag) {
            device = this.cameraMapper.selectByCameraId(cameraId);
            this.jedisUtil.del(CacheConstant.REDIS_KEY_CAMERA);
        }
        return device;
    }

    /**
     * 新增像机
     * @param camera
     * @return
     * @throws Exception
     */
    @Override
    public int insertCamera(Camera camera) throws Exception {
        int num = 0;
        String deviceId = null;
        boolean flag = true;
        ImportCamera check = null;
        //检查ip,编号是否重复
        if (StringUtils.isNotBlank(camera.getStandardCameraId())){
            check = this.cameraMapper.checkCamera(camera);
        }
        if (check != null){
            return num;
        }
        if (PropertiesUtil.getOceanOrViid() != 0){
            // 同步添加至欧神设备
            deviceId = addOsCamera(camera);
            if (StringUtils.isNotBlank(deviceId)) {
                camera.setCameraId(deviceId);
            } else {
                flag = false;
            }
        }
        if (flag) {
            num = this.cameraMapper.insertCamera(camera);
        } else {
            LOG.error("内网同步 - 新增 设备失败：");
            throw new Exception("内网同步 - 新增像机失败 deviceId = " + deviceId);
        }
        return num;
    }

    /**
     * 修改像机信息
     * @param camera
     * @return
     * @throws Exception
     */
    @Override
    public int updateCamera(Camera camera) throws Exception {
        Camera c = this.cameraMapper.selectById(camera);
        camera.setCameraId(c.getCameraId());
        camera.setCameraType(c.getCameraType());
        boolean osflag = true;
        JSONObject resultJson = null;
        String postUrl = null;
        Map<String, Object> parm = null;
        int updateCamera = 0;
        if (PropertiesUtil.getOceanOrViid() != 0){
            int cameraType = camera.getCameraType();
            if (cameraType > 0) {
                // 普通摄像机调用 欧神-新增像机接口
                postUrl = PropertiesUtil.getOshttp() + HttpConstant.CAMERA_EDIT;
                parm = new HashMap<>();
                BigDecimal b = new BigDecimal(camera.getLat());
                double f1 = b.setScale(4, 4).doubleValue();
                BigDecimal b1 = new BigDecimal(camera.getLon());
                double f2 = b1.setScale(4, 4).doubleValue();
                parm.put("deviceId", camera.getCameraId());
                parm.put("deviceName", camera.getCameraName());
                parm.put("lat", f1);
                parm.put("lng", f2);
                parm.put("address", camera.getInstallAdd());
                parm.put("deviceCode", camera.getThirdCameraId());
                parm.put("para", camera.getStreamSource());
                parm.put("cameraType", "RTSP");
            } else {
                osflag = false;
            }
            if (osflag) {
                String result = BaseHttpUtil.httpPost(JsonUtils.getFastjsonFromObject(parm), postUrl, null);
                LOG.info("相机修改" + result);
                resultJson = JSONObject.parseObject(result);
                // 欧神修改失败抛出异常
                if (!StringUtils.checkSuccess(resultJson)) {
                    LOG.error("内网同步 -  修改设备失败：" + resultJson.getString("message"));
                    throw new Exception("内网同步 -修改设备失败 deviceName = " + camera.getCameraName());
                }
            }
        }
        updateCamera = this.cameraMapper.updateCamera(camera);
        return updateCamera;
    }

    /**
     * 删除设备
     * @param camera
     * @return
     * @throws Exception
     */
    @Override
    public int deleteCamera(Camera camera) throws Exception {
        Camera c = this.cameraMapper.selectById(camera);
        //设备停止推流，停止抽帧
        if (c != null){
            String resultString = com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDevicePushFlowStopUrl(), c.getCameraId(), c.getCameraName());
            if (!CommonConstant.COMMON_2.equals(c.getCameraType()) && resultString != null){
                resultString = com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDeviceCutFlowStopUrl(), c.getCameraId(), c.getCameraName());
            }
            if (resultString == null){
                return 0;
            }
        }
        if (c != null && StringUtils.isNotBlank(c.getCameraId())) {
            if (c.getCameraType() > 0) {
                // 普通像机类型调用欧神删除像机设备接口
                String postUrl = PropertiesUtil.getOshttp() + HttpConstant.CAMERA_DELETE;
                Map<String, Object> parm = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
                parm.put("deviceId", c.getCameraId());
                String result = BaseHttpUtil.httpPost(JSONObject.toJSON(parm).toString(), postUrl, null);
                JSONObject resultJson = JSONObject.parseObject(result);
                if (!StringUtils.checkSuccess(resultJson)) {
                    LOG.error("内网同步 -  刪除设备失败：" + resultJson.getString("message"));
                    throw new Exception("内网同步 - 刪除像机失败 deviceId = " + c.getCameraId());
                }
            }
        }
        int num = this.cameraMapper.deleteCamera(camera);
        return num;
    }

    /**
     * 切换状态
     * @param camera
     * @return
     * @throws Exception
     */
    @Override
    public int opStatus(Camera camera) throws Exception {
        Camera c = this.cameraMapper.selectById(camera);
        //设备推流，抽帧
        if (c != null){
            String resultString = null;
            if(camera.getCameraEnabled() == 1 && c.getCameraEnabled() == 0) {
                resultString = com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDevicePushFlowUrl(), c.getCameraId(), c.getCameraName());
                if (!CommonConstant.COMMON_2.equals(c.getCameraType()) && resultString != null) {
                    resultString = com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDeviceCutFlowUrl(), c.getCameraId(), c.getCameraName());
                }
            } else if (camera.getCameraEnabled() == 0 && c.getCameraEnabled() == 1) {
                resultString = com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDevicePushFlowStopUrl(), c.getCameraId(), c.getCameraName());
                if (!CommonConstant.COMMON_2.equals(c.getCameraType()) && resultString != null) {
                    resultString = com.ss.utils.BaseHttpUtil.deviceHttpPost(DeviceProperties.getDeviceUrl() + DeviceProperties.getDeviceCutFlowStopUrl(), c.getCameraId(), c.getCameraName());
                }
            }
            if (resultString == null) {
                return 0;
            }
        }
        //修改状态
        int result = this.cameraMapper.opStatus(camera);
        return result;
    }

    /**
     * 查询像机分页列表
     * @param queryVO
     * @return
     */
    @Override
    public List<ImportCamera> findAllCameras(CameraQueryVO queryVO) {
        PageHelper.startPage(queryVO.getCurrentPage(), queryVO.getPageSize());
        List<ImportCamera> findAllCameras = this.cameraMapper.findAllCameras(queryVO);
        return findAllCameras;
    }

    /**
     * 欧神设备绑定
     * @param deviceId
     * @param engineType
     */
    public void engineBind(String deviceId, int engineType) {
        Map<String, Object> parm = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        parm.put("engineType", engineType);
        parm.put("deviceId", deviceId);
        String result = BaseHttpUtil.httpPost(JSONObject.toJSON(parm).toString(), PropertiesUtil.getOshttp() + HttpConstant.ENGINE_BIND, null);
        JSONObject engineJson = JSONObject.parseObject(result);
        if (!StringUtils.checkSuccess(engineJson)) {
            LOG.error("内网同步 - 新增 设备绑定引擎失败：" + engineJson.getString("message"));
        }
    }

    @Override
    public String batchImport(List<ImportCamera> tempList) throws Exception {
        List<ImportCamera> updateList = new ArrayList<ImportCamera>();
        List<ImportCamera> insertList = new ArrayList<ImportCamera>();

        boolean flag = true;
        int insertNum = 0;
        int updateNum = 0;
        StringBuilder failureMsg = new StringBuilder();
        int index = 0;
        for (ImportCamera importCamera : tempList) {
            index++;
            if (StringUtils.isBlank(importCamera.getCameraId())) {
                failureMsg.append("第" + index + "相机编号为空");
                flag = false;
            }
            if (StringUtils.isBlank(importCamera.getCameraName())) {
                failureMsg.append("第" + index + "相机名称为空");
                flag = false;
            }
            if (importCamera.getCameraType().intValue() < 0) {
                failureMsg.append("第" + index + "相机类型");
                flag = false;
            }
            if (importCamera.getCameraState().intValue() < 0) {
                failureMsg.append("第" + index + "相机状态");
                flag = false;
            }
            if (StringUtils.isBlank(importCamera.getStreamSource())) {
                failureMsg.append("第" + index + "视频流为空");
                flag = false;
            }
            if (flag) {
                ImportCamera entity = this.cameraMapper.check(importCamera);
                if (entity == null) {
                    insertList.add(importCamera);
                    insertNum++;
                    continue;
                }
                entity.setId(entity.getId());
                updateList.add(importCamera);
                updateNum++;
            }
        }

        if (flag) {
            if (insertList.size() > 0) {

                for (ImportCamera importCamera : insertList) {
                    int cameraType = importCamera.getCameraType().intValue();
                    if (cameraType != 3 && cameraType != 4) {
                        Camera camera = new Camera();
                        BeanUtils.copyProperties(importCamera, camera);
                        String deviceId = addOsCamera(camera);
                        if (StringUtils.isNotBlank(deviceId)) {
                            importCamera.setCameraId(deviceId);
                            this.cameraMapper.insertImportCamera(importCamera);
                        }
                        continue;
                    }
                    this.cameraMapper.insertImportCamera(importCamera);
                }
            }


            if (updateList.size() > 0) {
                this.cameraMapper.updateBatch(updateList);
            }
        }
        return flag ? ("成功导入数据，新增" + insertNum + "条，更新" + updateNum + "条") : ("导入失败" + failureMsg.toString());
    }

    /**
     * 根据像机类型[cameraType]查询像机[cameraId]集合
     * @param dto
     * @return
     */
    @Override
    public List<String> cameraIdList(Camera dto) {
        User user = new User();
        user.setUserId(dto.getUserIds());
        return this.cameraMapper.cameraIdList(dto);
    }

    /**
     * 添加欧神设备
     * @param camera
     * @return
     */
    public String addOsCamera(Camera camera) {
        JSONObject resultJson;
        Map<String, Object> parm = null;
        String postUrl = null;
        String deviceId = null;
        // 普通摄像机调用 欧神-新增像机接口
        postUrl = PropertiesUtil.getOshttp() + HttpConstant.CAMERA_ADD;
        // 设置请求参数
        parm = new HashMap<String, Object>();
        parm.put("deviceName", camera.getCameraName());
        // 经纬度精度处理
        BigDecimal b = new BigDecimal(camera.getLat());
        double f1 = b.setScale(4, 4).doubleValue();
        BigDecimal b1 = new BigDecimal(camera.getLon());
        double f2 = b1.setScale(4, 4).doubleValue();
        parm.put("lat", f1);
        parm.put("lng", f2);
        parm.put("address", camera.getInstallAdd());
        parm.put("deviceCode", camera.getThirdCameraId());
        parm.put("para", camera.getStreamSource());
        parm.put("cameraType", "RTSP");
        // 发送请求调用欧神接口
        String result = BaseHttpUtil.httpPost(JsonUtils.getFastjsonFromObject(parm), postUrl, null);
        LOG.info("相机新增" + result);
        resultJson = JSONObject.parseObject(result);

        if (StringUtils.checkSuccess(resultJson)) {
            parm = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            parm.put("engineType", 1);
            deviceId = resultJson.getString("data");
            //engineBind(deviceId, 1);
            //engineBind(deviceId, 2);
        }
        return deviceId;
    }


    @Override
    public List<String> findCameraIds(Camera camera) {
        return this.cameraMapper.findCameraIds(camera);
    }


    @Override
    public Map<String, Object> playVideo(Camera camera) {
        Map<String, Object> map = new HashMap<String, Object>();
        CameraVmsDTO cameraVmsDTO = new CameraVmsDTO();
        cameraVmsDTO.setIp(PropertiesUtil.getVmsIp());
        cameraVmsDTO.setPort(PropertiesUtil.getVmsPort());
        cameraVmsDTO.setUserName(PropertiesUtil.getVmsUsername());
        cameraVmsDTO.setPassword(PropertiesUtil.getVmsPassword());

        Camera c = (Camera) this.cameraMapper.selectOne(camera);
        Camera entity = new Camera();
        entity.setVillageCode(c.getVillageCode());
        entity.setBuildingNo(c.getBuildingNo());
        entity.setCameraType(NumberConstant.SEVEN);
        List<Camera> list = this.cameraMapper.select(entity);
        map.put("vmsInfo", cameraVmsDTO);
        map.put("devices", list);

        return map;
    }


    @Override
    public JSONObject vmsPreview(String standardCameraId) throws Exception {
        try {
            JSONObject returnObj = null;

            String httpPrefix = "http://" + PropertiesUtil.getVmsIp() + ":" + PropertiesUtil.getVmshttpPort();
            Map<String, String> parm = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

            String vmsLoginUrl = httpPrefix + HttpConstant.VMS_LOGIN;
            parm.put("LoginName", PropertiesUtil.getVmsUsername());
            parm.put("Password", PropertiesUtil.getVmsPassword());
            LOG.info("VMS登陆请求参数" + JsonUtils.getFastjsonFromObject(parm));
            String result = BaseHttpUtil.iscHttpPost(JsonUtils.getFastjsonFromObject(parm), vmsLoginUrl, null);
            LOG.info("VMS登陆返回信息" + result);
            JSONObject vmsObj = JSONObject.parseObject(result);
            if (vmsObj.getInteger("ResultCode").intValue() == CommonConstant.COMMON_0.intValue()) {
                String hmToken = vmsObj.getString("HmToken");

                String vmsDeviceUrl = httpPrefix + HttpConstant.VMS_DEVICE;
                Map<String, String> deviceParam = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
                deviceParam.put("Type", "2");
                deviceParam.put("HmToken", hmToken);
                String deviceResult = BaseHttpUtil.httpGet(vmsDeviceUrl + "/" + standardCameraId, deviceParam);
                LOG.info("VMS返回相机信息为" + deviceResult);
                if (StringUtils.isNotBlank(deviceResult)) {
                    JSONObject deviceObj = JSONObject.parseObject(deviceResult);
                    if (deviceObj.getInteger("ResultCode").intValue() == CommonConstant.COMMON_0.intValue()) {
                        returnObj = deviceObj;
                    }
                }
            }
            return returnObj;
        } catch (Exception e) {
            LOG.error("获取相机VMS流失败" + e.getMessage(), e);
            throw e;
        }
    }

}
