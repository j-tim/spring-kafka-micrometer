package nl.jtim.spring.kafka.producer.randommessage;

import lombok.Data;

@Data
public class Message {

  private String id;
  private String message;
  private String user;

  Message() {
  }

  public Message(String message, String user) {
    this.message = message;
    this.user = user;
  }
}
