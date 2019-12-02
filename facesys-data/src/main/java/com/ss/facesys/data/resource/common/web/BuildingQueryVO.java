package com.ss.facesys.data.resource.common.web;

import com.ss.request.Pagination;

/**
 * @Description 楼栋查询对象
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public class BuildingQueryVO extends Pagination {

    private String villageCode;
    private String villageCodes;
    private String buildingName;
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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    @Override
    public String toString() {
        return "BuildingQueryVO{" +
                "villageCode='" + villageCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", userIds='" + userIds + '\'' +
                ", sqlString='" + sqlString + '\'' +
                '}';
    }

}
