package com.ss.isc.schedule.viid.job;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ss.isc.data.viid.common.dict.KeepaliveStatus;
import com.ss.isc.data.viid.common.dict.RegisterStatus;
import com.ss.isc.data.viid.common.dto.ViidRegisterSendDTO;
import com.ss.isc.data.viid.common.util.ViidRegisterUtil;
import com.ss.isc.util.SpringUtil;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.spider.viid.config.ViidProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 视图库注册保活任务
 *
 * @author FrancisYs
 * @date 2019/10/25
 * @email yaoshuai@ss-cas.com
 */
public class ViidKeepConnectJob implements SimpleJob {

    private static final Logger logger = LoggerFactory.getLogger(ViidKeepConnectJob.class);

    private JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private ViidProperties viidProperties = SpringUtil.getBean(ViidProperties.class);

    private static final String REDIS_KEY_VIID_CONNECT_STATUS = "REDIS_KEY_VIID_CONNECT_STATUS";
    private static final String REDIS_KEY_VIID_REGISTER_STATUS = "REDIS_KEY_VIID_REGISTER_STATUS";


    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info("<-----开始注册保活任务----->");
        if (!registed()) {
            register();
            return;
        }
        keepAlive();
    }

    /**
     * 注册状态判断
     * @return 是否注册
     */
    private boolean registed() {
        return jedisUtil.hasKey(REDIS_KEY_VIID_REGISTER_STATUS) && RegisterStatus.REGISTERED.getCode().equals(Short.valueOf(String.valueOf(jedisUtil.get(REDIS_KEY_VIID_REGISTER_STATUS))));
    }

    /**
     * 重置视图库连接状态
     */
    private void clearCache() {
        jedisUtil.del(REDIS_KEY_VIID_REGISTER_STATUS);
        jedisUtil.del(REDIS_KEY_VIID_CONNECT_STATUS);
    }

    /**
     * 获取视图库通信参数
     * @return
     */
    private ViidRegisterSendDTO getRegisterSendDTO() {
        ViidRegisterSendDTO sendDTO = new ViidRegisterSendDTO();
        sendDTO.setDeviceId(viidProperties.getDeviceId());
        sendDTO.setHttpProtocol(viidProperties.getHttpProtocol());
        sendDTO.setIp(viidProperties.getIp());
        sendDTO.setPort(viidProperties.getPort());
        sendDTO.setSendUserName(viidProperties.getSendUserName());
        sendDTO.setSendPassWord(viidProperties.getSendPassWord());
        logger.info("视图库通信参数：{}", JSON.toJSONString(sendDTO));
        return sendDTO;
    }

    /**
     * 注册
     */
    private void register() {
        logger.info("<-----开始视图库注册----->");
        clearCache();
        try {
            if (ViidRegisterUtil.sendRegister(getRegisterSendDTO())) {
                jedisUtil.set(REDIS_KEY_VIID_REGISTER_STATUS, RegisterStatus.REGISTERED.getCode());
                jedisUtil.set(REDIS_KEY_VIID_CONNECT_STATUS, KeepaliveStatus.KEEPALIVED.getCode());
                logger.info("<-----视图库注册成功----->");
            }
        } catch (Exception e) {
            logger.error("视图库注册失败，发生异常：{}", e.getMessage());
        }
    }

    /**
     * 保活
     */
    private void keepAlive() {
        logger.info("<-----开始视图库保活----->");
        jedisUtil.set(REDIS_KEY_VIID_CONNECT_STATUS, KeepaliveStatus.UNKEEPALIVE.getCode());
        try {
            if (ViidRegisterUtil.sendKeepAlive(getRegisterSendDTO())) {
                jedisUtil.set(REDIS_KEY_VIID_CONNECT_STATUS, KeepaliveStatus.KEEPALIVED.getCode());
                logger.info("<-----视图库保活成功----->");
            } else {
                logger.info("<-----视图库保活失败，将重置REDIS视图库连接状态----->");
                clearCache();
            }
        } catch (Exception e) {
            logger.error("视图库保活失败，发生异常：{}", e.getMessage());
            clearCache();
        }
    }

}
