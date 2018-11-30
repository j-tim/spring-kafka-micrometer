package nl.jtim.spring.kafka.producer.config;

import lombok.extern.slf4j.Slf4j;
import nl.jtim.spring.kafka.producer.randommessage.Message;
import nl.jtim.spring.kafka.producer.randommessage.RandomMessageKafkaProducer;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
@Slf4j
public class KafkaProducerConfig {

  public static final String TOPIC_NAME = "hello-world-messages";
  private static final int NUMBER_OF_PARTITIONS = 1;
  private static final short REPLICATION_FACTOR = (short) 1;

  private final KafkaProperties kafkaProperties;

  @Bean
  public KafkaAdmin admin() {
    log.info("Create KafkaAdmin");
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    return new KafkaAdmin(configs);
  }

  /**
   * If the broker supports it (1.0.0 or higher), the admin will increase the number of partitions if it is found that
   * an existing topic has fewer partitions than the NewTopic.numPartitions
   */
  @Bean
  public NewTopic userMessageTopic() {
    log.info("About to create topic: {} ", TOPIC_NAME);
    return new NewTopic(TOPIC_NAME, NUMBER_OF_PARTITIONS, REPLICATION_FACTOR);
  }

  @Autowired
  public KafkaProducerConfig(KafkaProperties kafkaProperties) {
    this.kafkaProperties = kafkaProperties;
  }

  @Bean
  public ProducerFactory<String, Message> producerFactory() {
    Map<String, Object> producerProperties = kafkaProperties.buildProducerProperties();
    return new DefaultKafkaProducerFactory<>(producerProperties);
  }

  @Bean
  public KafkaTemplate<String, Message> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public RandomMessageKafkaProducer messageProducer() {
    return new RandomMessageKafkaProducer();
  }
}