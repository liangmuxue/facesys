package com.ss.spider.log.filter;

import com.ss.spider.log.bean.ReqestInfo;
import com.ss.spider.log.constants.Constants;
import com.ss.spider.log.utils.IpUtil;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CwLogFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String resourcePath = request.getServletPath();
        ReqestInfo reqestInfo = new ReqestInfo();
        reqestInfo.setPath(resourcePath);
        reqestInfo.setRemoteIp(IpUtil.getIpAddress(request));
        reqestInfo.setBaseUrl(request.getScheme() + "://" + request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath());

        Constants.REQEST_INFO.set(reqestInfo);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
