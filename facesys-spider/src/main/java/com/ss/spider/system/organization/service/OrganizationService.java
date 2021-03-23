package com.ss.spider.system.organization.service;

import com.ss.exception.ServiceException;
import com.ss.service.SsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 单位管理
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
public interface OrganizationService<Organization> extends SsService<Organization> {

    String getNewOrgId() throws ServiceException;

    /**
     * 查询单位列表
     *
     * @param paramOrganization
     * @return
     */
    List<Organization> list(Organization paramOrganization);

    /**
     * 查询单位分页列表
     *
     * @param paramOrganization
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    List<Organization> pages(Organization paramOrganization, int paramInt1, int paramInt2);

    /**
     * 查询单位树
     *
     * @return
     */
    List<Organization> treeData(Organization organization);

    /**
     * 查询单位及全部子节点集合
     *
     * @param orgId
     * @return
     */
    List<Organization> getCascadeChildren(String orgId);

    /**
     * 查询单位信息
     *
     * @param orgId
     * @return
     */
    Organization get(String orgId);

    /**
     * 新增单位信息
     *
     * @param paramOrganization
     * @return
     * @throws ServiceException
     */
    String save(Organization paramOrganization) throws ServiceException;

    /**
     * 修改单位信息
     *
     * @param paramOrganization
     * @return
     * @throws ServiceException
     */
    int update(Organization paramOrganization) throws ServiceException;

    /**
     * 批量逻辑删除
     *
     * @param paramList
     * @param paramString
     * @return
     * @throws ServiceException
     */
    int discard(List<String> paramList, String paramString) throws ServiceException;

    /**
     * 批量物理删除
     *
     * @param paramList
     * @return
     * @throws ServiceException
     */
    int delete(List<String> paramList) throws ServiceException;

    List<Organization> gets(Map<String, Object> paramMap);

    List<Organization> gets(List<String> paramList);

    int discard(String paramString1, String paramString2) throws ServiceException;

    int delete(String paramString) throws ServiceException;

    int delete(Organization paramOrganization) throws ServiceException;

    List<String> getLowerIds(String paramString);

    List<String> getNotExistIds(List<String> paramList);

    Organization getTopOrganization();

    Organization getTopOrgIgnoreIsLinkage();

    Organization getIgnoreStatus(String paramString);

    int insertOrg(MultipartFile file, String userId);

}