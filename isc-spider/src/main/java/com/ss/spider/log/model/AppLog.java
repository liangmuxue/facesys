package com.ss.spider.log.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "cw_app_log")
public class AppLog {

    @Id
    @Column(name = "APP_LOG")
    private String appLog;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "ORG_ID")
    private String orgId;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "WORK_CODE")
    private String workCode;
    @Column(name = "NAME")
    private String name;
    @Column(name = "OPER_TIME")
    private Long operTime;
    @Column(name = "MODULE_CODE")
    private String moduleCode;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "LOG_TYPE")
    private Short logType;
    @Column(name = "IP")
    private String ip;
    @Column(name = "URL")
    private String url;
    @Column(name = "TIME_CONSUM")
    private Integer timeConsum;
    @Column(name = "IS_SUCCESS")
    private Short isSuccess;
    @Column(name = "OP_DESC")
    private String desc;
    @Column(name = "IN_PARA")
    private String inPara;
    @Column(name = "OUT_PARA")
    private String outPara;

    public String getAppLog() {
        return this.appLog;
    }


    public void setAppLog(String appLog) {
        this.appLog = appLog;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getWorkCode() {
        return this.workCode;
    }


    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Long getOperTime() {
        return this.operTime;
    }


    public void setOperTime(Long operTime) {
        this.operTime = operTime;
    }


    public String getModuleCode() {
        return this.moduleCode;
    }


    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }


    public String getType() {
        return this.type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Short getLogType() {
        return this.logType;
    }


    public void setLogType(Short logType) {
        this.logType = logType;
    }


    public String getUrl() {
        return this.url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public Integer getTimeConsum() {
        return this.timeConsum;
    }


    public void setTimeConsum(Integer timeConsum) {
        this.timeConsum = timeConsum;
    }


    public Short getIsSuccess() {
        return this.isSuccess;
    }


    public void setIsSuccess(Short isSuccess) {
        this.isSuccess = isSuccess;
    }


    public String getDesc() {
        return this.desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getInPara() {
        return this.inPara;
    }


    public void setInPara(String inPara) {
        this.inPara = inPara;
    }


    public String getOutPara() {
        return this.outPara;
    }


    public void setOutPara(String outPara) {
        this.outPara = outPara;
    }


    public String getIp() {
        return this.ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getOrgId() {
        return this.orgId;
    }


    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
