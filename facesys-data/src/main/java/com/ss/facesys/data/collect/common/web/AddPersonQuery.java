package com.ss.facesys.data.collect.common.web;

import com.ss.facesys.data.baseinfo.common.web.BaseQueryEntity;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.Map;

public class AddPersonQuery extends BaseQueryEntity {

    private static final long serialVersionUID = 4404859728500487468L;
    private String peopleName;
    private String villageName;
    private Integer state;
    private Date beginTime;
    private Date endTime;
    private Integer days;
    private String residenceAddress;
    private String userIds;
    private Map<String, String> sqlMap;
    private String condition;

    public String getPeopleName() {
        return this.peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getVillageName() {
        return this.villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
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

    public Integer getDays() {
        return this.days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getResidenceAddress() {
        return this.residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
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
