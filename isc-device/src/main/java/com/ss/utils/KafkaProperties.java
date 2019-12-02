package com.ss.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.bootstrap")
@PropertySource("classpath:device.properties")
public class KafkaProperties {
    private static String servers;

    public static String getServers() {
        return servers;
    }

    public static void setServers(String servers) {
        KafkaProperties.servers = servers;
    }
}
