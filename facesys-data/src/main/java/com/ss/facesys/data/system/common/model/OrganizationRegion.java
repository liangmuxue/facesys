package com.ss.facesys.data.system.common.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrganizationRegion 组织区域关系
 * @author FrancisYs
 * @date 2019/8/25
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_relation_organization_region")
public class OrganizationRegion implements Serializable {

    private static final long serialVersionUID = 2086374451711311651L;
    @Id
    @Column(name = "ORG_ID")
    private String orgId;
    @Column(name = "REGION_ID")
    private String regionId;

    public String getOrgId() {
        return this.orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getRegionId() {
        return this.regionId;
    }
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

}
