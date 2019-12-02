package com.ss.facesys.data.resource.common.web;

import com.ss.request.Pagination;

/**
 * @Description 小区出入口查询对象
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public class VillageEntranceQueryVO extends Pagination {

    private String villageCode;
    private String villageCodes;
    private String entranceName;
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

    public String getEntranceName() {
        return entranceName;
    }

    public void setEntranceName(String entranceName) {
        this.entranceName = entranceName;
    }

    @Override
    public String toString() {
        return "VillageEntranceQueryVO{" +
                "villageCode='" + villageCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", entranceName='" + entranceName + '\'' +
                ", userIds='" + userIds + '\'' +
                ", sqlString='" + sqlString + '\'' +
                '}';
    }

}
