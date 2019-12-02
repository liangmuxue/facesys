package com.ss.facesys.data.collect.common.web;

import com.ss.request.Pagination;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.Map;

public class LeavePersonQuery extends Pagination {
    private static final long serialVersionUID = -7835258488812728972L;
    private String peopleName;
    private String credentialNo;
    private String villageCode;
    private String residenceAddress;
    public Integer state;
    private Date beginTime;
    private Date endTime;
    private Integer leaveDays;
    private String userIds;
    private Map<String, String> sqlMap;
    private String condition;

    public String getPeopleName() {
        return this.peopleName;
    }


    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }


    public String getCredentialNo() {
        return this.credentialNo;
    }


    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public Date getBeginTime() {
        return this.beginTime;
    }


    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }


    public Date getEndTime() {
        return this.endTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getResidenceAddress() {
        return this.residenceAddress;
    }


    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }


    public Integer getLeaveDays() {
        return this.leaveDays;
    }


    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
    }


    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public Map<String, String> getSqlMap() {
        if (this.sqlMap == null) {
            this.sqlMap = Maps.newHashMap();
        }
        return this.sqlMap;
    }


    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
