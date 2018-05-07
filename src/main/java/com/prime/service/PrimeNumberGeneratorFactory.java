package com.prime.service;

/**
 * 
 * @author dibbydon
 *
 */
public class PrimeNumberGeneratorFactory {
    
	private String genType;
	
	public PrimeNumberGeneratorFactory() {
		
	}
	
	public PrimeNumberGeneratorFactory(String genType) {
		this.genType = genType;
	}
	
	/**
	 * 
	 * @return return a new instance of the specified prime generator service class
	 */
	public PrimeNumberGeneratorService getPrimeNumberGenerator() {
		if ("pll".equals(genType)) {
			return new PrimeNumberGeneratorServiceParralell();
		} else 
			return new PrimeNumberGeneratorServiceSequential();
		
	}
}
