package com.prime.model;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PrimeResult {
	@JsonProperty("Initial")
    private final Integer initial;
	@JsonProperty("Primes")
    private final List<Integer> primes;
    
    public PrimeResult(Integer initial, List<Integer> primes){
    	this.initial = initial;
    	this.primes = primes;
    }
    
	public Integer getInitial() {
		return initial;
	}
	
	public List<Integer> getPrimes() {
		return Collections.unmodifiableList(primes);
	}
	
}
