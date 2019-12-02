package com.ss.utils;

import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.communication.HttpHead;
import com.j7cai.common.communication.HttpsClientHelper;
import com.j7cai.common.communication.InterfaceBean;
import com.ss.facesys.util.PropertiesUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import java.util.*;

/**
 * http请求工具类
 * @author 李爽超 chao
 * @date 2019/9/27
 * @email lishuangchao@ss-cas.com
 */
public abstract class BaseHttpUtil {

    public static final Log LOG = LogFactory.getLog(BaseHttpUtil.class);
    public static final String APPLICATION_JSON = "application/json";
    public static String token = null;
    public static long tokenTime = 0L;
    public static String url = PropertiesUtil.getOshttp();

    /**
     * POST请求调用欧神接口
     * @param parmJson
     * @param requestUrl
     * @param httpHead
     * @return
     */
    public static String httpPost(String parmJson, String requestUrl, HttpHead httpHead) {
        String resultString = null;
        try {
            InterfaceBean interf = new InterfaceBean();
            interf.setMethod("POST");
            // 非鉴权请求：访问前校验token有效性，失效则重新获取
            if (!url.equals(requestUrl)) {
                if (System.currentTimeMillis() > tokenTime) {
                    login();
                }
            }
            // 请求头
            if (null == httpHead) {
                httpHead = new HttpHead();
                httpHead.setServerAddress(requestUrl);
                httpHead.setContentType(APPLICATION_JSON);
            }
            Map<String, String> headers = new HashMap<String, String>(16);
            headers.put("Authorization", token);
            httpHead.setHeaders(headers);
            // 无参 | 连接测试桩情况：初始化空的json串
            if (null == parmJson || parmJson.length() == 0) {
                parmJson = "{}";
            }
            // 发起请求并接收返回结果
            resultString = HttpsClientHelper.getInstance().doHttpsLogicRequest(httpHead, interf, parmJson);
        } catch (Exception e) {
            LOG.error("httpPost fail", e);
        }
        return resultString;
    }

    /**
     * 欧神登录鉴权
     * @return  token及超时时间
     */
    public static String login() {
        Map<String, Object> parm = new HashMap<>(16);
        parm.put("appId", PropertiesUtil.getAppId());
        parm.put("appSecret", PropertiesUtil.getAppSecret());
        // 发起鉴权请求并获取请求结果
        Object jsonObject = JSONObject.toJSON(parm);
        String resultString = httpPost(jsonObject.toString(), url, null);
        String code = null;
        JSONObject jsobj = null;
        if (null != resultString) {
            jsobj = JSONObject.parseObject(resultString);
            code = jsobj.get("code").toString();
        }
        // 获取token并设置token超时时间
        if (null != jsobj && "00000000".equals(code)) {
            JSONObject tk = (JSONObject) jsobj.get("data");
            token = "Bearer " + tk.getString("token");
            tokenTime = System.currentTimeMillis() + 300000L;
            return "Bearer " + tk.getString("token");
        }
        return null;
    }

    /**
     * 设备流请求
     * @param deviceUrl
     * @param deviceId
     * @param name
     */
    public static void deviceHttpPost(String deviceUrl, String deviceId, String name) {

        String result;
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String json = "{\"cameras\": [{\"cameraId\": \"" + deviceId + "\",\"cameraName\":\"" + name + "\"}]}";
        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(deviceUrl);
            // 连接超时设置
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
            // 读取超时设置
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
            StringEntity stringEntity = new StringEntity(json, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, "UTF-8");
                LOG.info(result);
            }
        } catch (Exception e) {
            LOG.error("设备流错误", e);
        } finally {
            // 终止本次请求
            assert httpPost != null;
            httpPost.abort();
            // 释放连接
            httpPost.releaseConnection();
            httpClient.getConnectionManager().shutdown();
        }
    }
}
