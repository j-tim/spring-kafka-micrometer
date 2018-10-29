package nl.jtim.spring.kafka.consumer;

import nl.jtim.spring.kafka.producer.randommessage.Message;
import nl.jtim.spring.kafka.consumer.config.KafkaConsumerConfig;
import nl.jtim.spring.kafka.consumer.config.KafkaTestConfiguration;
import nl.jtim.spring.kafka.consumer.producer.MessageTestProducer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DirtiesContext

// Spring Kafka 2.0
//@EmbeddedKafka(partitions = 1,
//  topics = {
//    KafkaConsumerConfig.USER_MESSAGES_TOPIC_NAME,
//     })
@SpringBootTest(classes = KafkaTestConfiguration.class)
public class MessageConsumerTest {

     @ClassRule
     public static KafkaEmbedded kafkaEmbedded =
       new KafkaEmbedded(1, true, 1, KafkaConsumerConfig.USER_MESSAGES_TOPIC_NAME);

//     @Autowired
//     private KafkaEmbedded embeddedKafka;

     @Autowired
     private MessageTestProducer messageTestProducer;

     @Test
     public void name() {
          messageTestProducer.send(new Message("Message 1", "tim"));
          messageTestProducer.send(new Message("Message 2", "tim"));
          messageTestProducer.send(new Message("Message 3", "tim"));
          messageTestProducer.send(new Message("Message 4", "tim"));
          messageTestProducer.send(new Message("Message 5", "tim"));
     }
}