package com.prime.service.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prime.service.PrimeNumberGeneratorFactory;
import com.prime.service.PrimeNumberGeneratorService;
import com.prime.service.PrimeNumberGeneratorServiceParralell;
import com.prime.service.PrimeNumberGeneratorServiceSequential;

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
	public void testGivenNoParameterFactoryReturns_EratosthenesGenerator() {
		factory = new PrimeNumberGeneratorFactory();
		PrimeNumberGeneratorService genService = factory.getPrimeNumberGenerator();
		
		assertThat(genService, instanceOf(PrimeNumberGeneratorServiceSequential.class));
	}

	@Test
	public void testGivenParameter_Option_FactoryReturns_AtkinsGenerator() {
		factory = new PrimeNumberGeneratorFactory("pll");
		PrimeNumberGeneratorService genService = factory.getPrimeNumberGenerator();
		
		assertThat(genService, instanceOf(PrimeNumberGeneratorServiceParralell.class));
	}
	

}
