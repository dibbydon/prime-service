/**
 * 
 */
package com.prime.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prime.exception.BadInputException;
import com.prime.model.PrimeResult;

/**
 * @author dibbydon
 * Implementation of the Sequential Algorithm for finding 
 * prime number within a specific limit.
 */
public class PrimeNumberGeneratorServiceSequential implements PrimeNumberGeneratorService {
	private static Logger log = LoggerFactory.getLogger(PrimeNumberGeneratorServiceSequential.class);
	
	public PrimeResult generatePrimeNumbers(Integer limit) throws BadInputException {
		List<Integer> rangeToLimit;
		if (limit < 2) {
			log.error("input must be greater than 2");
			throw new BadInputException("input must be greater than 2");
		}
		
		rangeToLimit = IntStream.rangeClosed(2, limit).boxed().collect(Collectors.toList());
		Integer pivot = (int)Math.sqrt(limit);
		
		IntStream.rangeClosed(2, pivot).boxed()
		                               .forEach( p -> { 
								                          int count = 0;
								                          Integer value = (int) (Math.pow (p, 2) + count * p) ;
								                          while (value <= limit) {
								                        	  rangeToLimit.remove(value);
								                        	  count++;
								                              value = (int) (Math.pow(p, 2) + count * p) ;
				                                      }
		                        });
		
		return  new PrimeResult(limit, rangeToLimit);
	}

}
