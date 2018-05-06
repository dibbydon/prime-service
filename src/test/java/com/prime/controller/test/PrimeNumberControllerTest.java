package com.prime.controller.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prime.controller.PrimeControllerExceptionHandler;
import com.prime.controller.PrimeNumberController;
import com.prime.exception.BadInputException;
import com.prime.model.PrimeResult;
import com.prime.service.PrimeNumberGeneratorServiceImpl;


public class PrimeNumberControllerTest {
	
	private MockMvc mockMvc;
	@Mock
	private PrimeNumberGeneratorServiceImpl primeNumberGeneratorService;
	
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
	public void testPrimeNumberServiceInteraction() throws BadInputException {
		Integer limit = 3;
		when(primeNumberGeneratorService.generatePrimeNumbers(limit)).thenReturn(new PrimeResult(3, Arrays.asList(2, 3)));
		
		ResponseEntity<?> response = primeNumbercontroller.getPrimeNumbers(limit);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		verify(primeNumberGeneratorService, times(1)).generatePrimeNumbers(limit);
		verifyNoMoreInteractions(primeNumberGeneratorService);
	}
	
	@Test
	public void testMockMvcPrimeNumberServiceInteraction() throws Exception {
		Integer limit = 3;
		when(primeNumberGeneratorService.generatePrimeNumbers(limit)).thenReturn(new PrimeResult(limit, Arrays.asList(2, 3)));
		mockMvc.perform(get("/primes/{limit}", limit))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(jsonPath("$.Initial", is(3)))
		       .andExpect(jsonPath("$.Primes", hasItems(2,3)));
		
		verify(primeNumberGeneratorService, times(1)).generatePrimeNumbers(limit);
		verifyNoMoreInteractions(primeNumberGeneratorService);
	}
	
	@Test
	public void testMockMvcPrimeNumberServiceExpectErrorMessage() throws Exception {
		Integer limit = 1;
		when(primeNumberGeneratorService.generatePrimeNumbers(limit)).thenThrow(new BadInputException("input must be greater than 2"));
		mockMvc.perform(get("/primes/{limit}", limit))
		       .andExpect(status().isBadRequest())
		       .andExpect(jsonPath("$.message").value("input must be greater than 2"));
	}


}
