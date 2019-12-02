package com.ss.controller;

import com.alibaba.fastjson.JSON;
import com.ss.access.IDeviceDo;
import com.ss.business.VideoProcessor;
import com.ss.mapper.LocalCameraMapper;
import com.ss.model.Camera;
import com.ss.model.PushFlowState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 设备推流
 * @author chao
 * @create 2019/8/30
 * @email lishuangchao@ss-cas.com
 **/

@RestController
@RequestMapping({"/deviceDo"})
public class DeviceDoController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IDeviceDo deviceDo;
    @Autowired
    private VideoProcessor videoProcessor;
    @Autowired
    private LocalCameraMapper localCameraMapper;

    private List<PushFlowState> pushFlowStates = new ArrayList<>();

    private int size = 0;


    /**
     * 设备推流
     * @param cameras
     * @return
     * @throws Exception
     */

    @RequestMapping(value = {"/pushFlow"}, method = {RequestMethod.POST})
    public String pushFlow(@RequestBody Map<String, Object> cameras){
        List<Camera> param = (List<Camera>) cameras.get("cameras");
        String json = JSON.toJSONString(param);
        param = JSON.parseArray(json, Camera.class);
        String message = "";
        this.pushFlowStates.clear();
        this.size = 0;
        for (Camera para: param) {
            Camera camera = localCameraMapper.findByCodeOffLine(para.getCameraId());
            if(camera != null){
                //开启推流线程
                PushFlowState pushFlowState = new PushFlowState(camera.getCameraId(),0);
                this.pushFlowStates.add(pushFlowState);
            } else {
                message += para.getCameraName();
            }
        }
        if (pushFlowStates.size() > 0){
            deviceDo.startPushFlow(pushFlowStates, size);
        }
        if (!"".equals(message)){
            message += "设备不存在或已经开启推流! 其他设备推流启动成功!";
        } else {
            message = "设备推流启动成功!";
        }
        return message;
    }


    /**
     * 设备停止推流
     * @param cameras
     * @return
     * @throws Exception
     */

    @RequestMapping(value = {"/stopFlow"}, method = {RequestMethod.POST})
    public String stopFlow(@RequestBody Map<String, Object> cameras){

        List<Camera> param = (List<Camera>) cameras.get("cameras");
        String json = JSON.toJSONString(param);
        param = JSON.parseArray(json, Camera.class);
        //停止推流
        String message = "";
        for (Camera para: param) {
            Camera camera = localCameraMapper.findByCodeOnLine(para.getCameraId());
            if(camera != null) {
                //停止推流
                videoProcessor.stopRemoteLive(camera.getCameraId());
            } else {
                message += para.getCameraName() + " ";
            }
        }
        if (!"".equals(message)){
            message += "设备不存在或已经停止推流! 其他设备推流停止成功!";
        } else {
            message = "设备推流停止成功!";
        }
        return message;
    }


    /**
     * 全部设备推流
     * @return
     * @throws Exception
     */

    @RequestMapping(value = {"/pushAllFlow"}, method = {RequestMethod.POST})
    public String pushAllFlow(@RequestBody Map<String, Object> para){

        List<Camera> cameras = localCameraMapper.findStopCamera();
        this.pushFlowStates.clear();
        this.size = 0;
        for (Camera camera: cameras) {
            this.pushFlowStates.add(new PushFlowState(camera.getCameraId(),0));
        }
        if (pushFlowStates.size() > 0){
            deviceDo.startPushFlow(pushFlowStates, size);
        }
        return "全部设备推流启动成功!";
    }


    /**
     * 全部设备停止推流
     * @return
     * @throws Exception
     */

    @RequestMapping(value = {"/stopAllFlow"}, method = {RequestMethod.POST})
    public String stopAllFlow(@RequestBody Map<String, Object> para){

        List<Camera> cameras = localCameraMapper.findPushCamera();
        for (Camera camera: cameras) {
            videoProcessor.stopRemoteLive(camera.getCameraId());
        }
        return "全部设备推流停止成功!";
    }

}

