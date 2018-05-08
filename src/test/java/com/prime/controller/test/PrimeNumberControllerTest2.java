package com.prime.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prime.controller.PrimeControllerExceptionHandler;
import com.prime.controller.PrimeNumberController;

/**
 * 
 * @author dibbydon
 *
 * A test to verify controller would accept application/xml request
 * and provide application/xml response
 */
public class PrimeNumberControllerTest2 {
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private PrimeNumberController primeNumbercontroller = new PrimeNumberController();
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(primeNumbercontroller)
				.setControllerAdvice(new PrimeControllerExceptionHandler())
                .defaultRequest(get("/")
		        .accept(MediaType.APPLICATION_XML))
                .build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGivenAnXmlRequestThenReturnAnXmlResponse() throws Exception {
        Integer limit = 3;
        
		mockMvc.perform(get("/primes/{limit}", limit))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_XML));
	}

}
