package com.prime.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.prime.exception.BadInputException;

import com.prime.model.ErrorDetails;

@ControllerAdvice
public class PrimeControllerExceptionHandler {
   
	@ExceptionHandler(BadInputException.class)
	public ResponseEntity<?> exception(Exception ex){
	   ErrorDetails errorDetails = new ErrorDetails();
	   errorDetails.setMessage(ex.getMessage());
	   
	   StringWriter stringWriter = new StringWriter();
	   ex.printStackTrace(new PrintWriter(stringWriter));
	   
	   errorDetails.setAdditionalMessage(stringWriter.toString());
	   return new ResponseEntity<> (errorDetails, HttpStatus.BAD_REQUEST);
	   
   }
   
   
}
