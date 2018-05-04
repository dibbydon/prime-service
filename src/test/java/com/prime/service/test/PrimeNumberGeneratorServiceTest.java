package com.prime.service.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prime.exception.InvalidInputException;
import com.prime.service.PrimeNumberGeneratorService;
import com.prime.service.PrimeNumberGeneratorServiceImpl;

public class PrimeNumberGeneratorServiceTest {
    
	
	private PrimeNumberGeneratorService generatorService;
	
	@Before
	public void setUp() {
		generatorService = new PrimeNumberGeneratorServiceImpl();
	}
	
	@After
	public void TearDown() {
		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void givenAnInvalidNumber_1_throwInvalidInputException() throws InvalidInputException {
		Integer limit = 1;
		generatorService.generatePrimeNumbers(limit);
	}
	
	@Test(expected=InvalidInputException.class)
	public void ivenAnInvalidNumber_2_throwInvalidInputException() throws InvalidInputException {
		Integer limit = 2;
		generatorService.generatePrimeNumbers(limit);
	}
	
	@Test
	public void givenAnInput_3_Return_2_PrimeNumber() throws InvalidInputException {
		Integer limit = 3;
		List<Integer> result = generatorService.generatePrimeNumbers(limit);
		assertNotNull("expected value not returned", result);
		assertTrue("expected size not returned", result.size() == 1);
		assertTrue("expected value not returned",result.contains(2));
	}
	
	@Test
	public void givenInput_10_Return_4_PrimeNumber() throws InvalidInputException {
		Integer limit = 10;
		List<Integer> result = generatorService.generatePrimeNumbers(limit);
		assertTrue("expected size not returned", result.size() == 4);
		assertTrue("expected value not returned", result.contains(2));
		assertTrue("expected value not returned", result.contains(3));
		assertTrue("expected value not returned", result.contains(5));
		assertTrue("expected value not returned", result.contains(7));
	}
	
	@Test
	public void givenInput_100_Return_25_PrimeNumber() throws InvalidInputException {
		Integer upperBound = 100;
		List<Integer> result = generatorService.generatePrimeNumbers(upperBound);
		assertTrue("expected size not returned", result.size() == 25);
		assertTrue("expected value not returned", result.contains(2));
		assertTrue("expected value not returned", result.contains(37));
		assertTrue("expected value not returned", result.contains(53));
		assertTrue("expected value not returned", result.contains(97));
	}
	
	@Test
	public void givenInput_1000_Return_168_PrimeNumber() throws InvalidInputException {
		Integer upperBound = 1000;
		List<Integer> result = generatorService.generatePrimeNumbers(upperBound);
		assertTrue("expected size not returned", result.size() == 168);
		assertTrue("expected value not returned", result.contains(2));
		assertTrue("expected value not returned", result.contains(701));
		assertTrue("expected value not returned", result.contains(701));
		assertTrue("expected value not returned", result.contains(997));
	}
	
}
