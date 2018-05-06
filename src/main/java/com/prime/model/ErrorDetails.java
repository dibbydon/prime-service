package com.prime.model;

public final class ErrorDetails {
	private final String message;
	private final String additionalMessage;

	public ErrorDetails (String message, String additionalMessage) {
		this.message = message;
		this.additionalMessage = additionalMessage;
	}
	
	public String getMessage() {
		return message;
	}


	public String getAdditionalMessage() {
		return additionalMessage;
	}

}
