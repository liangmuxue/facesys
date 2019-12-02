package com.ss.facesys.data.archives.client;

import com.ss.facesys.data.collect.common.web.SubscribeVO;

/**
* VIID 订阅
* @author chao
* @create 2019/10/28
* @email lishuangchao@ss-cas.com
**/
public interface ISubscribeService {
    /**
     * 新增订阅
     * @param subscribeVO
     * @return
     */
    String add(SubscribeVO subscribeVO);

    /**
     * 取消订阅
     * @param subscribeVO
     * @return
     */
    String delete(SubscribeVO subscribeVO);

    /**
     * 修改订阅
     * @param subscribeVO
     * @return
     */
    String update(SubscribeVO subscribeVO);
}
