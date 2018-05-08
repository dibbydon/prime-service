/**
 * 
 */
package com.prime.exception;

/**
 * @author dibbydon
 * This class marks exception that may be thrown when 
 * an invalid Input request
 */
public class InvalidInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidInputException(String message) {
		super(message);
	}

}
