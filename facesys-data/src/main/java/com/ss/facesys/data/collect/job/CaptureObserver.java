package com.ss.facesys.data.collect.job;

import com.alibaba.fastjson.JSONObject;
import com.ss.thread.CameraThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * com.ss.facesys.data.collect.job
 *
 * @author 李爽超 chao
 * @create 2021/02/24
 * @email lishuangchao@ss-cas.com
 **/
//@Component
public class CaptureObserver implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(CaptureObserver.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private final static int FACE_TYPE = 1;
    private final static int BODY_TYPE = 2;
    private final static int FULL_TYPE = 3;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            try {
                Long size = stringRedisTemplate.opsForList().size("q_body_data_sec");
                for (int i = 0; i < size; i++) {
                    String msg = stringRedisTemplate.opsForList().leftPop("q_body_data_sec");
                    if (msg != null) {
                        JSONObject jsonObject = JSONObject.parseObject(msg);
                        String panoramaImgName = jsonObject.getString("panoramaImgName");
                        String panoramaImgData = jsonObject.getString("panoramaImgData");
                        String bodyImg = jsonObject.getString("bodyImg");
                        String deviceNo = jsonObject.getString("deviceNo");
                        Map<Integer, String> captureMap = new HashMap<>();
                        captureMap.put(BODY_TYPE, bodyImg);
                        captureMap.put(FULL_TYPE, panoramaImgData);
                        CaptureThread captureThread = new CaptureThread(null, deviceNo, captureMap);
                        CameraThreadPool.getThread().execute(captureThread);
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                logger.info("监听redis抓拍照信息异常：" + e, e.toString());
            }
        }
    }
}
