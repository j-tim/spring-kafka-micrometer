package nl.jtim.spring.kafka.producer.randommessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component
@Slf4j
public class RandomMessagePublisher {

    private final RandonMessageKafkaProducer randonMessageKafkaProducer;
    private final RandomMessageGenerator randomMessageGenerator;

    private AtomicBoolean publishingToKafkaEnabled = new AtomicBoolean(false);

    @Autowired
    public RandomMessagePublisher(RandonMessageKafkaProducer randonMessageKafkaProducer, RandomMessageGenerator randomMessageGenerator) {
        this.randonMessageKafkaProducer = randonMessageKafkaProducer;
        this.randomMessageGenerator = randomMessageGenerator;
    }

    @Async
    public void start() {
        log.info("Start publishing random messages to Kafka");
        publishingToKafkaEnabled.set(true);
        while (publishingToKafkaEnabled.get()) {
            try {
                Thread.sleep(Duration.of(1, SECONDS).toMillis());
                Message message = randomMessageGenerator.generateRandomMessage();
                randonMessageKafkaProducer.send(message.getId(), message);
            } catch (InterruptedException e) {
                log.info("Async thread has been interrupted: {}", e.getMessage());
            }
        }
    }

    public void stop() {
        log.info("Stop publishing random messages to Kafka");
        publishingToKafkaEnabled.set(true);
    }
}
