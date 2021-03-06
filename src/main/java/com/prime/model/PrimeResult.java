package com.prime.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public final class PrimeResult implements Serializable{
	/**
	 * @author dibbydon
	 * this class provide the model object for primes
	 * having the limit and the list of primes as variable
	 */
	private static final long serialVersionUID = 1L;

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
