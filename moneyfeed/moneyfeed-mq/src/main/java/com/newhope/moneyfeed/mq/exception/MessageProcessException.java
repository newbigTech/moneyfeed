package com.newhope.moneyfeed.mq.exception;

public class MessageProcessException extends Exception {
	private static final long serialVersionUID = 1L;

	public MessageProcessException(String message) {
		super(message);
	}
	public MessageProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
