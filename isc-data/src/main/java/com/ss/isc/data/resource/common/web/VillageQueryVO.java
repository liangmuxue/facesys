package com.ss.isc.data.resource.common.web;

import com.ss.request.Pagination;
import com.ss.valide.APIPageGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

/**
 * @Description 单元查询对象
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public class VillageQueryVO extends Pagination {

    private String regionCodes;
    private String villageCodes;
    private Map<String, String> sqlMap;
    @NotBlank(message = "{userIds.empty}", groups = {APIPageGroup.class})
    private String userIds;
    private String villageName;

    public String getRegionCodes() {
        return regionCodes;
    }

    public void setRegionCodes(String regionCodes) {
        this.regionCodes = regionCodes;
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

    public Map<String, String> getSqlMap() {
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    @Override
    public String toString() {
        return "VillageQueryVO{" +
                "regionCodes='" + regionCodes + '\'' +
                ", villageCodes='" + villageCodes + '\'' +
                ", sqlMap=" + sqlMap +
                ", userIds='" + userIds + '\'' +
                ", villageName='" + villageName + '\'' +
                '}';
    }

}
