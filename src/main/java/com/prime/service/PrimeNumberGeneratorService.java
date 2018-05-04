package com.prime.service;

import java.util.List;

import com.prime.exception.InvalidInputException;

/**
 * @author dibbydon
 *
 */
public interface PrimeNumberGeneratorService {
	
	/**
	 * method takes in a number limit and returns prime numbers between 2 (inclusive) to limit (exclusive)
	 */
	List<Integer> generatePrimeNumbers(Integer limit) throws InvalidInputException;
}
