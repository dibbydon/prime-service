package com.prime.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
@XmlRootElement
public final class PrimeResult {
	@JsonProperty("Initial")
    private Integer initial;
	@JsonProperty("Primes")
    private List<Integer> primes;
    
	public PrimeResult(){
		super();
	}
	
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
