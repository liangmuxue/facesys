package com.ss.model;

/**
 * 抽帧状态实体类
 * @author 李爽超 chao
 * @create 2019/09/12
 * @email lishuangchao@ss-cas.com
 **/
public class CutFlowState {

    private String code;
    private int state;

    public CutFlowState(String code, int state){
        this.code = code;
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
