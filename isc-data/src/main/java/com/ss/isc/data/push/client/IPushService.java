package com.ss.isc.data.push.client;

/**
 * 消息推送
 *
 * @author FrancisYs
 * @date 2019/11/11
 * @email yaoshuai@ss-cas.com
 */
public interface IPushService {

    /**
     * 首页推送
     * @param paramString
     */
    void pushHome(String paramString);

    void pushHomePage(String paramString);

}
