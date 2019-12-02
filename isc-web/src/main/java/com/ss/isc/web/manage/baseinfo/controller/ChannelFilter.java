package com.ss.isc.web.manage.baseinfo.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;


@Component
public class ChannelFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestWrapper requestWrapper1 = null;
        if (servletRequest instanceof HttpServletRequest) {
            requestWrapper1 = new RequestWrapper((HttpServletRequest) servletRequest);
        }
        if (requestWrapper1 == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {

            filterChain.doFilter(requestWrapper1, servletResponse);
        }
    }

    public void destroy() {
    }

}
