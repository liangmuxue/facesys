package com.ss.facesys.util.netty;

import com.alibaba.fastjson.JSONObject;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.jedis.JedisUtil;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.*;

/**
 * webSocketServer
 *
 * @author chao
 * @create 2019/12/20
 * @email lishuangchao@ss-cas.com
 **/
@ServerEndpoint(path = "/faceServer", maxFramePayloadLength = "${ws.maxFramePayloadLength}", port = "${ws.port}")
@Component
public class MyWebSocket {

    private static final Logger logger = LoggerFactory.getLogger(MyWebSocket.class);
    public static Map<String, Session> userIdMap = new HashMap<>();

    @Autowired
    private JedisUtil jedisUtil;

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) throws IOException {
        logger.info("new connection");
    }

    @OnClose
    public void onClose(Session session) throws IOException {

        for (String key : userIdMap.keySet()) {
            if (userIdMap.get(key) != null && userIdMap.get(key).equals(session)) {
                userIdMap.remove(key);
            }
        }
        logger.info("one connection closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {

        logger.info("接收到的信息websocket请求：" + message);
        JSONObject jsonObject = JSONObject.parseObject(message);
        String type = jsonObject.getString("type");
        if ("register".equals(type)) {
            String userId = jsonObject.getString("userId");
            userIdMap.put(userId, session);
            logger.info("userId加入map：" + userId);
        } else if ("selectedDevice".equals(type)) {
            String userId = jsonObject.getString("userId");
            String deviceIds = jsonObject.getString("deviceIds");
            this.jedisUtil.hset(CacheConstant.SELECTED_DEVICE, userId, deviceIds);
        }
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            logger.info(String.valueOf(b));
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    logger.info("read idle");
                    break;
                case WRITER_IDLE:
                    logger.info("write idle");
                    break;
                case ALL_IDLE:
                    logger.info("all idle");
                    break;
                default:
                    break;
            }
        }
    }

}
