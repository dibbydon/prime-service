package com.prime.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrimeResult {
	@JsonProperty("Initial")
    private Integer initial;
	@JsonProperty("Primes")
    private List<Integer> primes;
    
    public PrimeResult() {
    	
    }
    
    public PrimeResult(Integer initial, List<Integer> primes){
    	this.initial = initial;
    	this.primes = primes;
    }
    
	public Integer getInitial() {
		return initial;
	}
	public void setInitial(Integer initial) {
		this.initial = initial;
	}
	public List<Integer> getPrimes() {
		return primes;
	}
	public void setPrimes(List<Integer> primes) {
		this.primes = primes;
	}
}
