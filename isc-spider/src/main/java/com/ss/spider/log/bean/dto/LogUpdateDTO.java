package com.ss.spider.log.bean.dto;


public class LogUpdateDTO
        extends LogInsertDTO {

    private String appLog;

    public String getAppLog() {
        return this.appLog;
    }


    public void setAppLog(String appLog) {
        this.appLog = appLog;
    }

}
