package com.ss.isc.util.http;

import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.constant.HttpConstant;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.communication.HttpHead;
import com.j7cai.common.communication.HttpsClientHelper;
import com.j7cai.common.communication.InterfaceBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * BaseHttpUtil：http请求工具类
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public abstract class BaseHttpUtil {

    public static final Log LOG = LogFactory.getLog(BaseHttpUtil.class);

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    public static final String CONTENT_CODE_UTF_8 = "UTF-8";

    public static String token = null;
    public static long tokenTime = 0L;

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
            if (!requestUrl.equals(PropertiesUtil.getOshttp() + HttpConstant.TOKEN)) {
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
            Map<String, String> headers = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY);
            headers.put("Authorization", token);
            httpHead.setHeaders(headers);
            // 无参 | 连接测试桩情况：初始化空的json串
            if (null == parmJson || parmJson.length() == 0) {
                parmJson = "{}";
            } else if (PropertiesUtil.isLocal()) {
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
        Map<String, Object> parm = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        parm.put("appId", PropertiesUtil.getAppId());
        parm.put("appSecret", PropertiesUtil.getAppSecret());
        // 发起鉴权请求并获取请求结果
        Object jsonObject = JSONObject.toJSON(parm);
        String resultString = httpPost(jsonObject.toString(), PropertiesUtil.getOshttp() + HttpConstant.TOKEN, null);
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

    public static String iscHttpPost(String parmJson, String requestUrl, HttpHead httpHead) {
        String resultString = null;
        try {
            InterfaceBean interf = new InterfaceBean();
            interf.setMethod("POST");
            if (null == httpHead) {
                httpHead = new HttpHead();
                httpHead.setServerAddress(requestUrl);
                httpHead.setContentType(APPLICATION_JSON);
            }
            Map<String, String> headers = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY);
            httpHead.setHeaders(headers);
            resultString = HttpsClientHelper.getInstance().doHttpsLogicRequest(httpHead, interf, parmJson);
        } catch (Exception e) {
            LOG.error(e.toString(), e);
        }
        return resultString;
    }


    public static String httpGet(String url, Map<String, String> paramMap) throws ClientProtocolException, IOException {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet();
        List<NameValuePair> formparams = setHttpParams(paramMap);
        String param = URLEncodedUtils.format(formparams, CONTENT_CODE_UTF_8);
        httpGet.setURI(URI.create(url + "?" + param));
        HttpResponse response = defaultHttpClient.execute(httpGet);
        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        return httpEntityContent;
    }

    private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formparams.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        return formparams;
    }

    private static String getHttpEntityContent(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            br.close();
            is.close();
            return sb.toString();
        }
        return "";
    }


    public static String getToken() {
        return token;
    }


    public static void setToken(String token) {
        BaseHttpUtil.token = token;
    }


    public static long getTokenTime() {
        return tokenTime;
    }


    public static void setTokenTime(long tokenTime) {
        BaseHttpUtil.tokenTime = tokenTime;
    }

}
