package com.ss.spider.interceptor.constant;

public interface InterceptorConstant {

    public static final String DEFAULT_ENCODE = "UTF-8";

    public static final String ACCESS_USERNAME_FIELD = "sub";

    public static final String REQUEST_HEADER_AUTH = "X-Authorization";

    public static final String JWT_TOKEN_BEARER = "Bearer ";

    public static final String RESPONSE_HEADER_CONTENT_TYPE_NAME = "Content-type";

    public static final String RESPONSE_HEADER_CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";

    public static final String SUPER_MANAGE_ROLE_ID = "1";

    public static final String CACHE_TONKEN_PREFIX = "TOKEN_";

    public static final String CACHE_REFRESH_TOKEN_PREFIX = "REFRESH_TOKEN_";

    public static final String CACHE_ROLE_PREFIX = "ROLE_";

    public static final String CACHE_RESOURCE_PREFIX = "RESOURCE_";

    public static final String CACHE_USERID_PREFIX = "USERNAME_";

    public static final String CACHE_USERINFO_PREFIX = "USERINFO_";

    public static final String DEFAULT_SET_USERID_PARAM = "userId";

    public static final String DEFAULT_SET_USERID_VALUE = "1000";

    public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/token";

}
