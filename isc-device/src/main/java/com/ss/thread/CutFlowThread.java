package com.ss.thread;

import com.ss.access.IDeviceCut;
import com.ss.business.PictureProcessor;
import com.ss.model.CutFlowState;
import com.ss.utils.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * 设备抽帧线程
 * @author 李爽超 chao
 * @create 2019/09/12
 * @email lishuangchao@ss-cas.com
 **/
public class CutFlowThread implements Runnable {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private PictureProcessor pictureProcessor;

    private String code;

    private IDeviceCut deviceCut;

    public CutFlowThread(String code, IDeviceCut deviceCut){
        this.code = code;
        this.pictureProcessor = ApplicationContextProvider.getBean(PictureProcessor.class);
        this.deviceCut = deviceCut;
    }

    @Override
    public void run() {
        try {
            pictureProcessor.startRemoteLive(code, deviceCut);
        } catch (Exception e) {
            this.logger.error("抽帧失败原因：+" + e.toString(), e);
            if(deviceCut != null){
                List<CutFlowState> cutFlowStates = deviceCut.getCutFlowStates();
                cutFlowStates.get(deviceCut.getSize() - 1).setState(2);
                if (deviceCut.getSize() < cutFlowStates.size()){
                    //开启下个设备抽帧
                    deviceCut.startCutFlow(cutFlowStates, deviceCut.getSize());
                }
            }
        }
    }
}
