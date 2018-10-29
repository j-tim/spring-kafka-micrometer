package nl.jtim.spring.kafka.producer.randommessage;

import lombok.Data;

import java.util.UUID;

@Data
public class Message {

  private String id;
  private String message;
  private String user;

  Message() {
  }

  public Message(String message, String user) {
    id = UUID.randomUUID().toString();
    this.message = message;
    this.user = user;
  }
}
