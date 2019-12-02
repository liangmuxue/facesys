package com.ss.spider.security.wrapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class CustomHeaderWrapper extends HttpServletRequestWrapper {

    private final Set<String> names = new HashSet();

    private final Map<String, String> headers = new HashMap();

    public CustomHeaderWrapper(HttpServletRequest request) {
        super(request);
        Enumeration<String> e = super.getHeaderNames();
        while (e.hasMoreElements()) {
            this.names.add(e.nextElement());
        }
    }

    public CustomHeaderWrapper(HttpServletRequest request, Map<String, String> headers) {
        this(request);
        if (headers != null && !headers.isEmpty()) {
            this.headers.putAll(headers);
            this.names.addAll(headers.keySet());
        }
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
        this.names.add(name);
    }


    public String getHeader(String name) {
        if (this.headers.containsKey(name)) {
            return (String) this.headers.get(name);
        }
        return super.getHeader(name);
    }


    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(this.names);
    }

}
