package com.ss.tools;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2";


    private static final String ACCEPT = "text/html,application/xhtml+xml,application/xml,application/json,application/x-www-query-urlencoded;q=0.9,*/*;q=0.8";


    private static final String ACCEPT_LANGUAGE = "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3";


    private static final String ACCEPT_CHARSET = "utf-8,gbk,gb2312,ISO-8859-1,;q=0.7,*;q=0.7";


    private static PoolingHttpClientConnectionManager POOL_CM;


    private static final String DEFAULT_CHARSET = "UTF-8";


    private static String EMPTY_STR = "";


    private static final int RETRY_COUNT = 5;


    static HttpRequestRetryHandler httpRequestRetryHandler;


    static {
        PlainConnectionSocketFactory plainConnectionSocketFactory = PlainConnectionSocketFactory
                .getSocketFactory();
        SSLConnectionSocketFactory sSLConnectionSocketFactory = SSLConnectionSocketFactory
                .getSocketFactory();

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainConnectionSocketFactory)
                .register("https", sSLConnectionSocketFactory).build();
        if (POOL_CM == null) {
            POOL_CM = new PoolingHttpClientConnectionManager(registry);

            POOL_CM.setMaxTotal(200);

            POOL_CM.setDefaultMaxPerRoute(20);
        }

        httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                boolean result = false;

                if (exception == null) {
                    throw new IllegalArgumentException("Exception parameter may not be null");
                }
                if (context == null) {
                    throw new IllegalArgumentException("HTTP context may not be null");
                }
                if (executionCount >= 5) {
                    logger.info("请求已经尝试了5次，已经放弃请求！");
                    return false;
                }

                if (exception instanceof org.apache.http.NoHttpResponseException) {
                    logger.info("如果服务器丢掉了连接，正在尝试……");
                    return true;
                }

                if (exception instanceof javax.net.ssl.SSLHandshakeException) {
                    logger.info("SSL握手异常……");
                    return false;
                }

                if (exception instanceof java.io.InterruptedIOException) {
                    logger.info("请求超时……");
                    return false;
                }

                if (exception instanceof java.net.UnknownHostException) {
                    logger.info("无法到达目标服务器……");
                    return false;
                }

                if (exception instanceof org.apache.http.conn.ConnectTimeoutException) {
                    logger.info("目标服务器拒绝……");
                    return false;
                }

                if (exception instanceof javax.net.ssl.SSLException) {
                    logger.info("SSL目标服务器拒绝……");
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();

                if (!(request instanceof org.apache.http.HttpEntityEnclosingRequest)) {
                    logger.info("正在尝试再次连接目标服务器……");
                    return true;
                }

                if (result) {
                    logger.info("请求失败,第" + executionCount + "次重试");
                }

                return result;
            }
        };
    }


    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair((String) param.getKey(), (String) param.getValue()));
        }

        return pairs;
    }

    private static void config(HttpRequestBase httpRequestBase) {
        httpRequestBase.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        httpRequestBase.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml,application/json,application/x-www-query-urlencoded;q=0.9,*/*;q=0.8");
        httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpRequestBase.setHeader("Accept-Charset", "utf-8,gbk,gb2312,ISO-8859-1,;q=0.7,*;q=0.7");
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000)
                .setConnectTimeout(3000).setSocketTimeout(3000).build();
        httpRequestBase.setConfig(requestConfig);
    }

    private static CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(POOL_CM)
                .setRetryHandler(httpRequestRetryHandler).build();
    }


    private static String getResult(HttpRequestBase request, String charset) throws Exception {
        CloseableHttpClient httpClient = getHttpClient();

        String result = "";
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                if (StringUtils.isNotEmpty(charset)) {
                    result = EntityUtils.toString(entity, charset);
                } else {
                    result = EntityUtils.toString(entity, "UTF-8");
                }

                response.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            logger.error("客户端协议异常，原因：", e);
        } catch (IOException e) {
            logger.error("IO异常，原因:", e);
        }

        return EMPTY_STR;
    }


    public static String httpGetRequest(String url, Map<String, Object> headers,
                                        Map<String, Object> params, String charset) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader((String) param.getKey(), (String) param.getValue());
        }
        return getResult(httpGet, charset);
    }


    public static String httpGetRequest(String url, Map<String, Object> params, String charset)
            throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet, charset);
    }


    public static String httpGetRequest(String url, String resultCharType) throws Exception {
        HttpGet httpGet = new HttpGet(url);

        config(httpGet);
        return getResult(httpGet, resultCharType);
    }


    public static String httpPostRequest(String url, Map<String, Object> headers,
                                         Map<String, Object> params, String charset) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader((String) param.getKey(), (String) param.getValue());
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
        return getResult(httpPost, charset);
    }


    public static String httpPostRequest(String url, Map<String, Object> params, String charset)
            throws Exception {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
        return getResult(httpPost, charset);
    }


    public static String httpPostRequest(String url, String resultCharType) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost, resultCharType);
    }


    public static String httpPostWithJSONRequest(String url, Map<String, Object> headers, String json,
                                                 String charset) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader((String) param.getKey(), (String) param.getValue());
        }

        httpPost.setEntity(stringEntity);
        return getResult(httpPost, charset);
    }


    public static String httpPostWithJSONRequest(String url, String json, String charset)
            throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        return getResult(httpPost, charset);
    }


    public static String httpPutWithJSONRequest(String url, String json, String charset)
            throws Exception {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        return getResult(httpPut, charset);
    }


    public static String httpPutWithJSONRequest(String url, Map<String, Object> headers, String json,
                                                String charset) throws Exception {
        HttpPut httpPut = new HttpPut(url);

        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPut.addHeader((String) param.getKey(), (String) param.getValue());
        }
        httpPut.setEntity(stringEntity);
        return getResult(httpPut, charset);
    }


    public static String httpDeleteRequest(String url, Map<String, Object> params, String charset)
            throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpDelete httpDelete = new HttpDelete(ub.build());
        return getResult(httpDelete, charset);
    }


    public static String httpDeleteRequest(String url, Map<String, Object> headers,
                                           Map<String, Object> params, String charset) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpDelete httpDelete = new HttpDelete(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpDelete.addHeader((String) param.getKey(), (String) param.getValue());
        }
        return getResult(httpDelete, charset);
    }


    public static String httpDeleteWithJSONRequest(String url, String json, String charset)
            throws Exception {
        HttpDelete httpDelete = new HttpDelete(url);

        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpDelete.setEntity(stringEntity);
        return getResult(httpDelete, charset);
    }


    public static String httpDeleteWithJSONRequest(String url, Map<String, Object> headers,
                                                   String json, String charset) throws Exception {
        HttpDelete httpDelete = new HttpDelete(url);

        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpDelete.addHeader((String) param.getKey(), (String) param.getValue());
        }

        httpDelete.setEntity(stringEntity);
        return getResult(httpDelete, charset);
    }

    private static class HttpDelete
            extends HttpEntityEnclosingRequestBase {

        public HttpDelete() {
        }

        public HttpDelete(URI uri) {
            setURI(uri);
        }


        public HttpDelete(String uri) {
            setURI(URI.create(uri));
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }

    }

}
