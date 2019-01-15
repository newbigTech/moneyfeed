package com.newhope.moneyfeed.mq.receiver;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.springframework.amqp.core.Message;


public abstract class MessageReceiver {
	public static final String TRACKING_ID = "trackingId";

	protected void logMessage(Message message, Logger logger) {
		if (message.getMessageProperties().getHeaders().containsKey(TRACKING_ID)) {
			String tid = (String) message.getMessageProperties().getHeaders().get(TRACKING_ID);
			logger.info("Message Tracking ID: " + TRACKING_ID + "=" + tid + " ");
		}
		
		// log the correlation ID if it exists
		if (message.getMessageProperties().getCorrelationId() != null) {
			try {
				String correlationId = new String(message.getMessageProperties().getCorrelationId(), "UTF-8");
				logger.info("Message Correlation ID: " + correlationId);
			} catch (UnsupportedEncodingException e) {
				logger.error("Unable to process correlation identifer \n" + e.getMessage());
			}
		}
		// log the message body
		String body = null;
		try {
			body = new String(message.getBody(), StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			logger.error("Unable to parse message body /n" + e.getMessage());
		}
		logger.info("Received Message: \n" + body);
	}
}