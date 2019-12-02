package com.ss.util.redis.cluster.config;

import com.ss.util.redis.cluster.util.RedisClusterCommonCmd;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


@Configuration
@ConditionalOnProperty(name = {"spring.redis.cluster.state"}, havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties({RedisClusterProperties.class})
public class RedisClusterConfig {

    private static final String REDIS_NODE_SPLIT = ",";
    private static final String REDIS_HOST_PORT_SPLIT = ":";
    @Autowired
    private RedisClusterProperties redisClusterProperties;

    public JedisCluster jedisCluster() {
        String[] serverArray = this.redisClusterProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim()).intValue()));
        }

        return new JedisCluster(nodes, this.redisClusterProperties.getTimeout(), this.redisClusterProperties.getMaxAttempts());
    }

    @Bean
    public RedisClusterCommonCmd RedisClusterCommonCmd() {
        return new RedisClusterCommonCmd(jedisCluster());
    }

}
