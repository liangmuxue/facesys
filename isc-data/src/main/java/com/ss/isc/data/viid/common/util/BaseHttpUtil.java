package com.ss.isc.data.viid.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ss.isc.data.viid.common.dto.common.ResponseStatusObject;
import com.ss.isc.data.viid.common.dto.common.ResponseStatusObjectList;
import com.ss.isc.data.viid.common.dto.common.ResponseStatusVIID;
import com.ss.spider.viid.config.ViidProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.*;
/**
* VIID请求
* @author chao
* @create 2019/10/28
* @email lishuangchao@ss-cas.com
**/
public abstract class BaseHttpUtil {

    public static final Log LOG = LogFactory.getLog(BaseHttpUtil.class);

    public static final String APPLICATION_JSON = "application/VIID+JSON";
    public static final String CONTENT_CODE_UTF_8 = "UTF-8";
    private static final String STATUS_CODE = "StatusCode";
    private static final String STATUS_RESULT = "0";
    private static final String RESPONSE_STATUS_OBJECT = "ResponseStatusObject";

    /**
     * VIID POST请求
     * @param parmJson
     * @param requestUrl
     * @return
     */
    public static String viidHttpPost(String parmJson, String requestUrl) {
        String result = null;
        try {
            // 获取默认的请求客户端
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //获取请求地址
            String url = getViidUrl().concat(requestUrl);
            // 通过HttpPost来发送post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(new StringEntity(parmJson, ContentType.APPLICATION_JSON));
            httpPost.addHeader("Content-Type", APPLICATION_JSON);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, CONTENT_CODE_UTF_8);
            }
            httpClient.close();
            if (StringUtils.isBlank(result)) {
                LOG.info("VIID接口请求失败:" + requestUrl);
            }
        } catch (Exception e) {
            LOG.error("VIID POST请求异常" + e.toString(), e);
        }
        return result;
    }

    /**
     * VIID GET请求
     * @param requestUrl
     * @param paramMap
     * @return
     */
    public static String viidHttpGet(String requestUrl, Map<String, String> paramMap) {
        String result = null;
        try {
            // 获取默认的请求客户端
            CloseableHttpClient client = HttpClients.createDefault();
            //获取请求地址
            String url = getViidUrl().concat(requestUrl);
            HttpGet httpGet = new HttpGet();
            List<NameValuePair> formparams = setHttpParams(paramMap);
            String param = URLEncodedUtils.format(formparams, CONTENT_CODE_UTF_8);
            httpGet.setURI(URI.create(url + "?" + param));
            httpGet.setHeader("Content-Type", APPLICATION_JSON);
            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, CONTENT_CODE_UTF_8);
            }
            response.close();
            if (StringUtils.isBlank(result)) {
                LOG.info("VIID接口请求失败:" + requestUrl);
            }
        } catch (Exception e) {
            LOG.error("VIID GET请求异常" + e.toString(), e);
        }
        return result;
    }

    /**
     * VIID PUT请求
     * @param parmJson
     * @param requestUrl
     * @return
     */
    public static String viidHttpPut(String parmJson, String requestUrl) {
        String result = null;
        try {
            // 获取默认的请求客户端
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //获取请求地址
            String url = getViidUrl().concat(requestUrl);
            // 通过HttpPut来发送put请求
            HttpPut httpPut = new HttpPut(url);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
            httpPut.setConfig(requestConfig);
            httpPut.setEntity(new StringEntity(parmJson, ContentType.APPLICATION_JSON));
            httpPut.addHeader("Content-Type", APPLICATION_JSON);
            HttpResponse response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, CONTENT_CODE_UTF_8);
            }
            httpClient.close();
            if (StringUtils.isBlank(result)) {
                LOG.info("VIID接口请求失败:" + requestUrl);
            }
        } catch (Exception e) {
            LOG.error("VIID PUT请求异常" + e.toString(), e);
        }
        return result;
    }

    private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formparams.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        return formparams;
    }

    /**
     * 获取VIID地址
     * @return
     */
    private static String getViidUrl() {
        String concat = ViidProperties.getHttpProtocol() + "://" + ViidProperties.getIp() + ":" + ViidProperties.getPort();
        return concat;
    }

    /**
     * 判断请求是否成功
     * @param param
     * @return
     */
    public static boolean checkSuccess(String param){
        JSONObject res = JSON.parseObject(param);
        boolean result = false;
        if (StringUtils.equalsIgnoreCase(res.getJSONObject(RESPONSE_STATUS_OBJECT).getString(STATUS_CODE), STATUS_RESULT)) {
            result = true;
        }
        return result;
    }

    /**
     * 判断批量请求是否成功
     * @param param
     * @return
     */
    public static boolean checkListSuccess(String param){
        ResponseStatusObjectList responseStatusObjectList = new ResponseStatusObjectList();
        responseStatusObjectList = JSON.parseObject(param, ResponseStatusObjectList.class);
        boolean result = true;
        for (final ResponseStatusVIID responseStatusVIID : responseStatusObjectList.getResponseStatusListObject().getResponseStatusObject()){
            if (!"0".equalsIgnoreCase(responseStatusVIID.getStatusCode())) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 创建响应对象
     * @param requestURL
     * @param statusCode
     * @param statusString
     * @param id
     * @return
     */
    public static String createResponse(String requestURL, String statusCode, String statusString, String id){
        ResponseStatusVIID responseStatusVIID = new ResponseStatusVIID();
        ResponseStatusObject responseStatusObject = new ResponseStatusObject();
        if (id != null){
            responseStatusVIID.setId(id);
        }
        responseStatusVIID.setRequestURL(requestURL);
        responseStatusVIID.setStatusCode(statusCode);
        responseStatusVIID.setStatusString(statusString);
        responseStatusObject.setResponseStatusObject(responseStatusVIID);
        return JSONObject.toJSONString(responseStatusObject);
    }

}
