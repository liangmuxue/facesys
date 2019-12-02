package com.ss.facesys.data.archives.common.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
* 房屋关联人员实体类
* @author chao
* @create 2019/9/23
* @email lishuangchao@ss-cas.com
**/
public class PersonHousePeopleDTO {
    @NotBlank(message = "{house.id.empty}", groups = {com.ss.valide.APIGetsGroup.class})
    private String houseId;
    private String villageCode;
    private String buildingNo;
    private String houseNo;
    private String userIds;
    private String sqlString;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getVillageCode() {
        return this.villageCode;
    }


    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getBuildingNo() {
        return this.buildingNo;
    }


    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }


    public String getHouseNo() {
        return this.houseNo;
    }


    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }


    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public String getSqlString() {
        return this.sqlString;
    }


    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }


    public String toString() {
        return "PersonHousePeopleDTO [villageCode=" + this.villageCode + ", buildingNo=" + this.buildingNo + ", houseNo=" + this.houseNo + ", userIds=" + this.userIds + ", sqlString=" + this.sqlString + "]";
    }
}
