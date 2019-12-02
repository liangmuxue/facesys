package com.ss.spider.system.organization.mapper;

import com.ss.mapper.CWMapper;
import com.ss.spider.system.organization.model.Organization;

import java.util.List;
import java.util.Map;

import com.ss.spider.system.organization.model.OrganizationExp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrganizationMapper extends CWMapper<Organization> {

    List<Organization> pages(Organization paramOrganization);

    List<Organization> list(Organization paramOrganization);

    List<Organization> getDept(@Param("orgId") String orgId,@Param("status") int status);

    List<Organization> gets(Map<String, Object> paramMap);

    List<Organization> selectNameAndCode(Organization paramOrganization);

    Organization selectDeparth(@Param("parentId") String parentId,@Param("status") int status);

    int save(Organization paramOrganization);

    int update(Organization paramOrganization);

    int discard(Map<String, Object> paramMap);

    int remove(Map<String, Object> paramMap);

    List<Organization> getTopOrg();

    int insertOrg(List<OrganizationExp> organizationExpList);

    void deleteTreeData();

}
