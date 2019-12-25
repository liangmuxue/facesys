package com.ss.job;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DeviceDataSourceConfig {

    @Bean({"datasource"})
    @ConfigurationProperties("spring.datasource")
    public DataSource init() {
        return DruidDataSourceBuilder.create().build();
    }

}
