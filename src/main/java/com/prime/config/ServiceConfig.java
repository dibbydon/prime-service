package com.prime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prime.service.PrimeNumberGeneratorService;
import com.prime.service.PrimeNumberGeneratorServiceImpl;

@Configuration
public class ServiceConfig {
	
	@Bean
	public PrimeNumberGeneratorService primeNumberGeneratorService() {
		return new PrimeNumberGeneratorServiceImpl();
	}

}
