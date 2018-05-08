package com.prime.controller.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.prime.controller.PrimeControllerExceptionHandler;
import com.prime.controller.PrimeNumberController;
import com.prime.exception.InvalidInputException;

/**
 * 
 * @author dibbydon
 * A test showing controller would accept application/json requests 
 * and provide response as application/json
 */
public class PrimeNumberControllerTest {
	
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
		        .accept(MediaType.APPLICATION_JSON_UTF8))
                .build();
	}

	@After
	public void tearDown() throws Exception {
		primeNumbercontroller = null;
	}

	@Test
	public void testPrimeNumberServiceInteraction() throws InvalidInputException {
		Integer limit = 3;
				
		ResponseEntity<?> response = primeNumbercontroller.getPrimeNumbers(limit);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
	}
	
	@Test
	public void testMockMvcPrimeNumberServicewithDefaultParameter() throws Exception {
		Integer limit = 3;
		
		mockMvc.perform(get("/primes/{limit}", limit))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(jsonPath("$.Initial", is(3)))
		       .andExpect(jsonPath("$.Primes", hasItems(2,3)));
		
		
	}
	
	@Test
	public void testMockMvcPrimeNumberServicewithInvalidInputAndExpectErrorMessage() throws Exception {
		Integer limit = 1;
		
		mockMvc.perform(get("/primes/{limit}", limit))
		       .andExpect(status().isBadRequest())
		       .andExpect(jsonPath("$.message").value("input must be greater than or equal to 2"));
	}
	
	@Test
	public void testMockMvcPrimeNumberOptionalAlgoParameter() throws Exception {
		Integer limit = 3;
		
		mockMvc.perform(get("/primes/{limit}?algo=pll", limit))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(jsonPath("$.Initial", is(3)))
		       .andExpect(jsonPath("$.Primes", hasItems(2,3)));
		
		
	}


}
