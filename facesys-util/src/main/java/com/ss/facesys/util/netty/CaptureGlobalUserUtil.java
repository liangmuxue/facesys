package com.ss.facesys.util.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * channel初始化
 *
 * @author FrancisYs
 * @date 2019/11/11
 * @email yaoshuai@ss-cas.com
 */
class CaptureGlobalUserUtil {

    static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
