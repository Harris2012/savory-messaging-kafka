package cn.savory.messaging.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author hc_zhang
 * @date 2019/2/21.
 */
public class KafkaProducerConfig extends KafkaConfig {

    public KafkaProducerConfig() {
    }

    public KafkaProducerConfig(String bootstrapServers) {
        super(bootstrapServers);
    }

    /**
     * key序列化指定类
     */
    private String keySerializerClass = StringSerializer.class.getName();

    /**
     * value序列化指定类
     */
    private String valueSerializerClass = StringSerializer.class.getName();

    /**
     * acks
     */
    private String acks;

    /**
     * batch.size
     */
    private Integer batchSize;

    /**
     * delivery.timeout.ms
     */
    private Integer deliveryTimeoutMs;

    /**
     * linger.ms
     */
    private String lingerMs;

    /**
     * buffer.memory
     */
    private Integer bufferMomory;

    public Properties toProperties() {

        Properties properties = new Properties();

        putIfSet(properties, ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.getBootstrapServers());
        putIfSet(properties, ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, this.getKeySerializerClass());
        putIfSet(properties, ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, this.getValueSerializerClass());

        putIfSet(properties, ProducerConfig.ACKS_CONFIG, this.getAcks());
        putIfSet(properties, ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, this.getDeliveryTimeoutMs());
        putIfSet(properties, ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, this.getRequestTimeoutMs());
        putIfSet(properties, ProducerConfig.BATCH_SIZE_CONFIG, this.getBatchSize());
        putIfSet(properties, ProducerConfig.LINGER_MS_CONFIG, this.getLingerMs());
        putIfSet(properties, ProducerConfig.BUFFER_MEMORY_CONFIG, this.getBufferMomory());

        return properties;
    }

    public String getKeySerializerClass() {
        return keySerializerClass;
    }

    public void setKeySerializerClass(String keySerializerClass) {
        this.keySerializerClass = keySerializerClass;
    }

    public String getValueSerializerClass() {
        return valueSerializerClass;
    }

    public void setValueSerializerClass(String valueSerializerClass) {
        this.valueSerializerClass = valueSerializerClass;
    }

    public String getAcks() {
        return acks;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Integer getDeliveryTimeoutMs() {
        return deliveryTimeoutMs;
    }

    public void setDeliveryTimeoutMs(Integer deliveryTimeoutMs) {
        this.deliveryTimeoutMs = deliveryTimeoutMs;
    }

    public String getLingerMs() {
        return lingerMs;
    }

    public void setLingerMs(String lingerMs) {
        this.lingerMs = lingerMs;
    }

    public Integer getBufferMomory() {
        return bufferMomory;
    }

    public void setBufferMomory(Integer bufferMomory) {
        this.bufferMomory = bufferMomory;
    }
}
