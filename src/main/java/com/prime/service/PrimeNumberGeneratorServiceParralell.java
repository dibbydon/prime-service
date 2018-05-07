package com.prime.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prime.exception.BadInputException;
import com.prime.model.PrimeResult;

/**
 * @author dibbydon
 * Implementation of a Parallel Algorithm for finding 
 * prime number within a specific limit.
 */

public class PrimeNumberGeneratorServiceParralell implements PrimeNumberGeneratorService {
	private static Logger log = LoggerFactory.getLogger(PrimeNumberGeneratorServiceParralell.class);
	@Override
	public PrimeResult generatePrimeNumbers(Integer limit) throws BadInputException {
		if(limit < 2) {
			log.error("input must be greater than 2");
		    throw new BadInputException("input must be greater than 2");
		}
		
		List<Integer> primes =  IntStream.rangeClosed(2, limit)
				                         .parallel()
		                                 .filter(this::isPrime)
		                                 .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		
		return new PrimeResult(limit, primes);
	}
	
	Boolean isPrime(Integer value) {
		return value > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(value)).boxed().noneMatch(divisor -> value % divisor == 0);
	}

}
