package com.ss.spider.interceptor.requestwrapper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CwHttpServletRequestWrapper extends HttpServletRequestWrapper {

    protected Map<String, String[]> params = new HashMap();

    private static final String CONTENT_TYPE_APPLICATION_JSON_ONE= "application/json";
    private static final String CONTENT_TYPE_APPLICATION_JSON_TWO = "application/json;charset=UTF-8";
    protected Enumeration<String> parameterNames;
    private String requestBody = null;

    public CwHttpServletRequestWrapper(HttpServletRequest request, String userId, boolean isSuperRole)
            throws UnsupportedEncodingException {
        super(request);
        if (isApplicationJsonReq()) {
            if (this.requestBody == null) {
                this.requestBody = readBody(request);

                Map<String, Object> bodyMap = (Map) JSON.parseObject(this.requestBody, Map.class);
                bodyMap.put("userId", userId);
                bodyMap.put("isSuperRole", Boolean.valueOf(isSuperRole));
                this.requestBody = JSON.toJSONString(bodyMap);
            }
        } else {
            request.setCharacterEncoding("UTF-8");

            this.params.putAll(request.getParameterMap());
            this.params.put("userId", new String[]{userId});
            this.params.put("isSuperRole", new String[]{Boolean.valueOf(isSuperRole).toString()});
            Set<String> paramNames = new LinkedHashSet<String>();
            paramNames.add("userId");
            paramNames.add("isSuperRole");
            Enumeration paramEnum = super.getParameterNames();
            while (paramEnum.hasMoreElements()) {
                paramNames.add((String) paramEnum.nextElement());
            }
            this.parameterNames = Collections.enumeration(paramNames);
        }
    }

    private boolean isApplicationJsonReq() {
        if (getRequest() == null) {
            return false;
        }
        return CONTENT_TYPE_APPLICATION_JSON_ONE.equals(getRequest().getContentType()) || CONTENT_TYPE_APPLICATION_JSON_TWO.equals(getRequest().getContentType());
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return (this.parameterNames == null) ? super.getParameterNames() : this.parameterNames;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params.isEmpty() ? super.getParameterMap() : this.params;
    }

    @Override
    public String getParameter(String name) {
        if (this.params.isEmpty()) {
            return super.getParameter(name);
        }
        String[] values = (String[]) this.params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return (String[]) this.params.get(name);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return isApplicationJsonReq() ? new CustomServletInputStream(this.requestBody)
                : super.getInputStream();
    }


    private static String readBody(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = request.getReader();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read body.", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException iOException) {
                }
            }
        }

        return sb.toString();
    }

    private class CustomServletInputStream extends ServletInputStream {

        private ByteArrayInputStream buffer;

        public CustomServletInputStream(String body) {
            body = (body == null) ? "" : body;
            this.buffer = new ByteArrayInputStream(body.getBytes());
        }

        @Override
        public int read() throws IOException {
            return this.buffer.read();
        }

        @Override
        public boolean isFinished() {
            return (this.buffer.available() == 0);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new RuntimeException("Not implemented");
        }

    }

}
