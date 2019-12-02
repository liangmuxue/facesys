package com.ss.spider.security;

import com.ss.spider.security.entity.UserAuthEntity;

public interface UserTokenService {

    UserAuthEntity getUserByLoginName(String paramString);

    boolean isAuthSuccess(String paramString1, String paramString2);

    void cacheUserToken(String paramString1, String paramString2, Integer paramInteger);

    String getUserToken(String paramString);

    void cacheUserId(String paramString1, String paramString2, Integer paramInteger);

    void cleanCacheByKey(String paramString);

}
