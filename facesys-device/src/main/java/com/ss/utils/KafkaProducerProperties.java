package com.ss.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.producer")
public class KafkaProducerProperties {

    private static final String KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String VALUE_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String ACK = "all";
    private static final Integer RETRIES = 2147483647;

    private String keySerializer = KEY_SERIALIZER;
    private String valueSerializer = VALUE_SERIALIZER;
    private String ack = ACK;
    private Integer retries = RETRIES;


    public String getKeySerializer() {
        return this.keySerializer;
    }

    public void setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
    }

    public String getValueSerializer() {
        return this.valueSerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }

    public String getAck() {
        return this.ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public Integer getRetries() {
        return this.retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

}
