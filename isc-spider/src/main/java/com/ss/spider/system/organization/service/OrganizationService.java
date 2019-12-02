package com.ss.spider.system.organization.service;

import com.ss.exception.ServiceException;
import com.ss.service.CWService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface OrganizationService<Organization> extends CWService<Organization> {

    String getNewOrgId() throws ServiceException;

    List<Organization> pages(Organization paramOrganization, int paramInt1, int paramInt2);

    List<Organization> list(Organization paramOrganization);

    List<Organization> gets(Map<String, Object> paramMap);

    List<Organization> gets(List<String> paramList);

    Organization get(String paramString);

    String save(Organization paramOrganization) throws ServiceException;

    int update(Organization paramOrganization) throws ServiceException;

    int discard(String paramString1, String paramString2) throws ServiceException;

    int discard(List<String> paramList, String paramString) throws ServiceException;

    int delete(String paramString) throws ServiceException;

    int delete(List<String> paramList) throws ServiceException;

    int delete(Organization paramOrganization) throws ServiceException;

    List<String> getLowerIds(String paramString);

    List<String> getNotExistIds(List<String> paramList);

    Organization getTopOrganization();

    Organization getTopOrgIgnoreIsLinkage();

    Organization getIgnoreStatus(String paramString);

    int insertOrg(MultipartFile file,String userId);

}
