package com.ss.utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties({KafkaProperties.class, KafkaProducerProperties.class})
@ConditionalOnClass({KafkaProducer.class})
public class CKafkaProducer {

    @Autowired
    private KafkaProducerProperties kafkaProducerProperties;

    @ConditionalOnMissingBean({KafkaProducer.class})
    public <V, T> KafkaProducer getKafkaProducer() {
        Properties props = new Properties();
        props.put("key.serializer", this.kafkaProducerProperties.getKeySerializer());
        props.put("value.serializer", this.kafkaProducerProperties.getValueSerializer());
        props.put("acks", this.kafkaProducerProperties.getAck());
        props.put("retries", this.kafkaProducerProperties.getRetries());
        props.put("bootstrap.servers", KafkaProperties.getServers());

        return new KafkaProducer(props);
    }

}
