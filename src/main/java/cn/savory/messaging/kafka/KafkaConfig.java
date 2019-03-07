package cn.savory.messaging.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * @author hc_zhang
 * @date 2019/2/21.
 */
public abstract class KafkaConfig {

    public KafkaConfig() {
    }

    public KafkaConfig(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    /**
     * kafka集群地址
     */
    private String bootstrapServers;

    /**
     * request.timeout.ms
     */
    private Integer requestTimeoutMs;


    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public Integer getRequestTimeoutMs() {
        return requestTimeoutMs;
    }

    public void setRequestTimeoutMs(Integer requestTimeoutMs) {
        this.requestTimeoutMs = requestTimeoutMs;
    }

    protected static void putIfSet(Properties properties, String key, String value) {
        if (value != null && value.length() > 0) {
            properties.put(key, value);
        }
    }

    protected static void putIfSet(Properties properties, String key, Integer value) {
        if (value != null) {
            properties.put(key, value);
        }
    }
}
