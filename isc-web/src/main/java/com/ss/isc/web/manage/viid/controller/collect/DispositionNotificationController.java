package com.ss.isc.web.manage.viid.controller.collect;

import com.ss.isc.data.collect.client.IAlarmVehicleService;
import com.ss.isc.data.collect.common.model.AlarmVehicle;
import com.ss.isc.data.resource.common.model.Camera;
import com.ss.isc.data.viid.common.dto.common.*;
import com.ss.isc.data.viid.common.util.BaseHttpUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CacheConstant;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
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
* VIID-告警
* @author chao
* @create 2019/10/31
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/viid/collect/dispositionNotification"})
public class DispositionNotificationController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(DispositionNotificationController.class);

    @Resource
    private IAlarmVehicleService alarmVehicleService;

    private String MOTORVEHICLE_URL = "/viid/collect/dispositionNotification/motorVehicle";

    /**
     * VIID告警-过车信息
     * @param para
     * @return
     */
    @RequestMapping(value = {"/motorVehicle"}, method = {RequestMethod.POST})
    public String motorVehicle(@RequestBody DispositionNotificationObjectList para) {
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
            List<AlarmVehicle> alarmVehicleList = new ArrayList<>();
            //告警信息集合
            List<DispositionNotification> dispositionNotificationList = para.getDispositionNotificationObjectList().getDispositionNotificationObject();
            for (DispositionNotification dispositionNotification: dispositionNotificationList) {
                AlarmVehicle alarmVehicle = new AlarmVehicle();
                BeanUtils.copyProperties(dispositionNotification.getMotorVehicleObject(), alarmVehicle);
                alarmVehicle.setNotificationID(dispositionNotification.getNotificationID());
                alarmVehicle.setDispositionID(dispositionNotification.getDispositionID());
                alarmVehicle.setTitle(dispositionNotification.getTitle());
                alarmVehicle.setTriggerTime(dispositionNotification.getTriggerTime());
                alarmVehicle.setCntObjectID(dispositionNotification.getCntObjectID());
                //过车信息中添加小区信息
                if (StringUtils.isBlank(alarmVehicle.getVillageCode()) && cameraList != null){
                    for (Camera camera: cameraList) {
                        if (camera.getCameraId().equals(alarmVehicle.getCameraId())){
                            alarmVehicle.setVillageCode(camera.getVillageCode());
                        }
                    }
                }
                alarmVehicleList.add(alarmVehicle);
            }
            //新增过车告警信息
            String result = this.alarmVehicleService.insertAlarmVehicle(alarmVehicleList);
            if ("success".equals(result)){
                fromObject = BaseHttpUtil.createResponse(MOTORVEHICLE_URL, "0", "添加过车告警信息成功!", "00000000");
            } else {
                fromObject = BaseHttpUtil.createResponse(MOTORVEHICLE_URL, "1", "添加过车告警信息失败!", null);
                this.logger.error("添加过车告警信息失败!");
            }
        }catch (Exception e){
            fromObject = BaseHttpUtil.createResponse(MOTORVEHICLE_URL, "1", "添加过车告警信息失败!", null);
            this.logger.error("添加过车告警信息失败，原因：" + e.toString(), e);
        }
        return fromObject;
    }

}
