package cn.savory.messaging.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author hc_zhang
 * @date 2019/2/21.
 */
public class KafkaClientTest {

    private final static String topic = "topic001";
    private final static String bootstrapServer = "{ip}:9092";

    @Test
    public void enqueue() throws Throwable {

        KafkaProducerConfig kafkaConfig = new KafkaProducerConfig(bootstrapServer);

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaConfig.toProperties());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (producer != null) {
                producer.flush();
                producer.close();
            }
        }));

        for (int i = 0; i < 100; i++) {

            String key = UUID.randomUUID().toString();
            String message = String.format("this is test %d", i);

            producer.send(new ProducerRecord<String, String>(topic, key, message));

            Thread.sleep(1 * 1000);
        }
    }

    @Test
    public void dequeue() {

        String groupId = "group001";
        KafkaConsumerConfig kafkaConfig = new KafkaConsumerConfig(bootstrapServer, groupId);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaConfig.toProperties());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (consumer != null) {
                consumer.close();
            }
        }));

        int index = 0;
        Duration duration = Duration.ofSeconds(3);

        consumer.subscribe(Arrays.asList(topic));
        while (index < 100) {

            System.out.println("===== index = " + index + " =======");
            ConsumerRecords<String, String> items = consumer.poll(duration);
            for (ConsumerRecord<String, String> record : items) {
                System.out.println(record.key() + " = " + record.value());
            }
            consumer.commitSync();

            index++;
        }

    }


}