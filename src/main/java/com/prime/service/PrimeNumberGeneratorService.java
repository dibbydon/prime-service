package com.prime.service;

import com.prime.exception.InvalidInputException;
import com.prime.model.PrimeResult;

/**
 * @author dibbydon
 * an interface specfying the contract for all prime generators.
 */
public interface PrimeNumberGeneratorService {
	
	/**
	 * method takes in a number limit and returns prime numbers between 2 (inclusive) to limit (exclusive)
	 */
	PrimeResult generatePrimeNumbers(Integer limit) throws InvalidInputException;
}
