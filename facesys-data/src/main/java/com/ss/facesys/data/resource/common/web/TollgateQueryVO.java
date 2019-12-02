package com.ss.facesys.data.resource.common.web;

import com.ss.request.Pagination;

/**
 * @Description 车辆卡口查询对象
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public class TollgateQueryVO extends Pagination {

    private String villageCode;
    private String villageCodes;
    private String tollgateName;
    private Integer tollgateType;
    private Integer status;
    private String userIds;
    private String sqlString;

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

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

    public String getSqlString() {
        return sqlString;
    }

    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getTollgateName() {
        return tollgateName;
    }

    public void setTollgateName(String tollgateName) {
        this.tollgateName = tollgateName;
    }

    public Integer getTollgateType() {
        return tollgateType;
    }

    public void setTollgateType(Integer tollgateType) {
        this.tollgateType = tollgateType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TollgateQueryVO{" +
                "villageCode='" + villageCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", tollgateName='" + tollgateName + '\'' +
                ", tollgateType=" + tollgateType +
                ", status=" + status +
                ", userIds='" + userIds + '\'' +
                ", sqlString='" + sqlString + '\'' +
                '}';
    }

}
