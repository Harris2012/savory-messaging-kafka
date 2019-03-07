package cn.savory.messaging.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * @author hc_zhang
 * @date 2019/2/21.
 */
public class KafkaConsumerConfig extends KafkaConfig {

    public KafkaConsumerConfig() {
    }

    public KafkaConsumerConfig(String bootstrapServers) {
        super(bootstrapServers);
    }

    public KafkaConsumerConfig(String bootstrapServers, String groupId) {
        super(bootstrapServers);
        this.groupId = groupId;
    }

    /**
     * ConsumerConfig.GROUP_ID_CONFIG
     */
    private String groupId;

    /**
     * key序列化指定类
     */
    private String keyDeserializerClass = StringDeserializer.class.getName();

    /**
     * value序列化指定类
     */
    private String valueDeserializerClass = StringDeserializer.class.getName();

    public Properties toProperties() {

        Properties properties = new Properties();

        putIfSet(properties, ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.getBootstrapServers());
        putIfSet(properties, ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, this.getKeyDeserializerClass());
        putIfSet(properties, ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, this.getKeyDeserializerClass());

        putIfSet(properties, ConsumerConfig.GROUP_ID_CONFIG, this.getGroupId());

        putIfSet(properties, ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, this.getRequestTimeoutMs());

        return properties;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getKeyDeserializerClass() {
        return keyDeserializerClass;
    }

    public void setKeyDeserializerClass(String keyDeserializerClass) {
        this.keyDeserializerClass = keyDeserializerClass;
    }

    public String getValueDeserializerClass() {
        return valueDeserializerClass;
    }

    public void setValueDeserializerClass(String valueDeserializerClass) {
        this.valueDeserializerClass = valueDeserializerClass;
    }
}
