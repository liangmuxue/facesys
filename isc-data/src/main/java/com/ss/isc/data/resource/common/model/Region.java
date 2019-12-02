package com.ss.isc.data.resource.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Region 区划信息
 * @author FrancisYs
 * @date 2019/8/25
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_ge_region_info")
public class Region implements Serializable {

    private static final long serialVersionUID = -7236167920581286333L;
    @Column(name = "REGION_ID")
    private String regionId;
    @Column(name = "REGION_CODE")
    private String regionCode;
    @Column(name = "PARENT_ID")
    private String parentId;
    @Column(name = "REGION_NAME")
    private String regionName;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "STATE")
    private Integer state;
    @Column(name = "CREATED_TIME")
    private BigDecimal createdTime;
    @Column(name = "gisCenter")
    private String gisCenter;
    @Column(name = "gisArea")
    private String gisArea;
    @Column(name = "gisType")
    private Integer gisType;
    @Column(name = "pinyin")
    private String pinyin;
    @Column(name = "updateTime")
    private Date updateTime;
    @Column(name = "thirdId")
    private String thirdId;
    @Column(name = "regionType")
    private Integer regionType;
    @Transient
    private String userIds;
    @Transient
    private String thirdName;

    @Transient
    private String regionTypeDesc;

    public String getThirdName() {
        return this.thirdName;
    }


    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }


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


    public String getParentId() {
        return this.parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public String getRegionName() {
        return this.regionName;
    }


    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getState() {
        return this.state;
    }


    public void setState(Integer state) {
        this.state = state;
    }


    public BigDecimal getCreatedTime() {
        return this.createdTime;
    }


    public void setCreatedTime(BigDecimal createdTime) {
        this.createdTime = createdTime;
    }


    public String getGisCenter() {
        return this.gisCenter;
    }


    public void setGisCenter(String gisCenter) {
        this.gisCenter = gisCenter;
    }


    public String getGisArea() {
        return this.gisArea;
    }


    public void setGisArea(String gisArea) {
        this.gisArea = gisArea;
    }


    public Integer getGisType() {
        return this.gisType;
    }


    public void setGisType(Integer gisType) {
        this.gisType = gisType;
    }


    public static long getSerialversionuid() {
        return -7236167920581286333L;
    }


    public String getPinyin() {
        return this.pinyin;
    }


    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getUserIds() {
        return this.userIds;
    }


    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }


    public String getThirdId() {
        return this.thirdId;
    }


    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }


    public Integer getRegionType() {
        return this.regionType;
    }


    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public String getRegionTypeDesc() {
        return regionTypeDesc;
    }

    public void setRegionTypeDesc(String regionTypeDesc) {
        this.regionTypeDesc = regionTypeDesc;
    }

}
