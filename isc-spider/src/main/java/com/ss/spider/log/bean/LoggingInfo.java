package com.ss.spider.log.bean;

import com.ss.annotation.OpLog;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class LoggingInfo {

    private ReqestInfo reqestInfo;
    private OpLog opLogInfo;
    private Object[] args;
    private Object result;
    private Long operTime;
    private Integer timeConsum;
    private Throwable ex;

    public LoggingInfo() {
    }

    public LoggingInfo(ReqestInfo reqestInfo, OpLog opLogInfo, Object[] args, Object result, Long operTime, Integer timeConsum, Throwable ex) {
        this.reqestInfo = reqestInfo;
        this.args = args;
        this.result = result;
        this.ex = ex;
        this.opLogInfo = opLogInfo;
        this.operTime = operTime;
        this.timeConsum = timeConsum;
    }


    public OpLog getOpLogInfo() {
        return this.opLogInfo;
    }


    public void setOpLogInfo(OpLog opLogInfo) {
        this.opLogInfo = opLogInfo;
    }


    public ReqestInfo getReqestInfo() {
        return this.reqestInfo;
    }


    public void setReqestInfo(ReqestInfo reqestInfo) {
        this.reqestInfo = reqestInfo;
    }


    public Object[] getArgs() {
        return this.args;
    }


    public void setArgs(Object[] args) {
        this.args = args;
    }


    public Object getResult() {
        return this.result;
    }


    public void setResult(Object result) {
        this.result = result;
    }


    public Throwable getEx() {
        return this.ex;
    }


    public void setEx(Throwable ex) {
        this.ex = ex;
    }


    public Long getOperTime() {
        return this.operTime;
    }


    public void setOperTime(Long operTime) {
        this.operTime = operTime;
    }


    public Integer getTimeConsum() {
        return this.timeConsum;
    }


    public void setTimeConsum(Integer timeConsum) {
        this.timeConsum = timeConsum;
    }


    public String toString() {
        return (new ToStringBuilder(this))
                .append("reqestInfo", this.reqestInfo)
                .append("opLogInfo", this.opLogInfo)
                .append("args", this.args)
                .append("result", this.result)
                .append("operTime", this.operTime)
                .append("timeConsum", this.timeConsum)
                .toString();
    }

}
