package com.ss.thread;

import com.ss.access.IDeviceDo;
import com.ss.business.VideoProcessor;
import com.ss.model.PushFlowState;
import com.ss.utils.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * 设备推流
 * @author 李爽超 chao
 * @create 2019/08/30
 * @email lishuangchao@ss-cas.com
 **/
public class FlowThread implements Runnable {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private VideoProcessor videoProcessor;

    private String code;

    private IDeviceDo deviceDo;

    public FlowThread(String code, IDeviceDo deviceDo){
        this.code = code;
        this.videoProcessor = ApplicationContextProvider.getBean(VideoProcessor.class);
        this.deviceDo = deviceDo;
    }

    @Override
    public void run() {
        try {
            //推流开启
            videoProcessor.startRemoteLive(code, deviceDo);
        } catch (Exception e) {
            this.logger.error("推流失败原因：+" + e.toString(), e);
            if(deviceDo != null){
                List<PushFlowState> pushFlowStates = deviceDo.getPushFlowStates();
                pushFlowStates.get(deviceDo.getSize() - 1).setState(2);
                if (deviceDo.getSize() < pushFlowStates.size()) {
                    //下个设备推流开启
                    deviceDo.startPushFlow(pushFlowStates, deviceDo.getSize());
                }
            }
        }
    }

}
