package com.ss.spider.system.resource.service;

import com.ss.exception.ServiceException;
import com.ss.service.SsService;

import java.util.List;
import java.util.Map;

public interface ResourceService<Resource> extends SsService<Resource> {

    List<Resource> gets(Map<String, Object> paramMap);

    List<Resource> gets(List<String> paramList);

    Resource get(String paramString);

    List<Resource> list(Resource paramResource);

    /**
     * 查询用户资源列表
     * @param paramMap
     * @return
     */
    List<Resource> query(Map<String, Object> paramMap);

    List<Resource> pages(Resource paramResource, int paramInt1, int paramInt2);

    String save(Resource paramResource) throws ServiceException;

    int update(Resource paramResource) throws ServiceException;

    int discard(List<String> paramList, String paramString) throws ServiceException;

    int discard(String paramString1, String paramString2) throws ServiceException;

    int delete(List<String> paramList) throws ServiceException;

    int delete(String paramString) throws ServiceException;

    int delete(Resource paramResource) throws ServiceException;

}
