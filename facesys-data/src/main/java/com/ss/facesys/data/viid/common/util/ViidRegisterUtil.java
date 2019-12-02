package com.ss.facesys.data.viid.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ss.facesys.data.viid.common.constants.ViidURL;
import com.ss.facesys.data.viid.common.dto.ViidRegisterSendDTO;
import com.ss.tools.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1400传输注册保活工具
 *
 * @author FrancisYs
 * @date 2019/10/24
 * @email yaoshuai@ss-cas.com
 */
public class ViidRegisterUtil {

    private static final Logger log = LoggerFactory.getLogger(ViidRegisterUtil.class);

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String STATUS_CODE = "StatusCode";
    private static final String STATUS_RESULT = "0";
    private static final String RESPONSE_STATUS_OBJECT = "ResponseStatusObject";


    public static boolean sendRegister(ViidRegisterSendDTO registerDTO) {
        try {
            JSONObject para = new JSONObject();
            para.put("DeviceID", registerDTO.getDeviceId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("RegisterObject", para);
            String url = registerDTO.getHttpProtocol().concat("://").concat(registerDTO.getIp()).concat(":").concat(registerDTO.getPort()) + ViidURL.REGISTER.getUrl();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(JSONObject.toJSONString(jsonObject), ContentType.APPLICATION_JSON));
            httpPost.addHeader("Connection", "Close");
            CloseableHttpClient httpClient = getClient();
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost, getContext(registerDTO.getSendUserName(), registerDTO.getSendPassWord(), registerDTO.getIp(), Integer.valueOf(registerDTO.getPort())));
            HttpEntity entity = closeableHttpResponse.getEntity();
            String response = null;
            if (entity != null) {
                response = EntityUtils.toString(entity, DEFAULT_CHARSET);
            }
            httpClient.close();
            log.info("系统注册接口url : {}, 推送数据 : {}, 返回结果 : {}", url, JSONObject.toJSONString(jsonObject), response);
            if (StringUtils.isBlank(response)) {
                log.error("系统注册失败:{}", response);
                return false;
            }
            JSONObject res = JSON.parseObject(response);
            if (StringUtils.equalsIgnoreCase(res.getJSONObject(RESPONSE_STATUS_OBJECT).getString(STATUS_CODE), STATUS_RESULT)) {
                return true;
            }
        } catch (Exception e) {
            log.error("系统注册异常:{}", e);
        }
        return false;
    }


    public static boolean sendKeepAlive(ViidRegisterSendDTO registerDTO) {
        try {
            JSONObject para = new JSONObject();
            para.put("DeviceID", registerDTO.getDeviceId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("KeepaliveObject", para);
            String url = registerDTO.getHttpProtocol().concat("://").concat(registerDTO.getIp()).concat(":").concat(registerDTO.getPort()) + ViidURL.KEEPALIVE.getUrl();
            String response = HttpClientUtil.httpPostWithJSONRequest(url, JSONObject.toJSONString(jsonObject), DEFAULT_CHARSET);
            log.info("系统保活接口url : {}, 推送数据 : {}, 返回结果 : {}", url, JSONObject.toJSONString(jsonObject), response);
            if (StringUtils.isBlank(response)) {
                log.error("系统保活失败:{}", response);
                return false;
            }
            JSONObject res = JSON.parseObject(response);
            if (StringUtils.equalsIgnoreCase(res.getJSONObject(RESPONSE_STATUS_OBJECT).getString(STATUS_CODE), STATUS_RESULT)) {
                return true;
            }
        } catch (Exception e) {
            log.error("系统保活异常:{}", e);
        }
        return false;
    }


    public static CloseableHttpClient getClient() {
        return HttpClients.custom().build();
    }


    private static HttpClientContext getContext(String username, String password, String host, Integer port) {
        HttpHost targetHost = new HttpHost(host, port, "http");
        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()), new UsernamePasswordCredentials(username, password));
        BasicAuthCache basicAuthCache = new BasicAuthCache();
        DigestScheme basicAuth = new DigestScheme(Consts.UTF_8);
        basicAuthCache.put(targetHost, basicAuth);
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(basicCredentialsProvider);
        context.setAuthCache(basicAuthCache);
        return context;
    }

}
