package com.prime.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.prime.exception.InvalidInputException;

import com.prime.model.ErrorDetails;

/**
 * 
 * @author dibbydon
 *  generic exception handler class for controllers
 * 
 */

@ControllerAdvice
public class PrimeControllerExceptionHandler {
	private static Logger log = LoggerFactory.getLogger(PrimeControllerExceptionHandler.class);
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<?> exception(Exception ex){
	   StringWriter stringWriter = new StringWriter();
	   ex.printStackTrace(new PrintWriter(stringWriter));
	   
	   ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), stringWriter.toString());
	   log.error(ex.getMessage());
	   return new ResponseEntity<> (errorDetails, HttpStatus.BAD_REQUEST);
	   
   }
   
   
}
