package com.ss.facesys.data.trail.service;

import com.ss.facesys.data.push.client.IPushService;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.netty.CaptureMyChannelHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息推送
 *
 * @author FrancisYs
 * @date 2019/11/11
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class PushServiceImpl implements IPushService {

    /**
     * 首页推送
     * @param msg
     */
    @Override
    public void pushHome(String msg) {
        CaptureMyChannelHandler.send(msg, Enums.NettyUri.URI_HOME.getCode());
    }


    @Override
    public void pushHomePage(String msg) {
        CaptureMyChannelHandler.send(msg, Enums.NettyUri.URI_HOME_PAGE.getCode());
    }

}
