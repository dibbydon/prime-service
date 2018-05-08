package com.prime.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public final class PrimeResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("Initial")
	@JacksonXmlProperty(localName="Initial")
    private Integer initial;
	
	@JsonProperty("Primes")
	@JacksonXmlProperty(localName="Primes")
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
