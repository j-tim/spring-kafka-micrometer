package nl.jtim.spring.kafka.consumer.producer;

import lombok.extern.slf4j.Slf4j;
import nl.jtim.spring.kafka.producer.randommessage.Message;
import nl.jtim.spring.kafka.consumer.config.KafkaConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class MessageTestProducer {

  @Autowired
  private KafkaTemplate<String, Message> template;

  public void send(Message message) {
    log.info("Produce message to Kafka topic: {}. Value: {}", KafkaConsumerConfig.USER_MESSAGES_TOPIC_NAME, message);

    template.send(KafkaConsumerConfig.USER_MESSAGES_TOPIC_NAME, message);
  }
}
