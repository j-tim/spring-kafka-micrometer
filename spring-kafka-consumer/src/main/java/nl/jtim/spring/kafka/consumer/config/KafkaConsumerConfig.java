package nl.jtim.spring.kafka.consumer.config;

import nl.jtim.spring.kafka.producer.randommessage.Message;
import nl.jtim.spring.kafka.consumer.MessageConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConsumerConfig {

  public static final String USER_MESSAGES_TOPIC_NAME = "hello-world-messages";

  @Autowired
  private KafkaProperties kafkaProperties;

  @Bean
  public ConsumerFactory<String, Message> kafkaConsumerFactory() {
    Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties();
    DefaultKafkaConsumerFactory<String, Message> stringMessageDefaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumerProperties, new StringDeserializer(), new JsonDeserializer<>(Message.class));
    return stringMessageDefaultKafkaConsumerFactory;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(kafkaConsumerFactory());
    factory.setConcurrency(3);
    return factory;
  }

  @Bean
  public MessageConsumer messageConsumer() {
    return new MessageConsumer();
  }

}