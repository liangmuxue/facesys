package com.ss.facesys.web.manage.viid.controller.collect;

import com.ss.facesys.data.collect.client.IVehicleService;
import com.ss.facesys.data.collect.common.model.VehicleRecord;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.viid.common.dto.common.APE;
import com.ss.facesys.data.viid.common.dto.common.MotorVehicle;
import com.ss.facesys.data.viid.common.dto.common.SubscribeNotification;
import com.ss.facesys.data.viid.common.dto.common.SubscribeNotificationListObject;
import com.ss.facesys.data.viid.common.util.BaseHttpUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * VIID 通知
 * @author 李爽超 chao
 * @create 2019/10/30
 * @email lishuangchao@ss-cas.com
 **/
@RestController
@RequestMapping({"/viid/collect/subscribeNotification"})
public class SubscribeNotificationController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SubscribeNotificationController.class);

    @Resource
    private IVehicleService vehicleService;

    @Resource
    private ICameraService cameraService;

    private String MOTORVEHICLE_URL = "/viid/collect/vehicleNotification/motorVehicle";

    private String DEVICE_URL = "/viid/collect/vehicleNotification/device";

    /**
     * VIID通知-过车信息
     * @param para
     * @return
     */
    @RequestMapping(value = {"/motorVehicle"}, method = {RequestMethod.POST})
    public String motorVehicle(@RequestBody SubscribeNotificationListObject para) {
        String fromObject;
        try{
            List<Camera> cameraList = null;
            //获取相机信息
            if (this.jedisUtil.hasKey(CacheConstant.REDIS_KEY_CAMERA)) {
                Object object = this.jedisUtil.get(CacheConstant.REDIS_KEY_CAMERA);
                if (object != null){
                    cameraList = (List)object;
                }
            }
            List<VehicleRecord> vehicleRecordList = new ArrayList<>();
            List<MotorVehicle> motorVehicleList = new ArrayList<>();
            List<SubscribeNotification> subscribeNotificationList = para.getSubscribeNotificationListObject().getSubscribeNotificationObject();
            for (SubscribeNotification subscribeNotification: subscribeNotificationList) {
                List<MotorVehicle> motorVehicleObject = subscribeNotification.getMotorVehicleList().getMotorVehicleObject();
                motorVehicleList.addAll(motorVehicleObject);
            }
            for (MotorVehicle motorVehicle: motorVehicleList) {
                VehicleRecord vehicleRecord = new VehicleRecord();
                BeanUtils.copyProperties(motorVehicle, vehicleRecord);
                //过车信息中添加小区信息
                if (StringUtils.isBlank(vehicleRecord.getVillageCode()) && cameraList != null){
                    for (Camera camera: cameraList) {
                        if (camera.getCameraId().equals(vehicleRecord.getCameraId())){
                            vehicleRecord.setVillageCode(camera.getVillageCode());
                        }
                    }
                }
                vehicleRecordList.add(vehicleRecord);
            }
            //新增过车信息
            String result = this.vehicleService.batchInsertRecord(vehicleRecordList);
            if ("success".equals(result)){
                fromObject = BaseHttpUtil.createResponse(MOTORVEHICLE_URL, "0", "添加过车信息成功!", "00000000");
            } else {
                fromObject = BaseHttpUtil.createResponse(MOTORVEHICLE_URL, "1", "添加过车信息失败!", null);
                this.logger.error("添加过车信息失败!");
            }
        }catch (Exception e){
            fromObject = BaseHttpUtil.createResponse(MOTORVEHICLE_URL, "1", "添加过车信息失败!", null);
            this.logger.error("添加过车信息失败，原因：" + e.toString(), e);
        }
        return fromObject;
    }

    /**
     * VIID通知-设备信息
     * @param para
     * @return
     */
    @RequestMapping(value = {"/device"}, method = {RequestMethod.POST})
    public String device(@RequestBody SubscribeNotificationListObject para) {
        String fromObject;
        try{
            String message = "";
            String result = "success";
            List<APE> apeList = new ArrayList<>();
            List<SubscribeNotification> subscribeNotificationList = para.getSubscribeNotificationListObject().getSubscribeNotificationObject();
            for (SubscribeNotification subscribeNotification: subscribeNotificationList) {
                List<APE> apeObject = subscribeNotification.getDeviceList().getApeObject();
                apeList.addAll(apeObject);
            }
            for (APE ape: apeList) {
                Camera camera = new Camera();
                camera.setCameraId(ape.getApeId());
                camera.setCameraName(ape.getName());
                camera.setProductModel(ape.getModel());
                camera.setCameraIp(ape.getIpAddr());
                camera.setCameraPort(ape.getPort());
                camera.setLon(ape.getLongitude());
                camera.setLat(ape.getLatitude());
                camera.setInstallAdd(ape.getPlace());
                camera.setCompanyCode(ape.getOrgCode());
                camera.setInOutFlag(Integer.parseInt(ape.getMonitorDirection()));
                camera.setCameraState(Integer.parseInt(ape.getIsOnline()));
                camera.setLoginName(ape.getUserId());
                camera.setPassword(ape.getPassword());
                camera.setVillageCode(ape.getMonitorAreaDesc());
                camera.setCameraType(-1);
                //检查相机是否存在
                Camera selectOne = this.cameraService.findDevice(camera.getCameraId());
                if (selectOne != null){
                    //更新相机信息
                    camera.setId(selectOne.getId());
                    int updateCamera = this.cameraService.updateCamera(camera);
                    if (updateCamera == 0){
                        result = "failed";
                        message = message.concat(camera.getCameraId() + " ");
                    }
                } else {
                    //新增相机信息
                    int insertCamera = this.cameraService.insertCamera(camera);
                    if (insertCamera == 0){
                        result = "failed";
                        message =  message.concat(camera.getCameraId() + " ");
                    }
                }
            }
            //相机信息存入redis
            if (this.jedisUtil.hasKey(CacheConstant.REDIS_KEY_CAMERA)) {
                this.jedisUtil.del(CacheConstant.REDIS_KEY_CAMERA);
                this.cameraService.findCameras(new Camera());
            } else {
                this.cameraService.findCameras(new Camera());
            }
            if ("success".equals(result)){
                fromObject = BaseHttpUtil.createResponse(DEVICE_URL, "0", "设备信息处理成功!", "00000000");
            } else {
                fromObject = BaseHttpUtil.createResponse(DEVICE_URL, "1", message + "设备信息处理失败!", "null");
                this.logger.error(message + "设备信息处理失败!");
            }
        }catch (Exception e){
            fromObject = BaseHttpUtil.createResponse(DEVICE_URL, "1", "设备信息处理失败!", "null");
            this.logger.error("设备信息处理失败，原因：" + e.toString(), e);
        }
        return fromObject;
    }
}
