package com.prime.controller;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prime.exception.InvalidInputException;
import com.prime.model.PrimeResult;
import com.prime.service.PrimeNumberGeneratorFactory;
import com.prime.service.PrimeNumberGeneratorService;

/**
 * 
 * @author dibbydon
 * This class marks the entry point into the 
 * prime service api, it provides two get methods for 
 * handing default request and option to request an algorithm
 * for execution.
 *
 */
@RestController
public class PrimeNumberController {
	
	@GetMapping(value = "/primes/{limit}",  produces = { "application/json", "application/xml" })
	public ResponseEntity<?> getPrimeNumbers(@PathVariable("limit") Integer limit, @RequestParam(value = "algo", required = false) Optional<String> algorithm) throws InvalidInputException {
		PrimeNumberGeneratorFactory primeFactory;
		if (algorithm.isPresent()) {
		    primeFactory = new PrimeNumberGeneratorFactory(algorithm.get());
		} else {
			primeFactory = new PrimeNumberGeneratorFactory();
		}
		    
		PrimeNumberGeneratorService primeNumberService = primeFactory.getPrimeNumberGenerator();
		
		PrimeResult result = primeNumberService.generatePrimeNumbers(limit);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setCacheControl(CacheControl.maxAge(1000, TimeUnit.MILLISECONDS)
				                                    .sMaxAge(1000, TimeUnit.MILLISECONDS)
				                                    .cachePublic());
		return new ResponseEntity<> (result, responseHeaders, HttpStatus.OK);
	}
	
}
