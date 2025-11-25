package com.example;

import com.google.cloud.functions.CloudEventsFunction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.cloudevents.CloudEvent;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * A Cloud Function that is triggered by a Pub/Sub message and logs its content.
 */
public class PubSubFunction implements CloudEventsFunction {
  private static final Logger logger = Logger.getLogger(PubSubFunction.class.getName());
  private static final Gson gson = new Gson();

  /**
   * This method is called for every new Pub/Sub message.
   *
   * @param event The CloudEvent containing the Pub/Sub message.
   */
  @Override
  public void accept(CloudEvent event) {
    if (event.getData() == null) {
      logger.warning("Received CloudEvent with no data");
      return;
    }

    // The Pub/Sub message is in the data field of the CloudEvent, encoded as a JSON string.
    String cloudEventData = new String(event.getData().toBytes(), StandardCharsets.UTF_8);

    try {
      // Parse the JSON string to get the message object.
      JsonObject data = gson.fromJson(cloudEventData, JsonObject.class);
      if (data == null || !data.has("message")) {
        logger.warning("Invalid Pub/Sub message format: 'message' field is missing.");
        return;
      }
      JsonObject message = data.getAsJsonObject("message");

      // The actual message content is in the 'data' field of the message, Base64-encoded.
      if (message == null || !message.has("data")) {
        logger.warning("Invalid Pub/Sub message format: 'data' field is missing.");
        return;
      }
      String encodedData = message.get("data").getAsString();

      // Decode the Base64-encoded data.
      String decodedData = new String(Base64.getDecoder().decode(encodedData), StandardCharsets.UTF_8);

      logger.info("Received Pub/Sub message: " + decodedData);

    } catch (Exception e) {
      logger.severe("Error processing Pub/Sub message: " + e.getMessage());
    }
  }
}
