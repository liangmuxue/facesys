package com.ss.facesys.data.resource.common.model;

import java.io.Serializable;


/**
 * Region 数据看板统计请求参数
 * @author maomaohcong
 * @date 2019/11/7
 */
public class StParam implements Serializable {
    private String villageCodes;
    private String userIds;
    private String periodType;

    public String getVillageCodes() {
        return villageCodes;
    }

    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }
}
