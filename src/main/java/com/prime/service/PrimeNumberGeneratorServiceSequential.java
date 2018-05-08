/**
 * 
 */
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
 * @author dibbydon
 * Implementation of the Sequential Algorithm for finding 
 * prime number within a specific limit.
 */
public class PrimeNumberGeneratorServiceSequential implements PrimeNumberGeneratorService {
	private static Logger log = LoggerFactory.getLogger(PrimeNumberGeneratorServiceSequential.class);
	
	public PrimeResult generatePrimeNumbers(Integer limit) throws InvalidInputException {
		List<Integer> rangeToLimit = new ArrayList<>();
		if (limit < 2) {
			log.error("input must be greater than or equal to 2");
			throw new InvalidInputException("input must be greater than or equal to 2");
		}
		
		boolean []range = new boolean[limit + 1];
		Consumer<Integer> erastosthenes = (e) -> {
			int count = 0;
			Integer value = (int) (Math.pow(e, 2) + count * e);
			while (value <= limit) {
				if(!range[value]) {
					range[value] = true;
				}
				count++;
				value = (int) (Math.pow(e, 2) + count * e);
			}
		};
		
		Integer pivot = (int)Math.sqrt(limit);
		
		IntStream.rangeClosed(2, pivot).boxed()
		                               .forEach(erastosthenes);
		
		for (int i = 2; i < range.length; i++) {
			if(range[i] == false) {
				rangeToLimit.add(i);
			}
		}
		
		return  new PrimeResult(limit, rangeToLimit);
	}

}
