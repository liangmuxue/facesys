package com.ss.spider.log.config;

import com.ss.spider.log.filter.CwLogFilter;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@EnableAsync
public class CwWebLogConfig {

    @Autowired
    private CwLogJobConfig cwLogJobConfig;
    private static final int DEFAULT_MAX_POLL_SIZE = 5;

    @Bean
    public Executor logJobExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        if (this.cwLogJobConfig.getCorePoolSize() != null && this.cwLogJobConfig.getCorePoolSize().intValue() > 0) {
            executor.setCorePoolSize(this.cwLogJobConfig.getCorePoolSize().intValue());
        }
        if (this.cwLogJobConfig.getMaxPoolSize() != null && this.cwLogJobConfig.getMaxPoolSize().intValue() > 0) {
            executor.setMaxPoolSize(this.cwLogJobConfig.getMaxPoolSize().intValue());
        } else if (5 < executor.getCorePoolSize()) {
            executor.setMaxPoolSize(executor.getCorePoolSize());
        } else {
            executor.setMaxPoolSize(5);
        }

        if (this.cwLogJobConfig.getQueueCapacity() != null && this.cwLogJobConfig.getQueueCapacity().intValue() > 0) {
            executor.setQueueCapacity(this.cwLogJobConfig.getQueueCapacity().intValue());
        }
        if (this.cwLogJobConfig.getKeepAliveSeconds() != null && this.cwLogJobConfig.getKeepAliveSeconds().intValue() > 0) {
            executor.setKeepAliveSeconds(this.cwLogJobConfig.getKeepAliveSeconds().intValue());
        }
        if (executor.getCorePoolSize() > executor.getMaxPoolSize()) {
            executor.setMaxPoolSize(executor.getCorePoolSize());
        }
        executor.setThreadNamePrefix("log-job-pool-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }


    @Bean
    public FilterRegistrationBean logFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(CwLogFilter());
        registration.addUrlPatterns(new String[]{"/*"});
        registration.setName("logFilter");
        registration.setOrder(-2147483648);
        return registration;
    }


    @Bean
    public Filter CwLogFilter() {
        return new CwLogFilter();
    }

}
