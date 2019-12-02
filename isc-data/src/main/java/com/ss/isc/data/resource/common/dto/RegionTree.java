package com.ss.isc.data.resource.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * RegionTree
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
public class RegionTree implements Serializable {

    private static final long serialVersionUID = 7409133586336222804L;
    private String regionId;
    private String regionCode;
    private String regionName;
    private String parentId;
    private Integer state;
    private Long createdTime;
    private Double lon;
    private Double lat;
    private List<RegionTree> children;
    private String gisCenter;
    private Integer gisType;
    private String gisArea;
    private String remark;
    private String pinyin;
    private Integer regionType;
    private String updateTime;

    public String getRegionId() {
        return this.regionId;
    }


    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }


    public String getRegionCode() {
        return this.regionCode;
    }


    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    public String getRegionName() {
        return this.regionName;
    }


    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }


    public String getParentId() {
        return this.parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public Long getCreatedTime() {
        return this.createdTime;
    }


    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
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


    public List<RegionTree> getChildren() {
        return this.children;
    }


    public void setChildren(List<RegionTree> children) {
        this.children = children;
    }


    public String getGisCenter() {
        return this.gisCenter;
    }


    public void setGisCenter(String gisCenter) {
        this.gisCenter = gisCenter;
    }


    public Integer getGisType() {
        return this.gisType;
    }


    public void setGisType(Integer gisType) {
        this.gisType = gisType;
    }


    public String getGisArea() {
        return this.gisArea;
    }


    public void setGisArea(String gisArea) {
        this.gisArea = gisArea;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getPinyin() {
        return this.pinyin;
    }


    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }


    public Integer getRegionType() {
        return this.regionType;
    }


    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }


    public String getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
