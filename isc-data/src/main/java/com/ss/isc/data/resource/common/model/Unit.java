package com.ss.isc.data.resource.common.model;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Unit 单元
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_unit")
public class Unit {

    @Id
    @NotNull(message = "{unit.id.empty}", groups = {APIDeltGroup.class, APIEditGroup.class, APIGetsGroup.class})
    private String id;
    @NotBlank(message = "{unit.villageCode.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String villageCode;
    @NotBlank(message = "{unit.buildingNo.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String buildingNo;
    private String unitNo;
    @NotBlank(message = "{unit.unitName.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String unitName;
    @NotNull(message = "{unit.floorNum.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private Integer floorNum;
    private Integer houseNum;
    private Integer gisType;
    private String description;
    private Double lon;
    private Double lat;
    private Double alt;
    private Date createTime;
    private Date updateTime;
    @Transient
    private Integer pageNum;
    @Transient
    private Integer pageSize;
    @Transient
    private String buildingNoAndUnitNo;
    @Transient
    private String buildingAndUnitName;
    @Transient
    private String villageName;
    @Transient
    private String buildingName;
    @Transient
    private String userIds;
    @Transient
    private String sqlString;
    @Transient
    private String villageCodes;
    @Transient
    private String regionCode;
    private Integer isDelete;
    private String thirdId;

    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
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


    public String getUnitNo() {
        return this.unitNo;
    }


    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }


    public String getUnitName() {
        return this.unitName;
    }


    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }


    public Integer getFloorNum() {
        return this.floorNum;
    }


    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }


    public Integer getHouseNum() {
        return this.houseNum;
    }


    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }


    public String getDescription() {
        return this.description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getGisType() {
        return this.gisType;
    }


    public void setGisType(Integer gisType) {
        this.gisType = gisType;
    }


    public Double getLon() {
        return this.lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    public Double getLat() {
        return this.lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    public Double getAlt() {
        return this.alt;
    }


    public void setAlt(Double alt) {
        this.alt = alt;
    }


    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Integer getPageNum() {
        return this.pageNum;
    }


    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


    public Integer getPageSize() {
        return this.pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getBuildingNoAndUnitNo() {
        return this.buildingNoAndUnitNo;
    }


    public void setBuildingNoAndUnitNo(String buildingNoAndUnitNo) {
        this.buildingNoAndUnitNo = buildingNoAndUnitNo;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public String getBuildingName() {
        return this.buildingName;
    }


    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }


    public String getBuildingAndUnitName() {
        return this.buildingAndUnitName;
    }


    public void setBuildingAndUnitName(String buildingAndUnitName) {
        this.buildingAndUnitName = buildingAndUnitName;
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


    public String getVillageCodes() {
        return this.villageCodes;
    }


    public void setVillageCodes(String villageCodes) {
        this.villageCodes = villageCodes;
    }


    public String getRegionCode() {
        return this.regionCode;
    }


    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    public Integer getIsDelete() {
        return this.isDelete;
    }


    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }


    public String getThirdId() {
        return this.thirdId;
    }


    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

}
