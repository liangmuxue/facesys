package com.ss.spider.log.bean.dto;


public class LogInsertDTO {

    private String userId;
    private String orgId;
    private String phoneNumber;
    private String workCode;
    private String name;
    private Long operTime;
    private String moduleCode;
    private String type;
    private Short logType;
    private String ip;
    private String url;
    private Integer timeConsum;
    private Short isSuccess;
    private String desc;
    private String inPara;
    private String outPara;

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
