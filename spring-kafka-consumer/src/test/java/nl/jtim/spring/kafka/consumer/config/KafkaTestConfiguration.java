package nl.jtim.spring.kafka.consumer.config;

import nl.jtim.spring.kafka.producer.randommessage.Message;
import nl.jtim.spring.kafka.consumer.producer.MessageTestProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@TestConfiguration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaTestConfiguration {

  @Autowired
  private KafkaProperties kafkaProperties;

  @Bean
  public ProducerFactory<String, Message> producerFactory() {
    Map<String, Object> configProperties = new HashMap<>();
    configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(configProperties);
  }

  @Bean
  public KafkaTemplate<String, Message> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public MessageTestProducer messageProducer() {
    return new MessageTestProducer();
  }

}
