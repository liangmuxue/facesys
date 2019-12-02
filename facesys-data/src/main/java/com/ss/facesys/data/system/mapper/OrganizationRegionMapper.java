package com.ss.facesys.data.system.mapper;

import com.ss.facesys.data.system.common.dto.UserPermission;
import com.ss.facesys.data.system.common.model.OrganizationRegion;
import com.ss.mapper.SsMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * OrganizationRegionMapper：机构关联权限小区Mapper
 * @author FrancisYs
 * @date 2019/8/14
 * @email yaoshuai@ss-cas.com
 */
@Mapper
public interface OrganizationRegionMapper extends SsMapper<OrganizationRegion> {

    /**
     * 查询用户机构权限下的小区
     * @param paramOrganizationRegion
     * @return
     */
    List<UserPermission> findUserPermission(OrganizationRegion paramOrganizationRegion);

    List<OrganizationRegion> seleall(@Param("orgId") String orgId);

    int insert(OrganizationRegion organizationRegion);

    int update(OrganizationRegion organizationRegion);
}
