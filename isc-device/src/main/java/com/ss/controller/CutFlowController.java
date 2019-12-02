package com.ss.controller;

import com.alibaba.fastjson.JSON;
import com.ss.access.IDeviceCut;
import com.ss.business.PictureProcessor;
import com.ss.mapper.LocalCameraMapper;
import com.ss.model.Camera;
import com.ss.model.CutFlowState;
import com.ss.utils.DeviceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 设备视频抽帧
 * @author 李爽超 chao
 * @create 2019/09/12
 * @email lishuangchao@ss-cas.com
 **/
@RestController
@RequestMapping({"/deviceCut"})
public class CutFlowController {

    @Autowired
    private LocalCameraMapper localCameraMapper;
    @Autowired
    private IDeviceCut deviceCut;
    @Autowired
    private PictureProcessor pictureProcessor;

    private List<CutFlowState> cutFlowStates = new ArrayList<>();

    private int size = 0;

    /**
     * 抽帧开启
     * @param cameras
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/cutFlow"}, method = {RequestMethod.POST})
    public String pushFlow(@RequestBody Map<String, Object> cameras){

        List<Camera> param = (List<Camera>) cameras.get("cameras");
        String json = JSON.toJSONString(param);
        param = JSON.parseArray(json, Camera.class);
        String message = "";
        this.cutFlowStates.clear();
        for (Camera para: param) {
            Camera camera = localCameraMapper.findByCodeOffCut(para.getCameraId());
            if(camera != null){
                //检测图片存储路径是否存在
                File file = new File(DeviceUtil.getCutFlowUrl() + "/common/" + camera.getCameraId());
                if (!file.exists()){
                    file.mkdir();
                }
                CutFlowState cutFlowState = new CutFlowState(camera.getCameraId(),0);
                this.cutFlowStates.add(cutFlowState);
            } else {
                message += para.getCameraName();
            }
        }
        if (cutFlowStates.size() > 0){
            //开启设备抽帧
            deviceCut.startCutFlow(cutFlowStates, size);
        }
        if (!"".equals(message)){
            message += "设备不存在或已经启动抽帧! 其他设备抽帧启动成功!";
        } else {
            message = "设备抽帧启动成功!";
        }
        return message;
    }

    /**
     * 设备停止抽帧
     * @param cameras
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/stopCutFlow"}, method = {RequestMethod.POST})
    public String stopFlow(@RequestBody Map<String, Object> cameras){

        List<Camera> param = (List<Camera>) cameras.get("cameras");
        String json = JSON.toJSONString(param);
        param = JSON.parseArray(json, Camera.class);
        String message = "";
        for (Camera para: param) {
            //查询要停止抽帧设备
            Camera camera = localCameraMapper.findByCodeOnCut(para.getCameraId());
            if(camera != null) {
                //停止抽帧
                pictureProcessor.stopRemoteLive(camera.getCameraId());
            } else {
                message += para.getCameraName() + " ";
            }
        }
        if (!"".equals(message)){
            message += "设备不存在或已经停止抽帧! 其他设备抽帧停止成功!";
        } else {
            message = "设备抽帧停止成功!";
        }
        return message;
    }

    /**
     * 全部设备开启抽帧
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/cutAllFlow"}, method = {RequestMethod.POST})
    public String pushAllFlow(@RequestBody Map<String, Object> para){

        //查询所有未开启抽帧设备
        List<Camera> cameras = localCameraMapper.findCutStopCamera();
        this.cutFlowStates.clear();
        this.size = 0;
        for (Camera camera: cameras){
            //检测图片存储路径是否存在
            File file = new File(DeviceUtil.getCutFlowUrl() + "/common/" + camera.getCameraId());
            if (!file.exists()){
                file.mkdir();
            }
            this.cutFlowStates.add(new CutFlowState(camera.getCameraId(),0));
        }
        if (cutFlowStates.size() > 0){
            //开启抽帧
            deviceCut.startCutFlow(cutFlowStates, size);
        }
        return "全部设备抽帧启动成功!";
    }

    /**
     * 全部设备停止抽帧
     * @param para
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/stopCutAllFlow"}, method = {RequestMethod.POST})
    public String stopAllFlow(@RequestBody Map<String, Object> para){

        //查询所有已开启抽帧设备
        List<Camera> cameras = localCameraMapper.findCutCamera();
        for (Camera camera: cameras) {
            //停止抽帧
            pictureProcessor.stopRemoteLive(camera.getCameraId());
        }
        return "全部设备抽帧停止成功!";
    }
}
