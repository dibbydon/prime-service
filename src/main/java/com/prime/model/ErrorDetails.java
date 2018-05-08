package com.prime.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public final class ErrorDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty
	private final String message;
	
	@JsonProperty
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
