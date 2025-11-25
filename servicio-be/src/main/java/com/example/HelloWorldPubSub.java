package com.example;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * A "Hello World" function that is triggered by a Pub/Sub message.
 */
public class HelloWorldPubSub implements BackgroundFunction<HelloWorldPubSub.PubSubMessage> {

  private static final Logger logger = Logger.getLogger(HelloWorldPubSub.class.getName());

  @Override
  public void accept(PubSubMessage message, Context context) {
    String name = "World";
    if (message != null && message.data != null) {
      name = new String(Base64.getDecoder().decode(message.data), StandardCharsets.UTF_8);
    }
    logger.info("Hello, " + name + "!");
  }

  /**
   * The Pub/Sub message payload.
   */
  public static class PubSubMessage {
    public String data;
    public java.util.Map<String, String> attributes;
    public String messageId;
    public String publishTime;
  }
}
