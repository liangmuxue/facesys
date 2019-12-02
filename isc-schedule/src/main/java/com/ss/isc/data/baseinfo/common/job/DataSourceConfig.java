package com.ss.isc.data.baseinfo.common.job;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataSourceConfig {

    @Bean({"datasource"})
    @ConfigurationProperties("spring.datasource")
    public DataSource init() {
        return DruidDataSourceBuilder.create().build();
    }

}
