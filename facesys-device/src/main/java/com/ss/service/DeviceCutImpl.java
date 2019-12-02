package com.ss.service;

import com.ss.access.IDeviceCut;
import com.ss.access.IDeviceDo;
import com.ss.model.CutFlowState;
import com.ss.model.PushFlowState;
import com.ss.thread.CameraThreadPool;
import com.ss.thread.CutFlowThread;
import com.ss.thread.FlowThread;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备开启抽帧
 * @author 李爽超 chao
 * @create 2019/09/05
 * @email lishuangchao@ss-cas.com
 **/
@Service
public class DeviceCutImpl implements IDeviceCut {

    private List<CutFlowState> cutFlowStates = new ArrayList<>();

    private int size = 0;

    /**
     * 开启抽帧
     * @param cutFlowStates
     * @param size
     */
    @Override
    public void startCutFlow(List<CutFlowState> cutFlowStates, int size) {
        this.cutFlowStates = cutFlowStates;
        this.size = size;
        if (0 == this.cutFlowStates.get(size).getState()){
            //创建抽帧线程
            CutFlowThread flowThread = new CutFlowThread(this.cutFlowStates.get(size).getCode(), this);
            this.size++;
            //放入线程池
            CameraThreadPool.getThread().execute(flowThread);
        }
    }

    @Override
    public List<CutFlowState> getCutFlowStates(){
        return this.cutFlowStates;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }
}
