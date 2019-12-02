package com.ss.spider.interceptor.service;

import com.ss.spider.interceptor.cache.beans.CacheUserInfo;

import java.util.List;

public interface UserInfoCacheService {

    String getCacheToken(String paramString);

    void addCache(String paramString1, String paramString2, int paramInt);

    List<String> getCacheRoles(String paramString);

    void setCacheRoles(String paramString, List<String> paramList, int paramInt);

    List<String> getPersistenceRoles(String paramString);

    List<String> getCacheResources(String paramString);

    void setCacheResources(String paramString, List<String> paramList, int paramInt);

    List<String> getPersistenceResources(String paramString);

    String getCacheUserId(String paramString);

    CacheUserInfo getCacheUserInfo(String paramString);

    void setCacheUserInfo(String paramString, CacheUserInfo paramCacheUserInfo, int paramInt);

    CacheUserInfo getPersistenceUserInfo(String paramString);

}
