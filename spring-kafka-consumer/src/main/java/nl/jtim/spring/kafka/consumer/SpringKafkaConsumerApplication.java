package nl.jtim.spring.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SpringKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaConsumerApplication.class, args);
	}
}
