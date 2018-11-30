package nl.jtim.spring.kafka.producer.randommessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import static nl.jtim.spring.kafka.producer.config.KafkaProducerConfig.TOPIC_NAME;

@Slf4j
public class RandomMessageKafkaProducer {

    @Autowired
    private KafkaTemplate<String, Message> template;

    void send(String key, Message message) {
        log.info("Thread: {}. Produce message to Kafka topic: {}. Key: {} Value: {}", Thread.currentThread().getName(), TOPIC_NAME, key, message);

        template.send(TOPIC_NAME, message);
    }
}
