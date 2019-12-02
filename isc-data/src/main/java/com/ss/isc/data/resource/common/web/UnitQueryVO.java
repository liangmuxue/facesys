package com.ss.isc.data.resource.common.web;

import com.ss.request.Pagination;

/**
 * @Description 单元查询对象
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public class UnitQueryVO extends Pagination {

    private String villageCode;
    private String villageCodes;
    private String buildingNo;
    private String unitName;
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

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String toString() {
        return "UnitQueryVO{" +
                "villageCode='" + villageCode + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", buildingNo='" + buildingNo + '\'' +
                ", unitName='" + unitName + '\'' +
                ", userIds='" + userIds + '\'' +
                ", sqlString='" + sqlString + '\'' +
                '}';
    }

}
