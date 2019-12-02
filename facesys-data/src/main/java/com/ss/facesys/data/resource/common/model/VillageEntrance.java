package com.ss.facesys.data.resource.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
 * VillageEntrance 小区出入口
 *
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_base_village_entrance")
public class VillageEntrance implements Serializable {

    private static final long serialVersionUID = -3222858320878617918L;
    @NotNull(message = "{villageEntrance.id.empty}", groups = {APIDeltGroup.class, APIEditGroup.class, APIGetsGroup.class})
    @Id
    private Integer id;
    @NotBlank(message = "{villageEntrance.villageCode.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String villageCode;
    @NotBlank(message = "{villageEntrance.entranceName.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String entranceName;
    private Short gisType;
    private Double lon;
    private Double lat;
    private Double alt;
    @NotBlank(message = "{villageEntrance.entrancePicUrl.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String entrancePicUrl;
    private Date createTime;
    private Date updateTime;
    private Integer state;
    @Transient
    private List<String> villages;
    @Transient
    private String villageName;
    @Transient
    private Integer pageNum;
    @Transient
    private Integer pageSize;
    @Transient
    private String userIds;
    @Transient
    private String sqlString;
    @Transient
    private String enumName;
    @Transient
    private String villageCodes;
    @Transient
    private String regionCode;
    @Transient
    private Map<String, String> sqlMap;

    public Map<String, String> getSqlMap() {
        return this.sqlMap;
    }


    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }


    public String getEnumName() {
        return this.enumName;
    }


    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }


    public String getVillageName() {
        return this.villageName;
    }


    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public List<String> getVillages() {
        return this.villages;
    }


    public void setVillages(List<String> villages) {
        this.villages = villages;
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


    public String getEntranceName() {
        return this.entranceName;
    }


    public void setEntranceName(String entranceName) {
        this.entranceName = entranceName;
    }


    public Short getGisType() {
        return this.gisType;
    }


    public void setGisType(Short gisType) {
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


    public String getEntrancePicUrl() {
        return this.entrancePicUrl;
    }


    public void setEntrancePicUrl(String entrancePicUrl) {
        this.entrancePicUrl = entrancePicUrl;
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


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }

}
