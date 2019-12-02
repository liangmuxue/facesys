package com.ss.facesys.data.resource.common.web;

import com.ss.request.Pagination;
import com.ss.valide.APIListGroup;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Region 区划信息
 * @author FrancisYs
 * @date 2019/8/25
 * @email yaoshuai@ss-cas.com
 */
public class RegionVO extends Pagination {

    private String regionId;
    private String regionCode;
    @NotBlank(message = "{region.parentId.empty}", groups = {APIListGroup.class})
    private String parentId;
    private String regionName;
    private Integer regionType;
    private String userIds;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

}
