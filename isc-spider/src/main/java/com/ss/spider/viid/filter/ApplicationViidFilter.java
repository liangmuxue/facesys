package com.ss.spider.viid.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.enums.CommonEnumClass;
import com.ss.response.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1400接口过滤器
 *
 * @author FrancisYs
 * @date 2019/10/25
 * @email yaoshuai@ss-cas.com
 */
public class ApplicationViidFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(ApplicationViidFilter.class);;
    private static final String FILTER_URL_PERFIX = "/viid/";

    @Resource
    private ObjectMapper mapper;
//    @Autowired
//    @Qualifier("oauthCacheServiceImpl")
//    private OauthCacheService cacheService;
//    @Autowired
//    private OceanApplicationService oceanApplicationService;
//    @Autowired
//    private GeParaService geParaService;

    public ApplicationViidFilter() { }

    @Override
    public void init(final FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String servletPath = httpRequest.getServletPath();
        logger.info("1400接口拦截请求地址：" + servletPath);
        if (!servletPath.contains(FILTER_URL_PERFIX)) {
            if (!keepViidConnect()) {
                logger.error("1400接口拦截转发失败：视图库连接失败");
                this.returnErrorResult(servletResponse);
                return;
            }
            servletPath = "/viid" + servletPath;
            logger.info("1400接口拦截转发地址：" + servletPath);
            httpRequest.getRequestDispatcher(servletPath).forward(servletRequest, servletResponse);
        }
    }

    /**
     * 1400接口注册/保活
     * @return
     */
    private boolean keepViidConnect() {


        return true;
    }

    private void returnErrorResult(ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setHeader("Content-type", "application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
        servletOutputStream.write(this.mapper.writeValueAsString(new ResponseEntity(CommonEnumClass.CommonInterfaceEnum.UNAUTHORIZED_ACCESS.getKey(), CommonEnumClass.CommonInterfaceEnum.UNAUTHORIZED_ACCESS.getValue())).getBytes("UTF-8"));
        servletOutputStream.flush();
        servletOutputStream.close();
    }

    @Override
    public void destroy() {
    }

}
