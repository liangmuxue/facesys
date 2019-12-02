package com.ss.service;

import com.ss.access.IDeviceDo;
import com.ss.model.PushFlowState;
import com.ss.thread.CameraThreadPool;
import com.ss.thread.FlowThread;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备推流
 * @author 李爽超 chao
 * @create 2019/09/05
 * @email lishuangchao@ss-cas.com
 **/
@Service
public class DeviceDoImpl implements IDeviceDo {

    private List<PushFlowState> pushFlowStates = new ArrayList<>();

    private int size = 0;

    @Override
    public void startPushFlow(List<PushFlowState> pushFlowStates, int size) {
        this.pushFlowStates = pushFlowStates;
        this.size = size;
        if (0 == this.pushFlowStates.get(size).getState()){
            //创建推流线程
            FlowThread flowThread = new FlowThread(this.pushFlowStates.get(size).getCode(), this);
            this.size++;
            //放入线程池
            CameraThreadPool.getThread().execute(flowThread);
        }
    }

    @Override
    public List<PushFlowState> getPushFlowStates(){
        return this.pushFlowStates;
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
