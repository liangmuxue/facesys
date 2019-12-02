package com.ss.access;

import com.ss.model.CutFlowState;
import java.util.List;
/**
* 设备抽帧
* @author chao
* @create 2019/9/16
* @email lishuangchao@ss-cas.com
**/
public interface IDeviceCut {

    void startCutFlow(List<CutFlowState> cutFlowStates, int size);

    List<CutFlowState> getCutFlowStates();

    int getSize();

    void setSize(int size);
}
