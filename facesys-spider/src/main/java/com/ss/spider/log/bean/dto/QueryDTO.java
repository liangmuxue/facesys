package com.ss.spider.log.bean.dto;

import java.util.List;


public class QueryDTO {

    private String keyword;
    private String userId;
    private List<String> userIds;
    private String ip;
    private String url;
    private String moduleCode;
    private String phoneNumber;
    private String orgId;
    private Long operTimeB;
    private Long operTimeE;
    private String name;
    private String type;
    private Short logType;
    private String workCode;
    private Short isSuccess;
    private String desc;
    private String inPara;
    private String outPara;

    public String getKeyword() {
        return this.keyword;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public List<String> getUserIds() {
        return this.userIds;
    }


    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }


    public String getIp() {
        return this.ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getUrl() {
        return this.url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getModuleCode() {
        return this.moduleCode;
    }


    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }


    public Long getOperTimeB() {
        return this.operTimeB;
    }


    public void setOperTimeB(Long operTimeB) {
        this.operTimeB = operTimeB;
    }


    public Long getOperTimeE() {
        return this.operTimeE;
    }


    public void setOperTimeE(Long operTimeE) {
        this.operTimeE = operTimeE;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
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


    public String getWorkCode() {
        return this.workCode;
    }


    public void setWorkCode(String workCode) {
        this.workCode = workCode;
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


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getOrgId() {
        return this.orgId;
    }


    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public Short getIsSuccess() {
        return this.isSuccess;
    }


    public void setIsSuccess(Short isSuccess) {
        this.isSuccess = isSuccess;
    }

}
