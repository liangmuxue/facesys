package com.ss.spider.system.application.service;

import com.ss.exception.ServiceException;
import com.ss.service.CWService;

import java.util.List;
import java.util.Map;

public interface AppSystemService<AppSystem> extends CWService<AppSystem> {

    List<AppSystem> pages(AppSystem paramAppSystem, int paramInt1, int paramInt2);

    List<AppSystem> list(AppSystem paramAppSystem);

    List<AppSystem> gets(Map<String, Object> paramMap);

    List<AppSystem> gets(List<String> paramList);

    AppSystem get(String paramString);

    int remove(String paramString) throws ServiceException;

    int remove(List<String> paramList) throws ServiceException;

    String save(AppSystem paramAppSystem) throws ServiceException;

    int update(AppSystem paramAppSystem) throws ServiceException;

}
