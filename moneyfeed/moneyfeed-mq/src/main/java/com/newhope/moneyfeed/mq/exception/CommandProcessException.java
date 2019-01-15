package com.newhope.moneyfeed.mq.exception;

public class CommandProcessException extends Exception {
	private static final long serialVersionUID = 1L;

	public CommandProcessException(String message) {
		super(message);
	}
	
	public CommandProcessException(String message, Throwable cause) {
		super(message, cause);
	}
}
