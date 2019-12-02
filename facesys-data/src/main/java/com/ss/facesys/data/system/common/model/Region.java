package com.ss.facesys.data.system.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
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

    private static final long serialVersionUID = -2634908007308073567L;
    @Id
    @Column(name = "REGION_ID")
    private String regionId;
    @Column(name = "REGION_CODE")
    private String regionCode;
    @Column(name = "REGION_NAME")
    private String regionName;
    @Column(name = "PARENT_ID")
    private String parentId;
    @Column(name = "STATE")
    private Double state;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "CREATED_TIME")
    private Double createTime;
    @Transient
    private List<Region> children = new ArrayList();

    @Transient
    private String orgId;

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


    public Double getState() {
        return this.state;
    }


    public void setState(Double state) {
        this.state = state;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Double getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Double createTime) {
        this.createTime = createTime;
    }


    public List<Region> getChildren() {
        return this.children;
    }


    public void setChildren(List<Region> children) {
        this.children = children;
    }


    public String getOrgId() {
        return this.orgId;
    }


    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}
