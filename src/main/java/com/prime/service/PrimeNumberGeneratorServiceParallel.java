package com.prime.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prime.exception.InvalidInputException;
import com.prime.model.PrimeResult;

/**
 * 
 * @author dibbydon
 * This class attempt to provides an implementation of the Sieve of Erastothenes algorithm 
 * for finding prime number within a specific limit with a parrallel execution.
 * 
 */

public class PrimeNumberGeneratorServiceParallel implements PrimeNumberGeneratorService {
	private static Logger log = LoggerFactory.getLogger(PrimeNumberGeneratorServiceParallel.class);
	@Override
	public PrimeResult generatePrimeNumbers(Integer limit) throws InvalidInputException {
		List<Integer> primes = new ArrayList<>();
		if(limit < 2) {
			log.error("input must be greater than or equal to 2");
		    throw new InvalidInputException("input must be greater than or equal to 2");
		}
		
		boolean []range = new boolean[limit + 1];
		
		Consumer<Integer> erastosthenes = (e) -> {
			int count = 0;
			Integer value = (int) (Math.pow(e, 2) + count * e);
			while (value <= limit) {
				if (!range[value]) {
					range[value] = true;
				}
				count++;
				value = (int) (Math.pow(e, 2) + count * e);
			}
		};
		
		Integer pivot = (int)Math.sqrt(limit);
		
		IntStream.rangeClosed(2, pivot).boxed()
									   .parallel()
		                               .forEach(erastosthenes);
		
		for (int i = 2; i < range.length; i++) {
			if(!range[i]) {
				primes.add(i);
			}
		}
		
		return new PrimeResult(limit, primes);
	}
	

}
