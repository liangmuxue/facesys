package com.ss.access;

import com.ss.model.PushFlowState;
import java.util.List;
/**
* 设备推流
* @author chao
* @create 2019/9/16
* @email lishuangchao@ss-cas.com
**/
public interface IDeviceDo {

    void startPushFlow(List<PushFlowState> pushFlowStates, int size);

    List<PushFlowState> getPushFlowStates();

    int getSize();

    void setSize(int size);
}
