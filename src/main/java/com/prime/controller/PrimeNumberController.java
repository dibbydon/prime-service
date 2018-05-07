package com.prime.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prime.exception.BadInputException;
import com.prime.model.PrimeResult;
import com.prime.service.PrimeNumberGeneratorFactory;
import com.prime.service.PrimeNumberGeneratorService;

@RestController
public class PrimeNumberController {
    
	
	
	@GetMapping(value = "/primes/{limit}", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> getPrimeNumbers(@PathVariable("limit") Integer limit) throws BadInputException {
		PrimeNumberGeneratorFactory primeFactory = new PrimeNumberGeneratorFactory();
		PrimeNumberGeneratorService primeNumberService = primeFactory.getPrimeNumberGenerator();
		
		PrimeResult result = primeNumberService.generatePrimeNumbers(limit);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setCacheControl(CacheControl.maxAge(1000, TimeUnit.MILLISECONDS)
				                                    .sMaxAge(1000, TimeUnit.MILLISECONDS)
				                                    .cachePublic());
		return new ResponseEntity<> (result, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/primes/{limit}",  params= {"algo"}, produces = { "application/json", "application/xml" })
	public ResponseEntity<?> getPrimeNumbers(@PathVariable("limit") Integer limit, @RequestParam("algo") String algorithm) throws BadInputException {
		PrimeNumberGeneratorFactory primeFactory = new PrimeNumberGeneratorFactory(algorithm);
		PrimeNumberGeneratorService primeNumberService = primeFactory.getPrimeNumberGenerator();
		
		PrimeResult result = primeNumberService.generatePrimeNumbers(limit);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setCacheControl(CacheControl.maxAge(1000, TimeUnit.MILLISECONDS)
				                                    .sMaxAge(1000, TimeUnit.MILLISECONDS)
				                                    .cachePublic());
		return new ResponseEntity<> (result, responseHeaders, HttpStatus.OK);
	}
	
}
