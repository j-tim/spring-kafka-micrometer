package nl.jtim.spring.kafka.producer.randommessage;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
class RandomMessageGenerator {

  private String names[] = {"Tim", "Claudia", "Tess", "Maud", "Mike", "Roy"};
  private String[] messages = {"Cowboy", "World", "Foo", "Bar", "Duck", "Coder"};

  Message generateRandomMessage() {
    return new Message(generateRandomMessageString(), pickRandomName());
  }

  private String pickRandomName() {
    return names[new Random().nextInt(names.length)];
  }

  private String generateRandomMessageString() {
    return "Hello " + messages[new Random().nextInt(messages.length)];
  }
}
