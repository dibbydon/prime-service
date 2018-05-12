package com.prime.service.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prime.service.PrimeNumberGeneratorFactory;
import com.prime.service.PrimeNumberGeneratorService;
import com.prime.service.PrimeNumberGeneratorServiceParallel;
import com.prime.service.PrimeNumberGeneratorServiceSequential;

/**
 * 
 * @author dibbydon
 * a test to assert that a default when no option or option does not exist is provided 
 * the SequentialGeneratorProcess is used in calculation of prime numbers
 * when an option is given and it exist the factory returns the appropriate instance.
 * i.e 
 */
public class PrimeNumberGeneratorFactoryTest {
    
	private PrimeNumberGeneratorFactory factory;
		
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		factory = null;
	}
	
	@Test
	public void testGivenNoParameterFactoryReturns_EratosthenesSequentialGenerator() {
		factory = new PrimeNumberGeneratorFactory();
		PrimeNumberGeneratorService genService = factory.getPrimeNumberGenerator();
		
		assertThat(genService, instanceOf(PrimeNumberGeneratorServiceSequential.class));
	}

	@Test
	public void testGivenParameter_Option_FactoryReturns_EratosthenesParrallelGenerator() {
		factory = new PrimeNumberGeneratorFactory("pll");
		PrimeNumberGeneratorService genService = factory.getPrimeNumberGenerator();
		
		assertThat(genService, instanceOf(PrimeNumberGeneratorServiceParallel.class));
	}
	

}
