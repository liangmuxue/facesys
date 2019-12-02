package com.ss.isc.data.resource.common.model;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Building 楼栋
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_building")
public class Building implements Serializable {

    private static final long serialVersionUID = -681817230815056082L;
    @NotNull(message = "{building.id.empty}", groups = {APIDeltGroup.class,APIGetsGroup.class,APIEditGroup.class})
    @Id
    private Integer id;
    @NotNull(message = "{building.villageCode.empty}", groups = {APIAddGroup.class,APIEditGroup.class})
    private String villageCode;
    private String buildingNo;
    @NotBlank(message = "{building.buildingName.empty}", groups = {APIAddGroup.class,APIEditGroup.class})
    private String buildingName;
    @NotNull(message = "{building.floorNum.empty}", groups = {APIAddGroup.class,APIEditGroup.class})
    private Integer floorNum;
    private Integer houseNum;
    private Short gisType;
    private String description;
    private Double lon;
    private Double lat;
    private Double alt;
    @Transient
    private List<Unit> units;
    private Date createTime;
    private Date updateTime;
    private int state;
    @Transient
    private Integer pageNum;
    @Transient
    private Integer pageSize;
    @Transient
    private String villageName;
    @Transient
    private String userIds;
    @Transient
    private String sqlString;
    @Transient
    private String villageCodes;
    @Transient
    private String regionCode;
    private Integer isDelete;
    private String thirdVillageId;
    private String thirdId;
    private String thirdStreetCode;

    public List<Unit> getUnits() {
        return this.units;
    }


    public void setUnits(List<Unit> units) {
        this.units = units;
    }


    public Integer getId() {
        return this.id;
    }


    public void setId(Integer id) {
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


    public String getBuildingName() {
        return this.buildingName;
    }


    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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


    public Short getGisType() {
        return this.gisType;
    }


    public void setGisType(Short gisType) {
        this.gisType = gisType;
    }


    public String getDescription() {
        return this.description;
    }


    public void setDescription(String description) {
        this.description = description;
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


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public String getThirdStreetCode() {
        return this.thirdStreetCode;
    }


    public void setThirdStreetCode(String thirdStreetCode) {
        this.thirdStreetCode = thirdStreetCode;
    }


    public int getState() {
        return this.state;
    }


    public void setState(int state) {
        this.state = state;
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


    public String getThirdVillageId() {
        return this.thirdVillageId;
    }


    public void setThirdVillageId(String thirdVillageId) {
        this.thirdVillageId = thirdVillageId;
    }


    public String getThirdId() {
        return this.thirdId;
    }


    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }


    public Integer getIsDelete() {
        return this.isDelete;
    }


    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}
