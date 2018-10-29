package nl.jtim.spring.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import nl.jtim.spring.kafka.producer.randommessage.RandomMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
@Slf4j
@EnableKafka
@EnableAsync
public class SpringKafkaProducerApplication {

	private final RandomMessagePublisher randomMessagePublisher;

	private final KafkaAdmin kafkaAdmin;

	@Autowired
	public SpringKafkaProducerApplication(RandomMessagePublisher randomMessagePublisher, KafkaAdmin kafkaAdmin) {
		this.randomMessagePublisher = randomMessagePublisher;
		this.kafkaAdmin = kafkaAdmin;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaProducerApplication.class, args);
	}

	@PostConstruct
	void started() {
		log.info("Application initialized");

		kafkaAdmin.getConfig();

		randomMessagePublisher.start();
	}

	@PreDestroy
	void shutDown() {
		log.info("Application about to stop");
		randomMessagePublisher.stop();
	}
}
