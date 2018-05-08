package com.prime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public final class ErrorDetails {
	@JsonProperty
	@JacksonXmlProperty
	private final String message;
	
	@JsonProperty
	@JacksonXmlProperty
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
