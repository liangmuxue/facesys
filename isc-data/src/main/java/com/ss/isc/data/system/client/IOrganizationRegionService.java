package com.ss.isc.data.system.client;

import com.ss.isc.data.system.common.dto.UserPermission;
import com.ss.isc.data.system.common.model.OrganizationRegion;
import com.ss.isc.data.system.common.model.Region;

import java.util.List;

/**
 * IOrganizationRegionService：机构关联区划接口服务
 * @author FrancisYs
 * @date 2019/8/14
 * @email yaoshuai@ss-cas.com
 */
public interface IOrganizationRegionService {

    List<OrganizationRegion> getList(OrganizationRegion paramOrganizationRegion);

    List<Region> getTree();

    void update(OrganizationRegion paramOrganizationRegion);

    List<UserPermission> findUserPermission();

    /**
     * 查询当前用户机构权限
     * @param paramString
     * @return
     */
    UserPermission findCurrentPersion(String paramString);

    String dataScopeFilter(String paramString);

    List<String> findPermissionByVillageCode(String paramString);

    String cameraIdsString(String paramString);

}
