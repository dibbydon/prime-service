/**
 * 
 */
package com.prime.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.prime.exception.BadInputException;
import com.prime.model.PrimeResult;

/**
 * @author dibbydon
 *
 */
public class PrimeNumberGeneratorServiceImpl implements PrimeNumberGeneratorService {

	
	public PrimeResult generatePrimeNumbers(Integer limit) throws BadInputException {
		List<Integer> rangeToLimit = IntStream.rangeClosed(2, limit).boxed().collect(Collectors.toList());
		if (limit < 2) {
			throw new BadInputException("input must be greater than 2");
		} else {
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
		}
		return  new PrimeResult(limit, rangeToLimit);
	}

}
