//package com.ss.job;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.ss.facesys.util.autoconfigure.DeviceProperties;
//import com.ss.facesys.util.constant.CacheConstant;
//import com.ss.facesys.util.http.BaseHttpUtil;
//import com.ss.facesys.util.jedis.JedisUtil;
//import com.ss.utils.CKafkaProducer;
//import com.ss.utils.DeviceUtil;
//import com.ss.utils.FileUtils;
//import org.apache.commons.io.filefilter.FileFilterUtils;
//import org.apache.commons.io.filefilter.IOFileFilter;
//import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
//import org.apache.commons.io.monitor.FileAlterationMonitor;
//import org.apache.commons.io.monitor.FileAlterationObserver;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.io.FileFilter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 文件夹变化监听
// * @author 李爽超 chao
// * @create 2019/09/16
// * @email lishuangchao@ss-cas.com
// **/
//@Component
//public class FileObserver implements ApplicationRunner {
//
//    private static final Logger log = LoggerFactory.getLogger(FileObserver.class);
//    @Resource
//    private JedisUtil jedisUtil;
//    @Resource
//    private CKafkaProducer cKafkaProducer;
//
//    @Override
//    public void run(ApplicationArguments applicationArguments) throws Exception{
//        //监听路径
//        String filePath= DeviceUtil.getCutFlowUrl() + "/";
//        FileFilter filter= FileFilterUtils.and((IOFileFilter) new MyFileFilter());
//        FileAlterationObserver filealtertionObserver=new FileAlterationObserver(filePath, filter);
//        filealtertionObserver.addListener(new FileAlterationListenerAdaptor(){
//            /**
//             * 监听图片创建
//             * @param file
//             */
//            @Override
//            public void onFileCreate(File file)
//            {
//                try {
//                    String result = null;
//                    if ("cloudwalk".equals(file.getParentFile().getParentFile().getName())){
//                        //云从抓拍机
//                        String url = DeviceProperties.getCutFlowAgentUrl() + "/cloudwalk";
//                        String fileUrl = url + "/" + file.getParentFile().getName() + "/" + file.getName();
//                        String[] temps = file.getName().split("-");
//                        String valueKey = temps[0] + temps[2];
//                        if (FileObserver.this.jedisUtil.hmget(CacheConstant.REDIS_KEY_DEVICE_SNAP_CLOUDWALK) == null){
//                            Map<String, Object> deviceMap = new HashMap<>();
//                            deviceMap.put(valueKey, fileUrl);
//                            FileObserver.this.jedisUtil.hmset(CacheConstant.REDIS_KEY_DEVICE_SNAP_CLOUDWALK, deviceMap);
//                        } else {
//                            if (FileObserver.this.jedisUtil.hHasKey(CacheConstant.REDIS_KEY_DEVICE_SNAP_CLOUDWALK, valueKey)){
//                                String brotherFileUrl = (String)FileObserver.this.jedisUtil.hget(CacheConstant.REDIS_KEY_DEVICE_SNAP_CLOUDWALK, valueKey);
//                                for (String temp: temps) {
//                                    FileObserver.this.jedisUtil.hdel(CacheConstant.REDIS_KEY_DEVICE_SNAP_CLOUDWALK, valueKey);
//                                    String base64 = FileUtils.getBase64ByUrl(brotherFileUrl);
//                                    String base64Full = FileUtils.getBase64ByUrl(fileUrl);
//                                    if (temp.contains("full")){
//                                        //发送请求
//                                        result = requestvplatRequest(valueKey, base64, base64Full, file.getParentFile().getName());
//                                        break;
//                                    } else {
//                                        //发送请求
//                                        result = requestvplatRequest(valueKey, base64Full, base64, file.getParentFile().getName());
//                                        break;
//                                    }
//                                }
//                            } else {
//                                FileObserver.this.jedisUtil.hset(CacheConstant.REDIS_KEY_DEVICE_SNAP_CLOUDWALK, valueKey, fileUrl);
//                                return;
//                            }
//                        }
//                    } else {
//                        //普通摄像机
//                        //图片base64
//                        String base64ByUrl = FileUtils.getBase64ByUrl(DeviceProperties.getCutFlowAgentUrl() + "/common/" + file.getParentFile().getName() + "/" + file.getName());
//                        result = requestvplatRequest(file.getParentFile().getName() + System.currentTimeMillis(), base64ByUrl, null, file.getParentFile().getName());
//                    }
//                    if (result != null){
//                        JSONObject jsonObject = JSON.parseObject(result);
//                        if ("00000000".equals(jsonObject.getString("code")) || "0".equals(jsonObject.getString("code")) || "0".equals(jsonObject.getString("respCode"))) {
//                            log.debug("抓拍照接入成功！");
//                        }
//                    }
//                } catch (Exception e) {
//                    log.error("抓拍照接入请求异常", e);
//                }
//                super.onFileCreate(file);
//            }
//
//        });
//
//        FileAlterationMonitor filealterationMonitor=new FileAlterationMonitor(1000);
//        filealterationMonitor.addObserver(filealtertionObserver);
//        //开始监听
//        filealterationMonitor.start();
//        log.info("设备抽帧图片监听开启");
//    }
//
//    private String requestvplatRequest(String valueKey, String base64, String base64Full, String parentName){
//        String result = null;
//        Map<String, Object> paramJson = new HashMap<>();
//        paramJson.put("captureId", valueKey);
//        paramJson.put("captureImg", base64);
//        if(base64Full != null){
//            paramJson.put("panoramaId", valueKey);
//            paramJson.put("panoramaImg", base64Full);
//        }
//        paramJson.put("deviceId", parentName);
//        paramJson.put("captureTime", System.currentTimeMillis());
//        if ("0".equals(DeviceUtil.getKafka())){
//            try (KafkaProducer kafkaProducer = cKafkaProducer.getKafkaProducer()) {
//                kafkaProducer.send(new ProducerRecord<>("ISC_DEVICE_SNAP", "DEVICE_SNAP_CLOUDWALK", JSON.toJSONString(paramJson)));
//                log.debug("发送kafka主题：{}, 数据：{}", "ISC_DEVICE_SNAP", "DEVICE_SNAP_CLOUDWALK");
//            } catch (Exception e) {
//                log.error("发送kafka主题：{}, 数据：{}失败！ 错误原因:{}.", new Object[]{"ISC_DEVICE_SNAP", "DEVICE_SNAP_CLOUDWALK"});
//            }
//        } else {
//            result = BaseHttpUtil.httpPost(JSON.toJSONString(paramJson), DeviceProperties.getCameraCaptureUrl(), null);
//        }
//        return result;
//    }
//
//}
//
//class MyFileFilter implements IOFileFilter{
//
//    @Override
//    public boolean accept(File file)
//    {
//        return true;
//    }
//
//    @Override
//    public boolean accept(File dir, String name)
//    {
//        return true;
//    }
//
//}
