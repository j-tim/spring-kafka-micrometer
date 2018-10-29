package nl.jtim.spring.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class KafkaController {

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Autowired
    public KafkaController(KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry) {
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
    }

    @GetMapping("/showRawKafkaMetrics")
    public String showMetrics() {
        for (MessageListenerContainer listenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
            Map<String, Map<MetricName, ? extends Metric>> metrics = listenerContainer.metrics();
            log.info("Kakfa metrics: {}", metrics);
        }
        return "Raw Kafka metrics logged";
    }
}
