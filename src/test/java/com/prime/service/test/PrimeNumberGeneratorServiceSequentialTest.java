package com.prime.service.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prime.exception.InvalidInputException;
import com.prime.model.PrimeResult;
import com.prime.service.PrimeNumberGeneratorFactory;
import com.prime.service.PrimeNumberGeneratorService;
import com.prime.service.PrimeNumberGeneratorServiceSequential;

public class PrimeNumberGeneratorServiceSequentialTest {
    
	private PrimeNumberGeneratorFactory factory;
	private PrimeNumberGeneratorService generatorService;
	
	@Before
	public void setUp() {
		factory = new PrimeNumberGeneratorFactory();
		generatorService = factory.getPrimeNumberGenerator();
	}
	
	@After
	public void TearDown() {
		generatorService = null;
		factory = null;
	}
	
		
	@Test(expected=InvalidInputException.class)
	public void givenAnInvalidNumber_1_throwInvalidInputException() throws InvalidInputException {
		Integer limit = 1;
		generatorService.generatePrimeNumbers(limit);
	}
	
	@Test
	public void givenNumber_2_Return_1_PrimeNumber() throws InvalidInputException {
		Integer limit = 2;
		PrimeResult result = generatorService.generatePrimeNumbers(limit);
		assertTrue("expected value not returned", result.getInitial() == 2);
		assertTrue("expected size not returned", result.getPrimes().size() == 1);
		assertTrue("expected value not returned",result.getPrimes().contains(2));
		
		assertThat(generatorService, instanceOf(PrimeNumberGeneratorServiceSequential.class));
	}
	
	@Test
	public void givenAnInput_3_Return_2_PrimeNumber() throws InvalidInputException {
		Integer limit = 3;
		PrimeResult result = generatorService.generatePrimeNumbers(limit);
		assertNotNull("expected value not returned", result);
		assertTrue("expected value not returned", result.getInitial() == 3);
		assertTrue("expected size not returned", result.getPrimes().size() == 2);
		assertTrue("expected value not returned",result.getPrimes().contains(2));
		assertTrue("expected value not returned",result.getPrimes().contains(3));
	}
	
	@Test
	public void givenInput_10_Return_4_PrimeNumber() throws InvalidInputException {
		Integer limit = 10;
		PrimeResult result = generatorService.generatePrimeNumbers(limit);
		assertTrue("expected value not returned", result.getInitial() == 10);
		assertTrue("expected size not returned", result.getPrimes().size() == 4);
		assertTrue("expected value not returned", result.getPrimes().contains(2));
		assertTrue("expected value not returned", result.getPrimes().contains(3));
		assertTrue("expected value not returned", result.getPrimes().contains(5));
		assertTrue("expected value not returned", result.getPrimes().contains(7));
	}
	
	@Test
	public void givenInput_100_Return_25_PrimeNumber() throws InvalidInputException {
		Integer upperBound = 100;
		PrimeResult result = generatorService.generatePrimeNumbers(upperBound);
		assertTrue("expected value not returned", result.getInitial() == 100);
		assertTrue("expected size not returned", result.getPrimes().size() == 25);
		assertTrue("expected value not returned", result.getPrimes().contains(2));
		assertTrue("expected value not returned", result.getPrimes().contains(37));
		assertTrue("expected value not returned", result.getPrimes().contains(53));
		assertTrue("expected value not returned", result.getPrimes().contains(97));
	}
	
	@Test
	public void givenInput_1000_Return_168_PrimeNumber() throws InvalidInputException {
		Integer upperBound = 1000;
		PrimeResult result = generatorService.generatePrimeNumbers(upperBound);
		assertTrue("expected value not returned", result.getInitial() == 1000);
		assertTrue("expected size not returned", result.getPrimes().size() == 168);
		assertTrue("expected value not returned", result.getPrimes().contains(2));
		assertTrue("expected value not returned", result.getPrimes().contains(701));
		assertTrue("expected value not returned", result.getPrimes().contains(701));
		assertTrue("expected value not returned", result.getPrimes().contains(997));
	}
	
	@Test
	public void givenInput_11_Return_5_PrimeNumber() throws InvalidInputException {
		Integer upperBound = 11;
		PrimeResult result = generatorService.generatePrimeNumbers(upperBound);
		assertTrue("expected value not returned", result.getInitial() == 11);
		assertTrue("expected size not returned", result.getPrimes().size() == 5);
		assertTrue("expected value not returned", result.getPrimes().contains(2));
		assertTrue("expected value not returned", result.getPrimes().contains(3));
		assertTrue("expected value not returned", result.getPrimes().contains(5));
		assertTrue("expected value not returned", result.getPrimes().contains(7));
		assertTrue("expected value not returned", result.getPrimes().contains(11));
	}
	
	@Test(timeout= 3000)
	public void givenInput_10000000_assertReturnIn3milisecond() throws InvalidInputException {
		Integer upperBound = 10000000;
		PrimeResult result = generatorService.generatePrimeNumbers(upperBound);
	}
	
	
}
