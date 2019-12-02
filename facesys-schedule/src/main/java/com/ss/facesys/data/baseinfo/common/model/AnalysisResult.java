package com.ss.facesys.data.baseinfo.common.model;

import java.util.Date;


public class AnalysisResult {

    private String resultId;
    private String refTaskId;
    private Long count;
    private String captureId;
    private Date captureTime;
    private String captureUrl;
    private String captureUrlFull;

    public String getResultId() {
        return this.resultId;
    }


    public void setResultId(String resultId) {
        this.resultId = resultId;
    }


    public String getRefTaskId() {
        return this.refTaskId;
    }


    public void setRefTaskId(String refTaskId) {
        this.refTaskId = refTaskId;
    }


    public Long getCount() {
        return this.count;
    }


    public void setCount(Long count) {
        this.count = count;
    }


    public String getCaptureId() {
        return this.captureId;
    }


    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }


    public Date getCaptureTime() {
        return this.captureTime;
    }


    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }


    public String getCaptureUrl() {
        return this.captureUrl;
    }


    public void setCaptureUrl(String captureUrl) {
        this.captureUrl = captureUrl;
    }


    public String getCaptureUrlFull() {
        return this.captureUrlFull;
    }


    public void setCaptureUrlFull(String captureUrlFull) {
        this.captureUrlFull = captureUrlFull;
    }

}
