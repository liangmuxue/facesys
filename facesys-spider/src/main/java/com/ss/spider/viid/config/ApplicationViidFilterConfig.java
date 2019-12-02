package com.ss.spider.viid.config;

import com.ss.spider.viid.filter.ApplicationViidFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 1400接口拦截配置
 *
 * @author FrancisYs
 * @date 2019/10/25
 * @email yaoshuai@ss-cas.com
 */
@Configuration
public class ApplicationViidFilterConfig {

    @Resource
    private ViidProperties viidProperties;

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ApplicationViidFilter());
        String[] anonUrls = getAnonUrls();
        System.out.println("1400协议接口拦截器注册拦截接口：" + Arrays.toString(anonUrls));
        if (anonUrls.length > 0) {
            registration.addUrlPatterns(anonUrls);
        }
        registration.setName("viidFilter");
        return registration;
    }

    private String[] getAnonUrls() {
        String[] anonUrls = new String[0];
        if (this.viidProperties != null && !StringUtils.isEmpty(this.viidProperties.getAnonUrls())) {
            anonUrls = this.viidProperties.getAnonUrls().split(",");
        }
        return anonUrls;
    }

}
