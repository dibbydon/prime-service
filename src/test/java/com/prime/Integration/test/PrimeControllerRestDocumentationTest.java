package com.prime.Integration.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.prime.controller.PrimeNumberController;
/**
 * 
 * @author dibbydon
 * a rest documentation test case to generate api doc for rest endpoints.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PrimeNumberController.class)
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public class PrimeControllerRestDocumentationTest {

	@Autowired
	private MockMvc mockMvc;
	
	

	@Test
	public void testDefaultRequestShouldReturnValidResponse() throws Exception {
		mockMvc.perform(get("/primes/10").accept(MediaType.APPLICATION_JSON))
					.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("index", responseFields(
                            		          fieldWithPath("Initial").description("the limit to which prime number can be generated inclusive"),
                            		          fieldWithPath("Primes").description("an array of prime numbers within the limit inclusive"))));
	}

	
	@Test 
	public void testRequestWithInvalidInputShouldReturnErrorResponse() throws Exception {
		mockMvc.perform(get("/primes/1").accept(MediaType.APPLICATION_JSON))
		                .andDo(print())
		                .andExpect(status().isBadRequest())
		                .andDo(document("exception", responseFields(
		                		fieldWithPath("message").description("error message thrown by the exception"),
		                		fieldWithPath("additionalMessage").description("a more detailed excetion providing stack traces"))));
	}
	
	@Test
	public void testRequestWithOptiontoChooseAlgorithShouldReturnValidResponse() throws Exception {
		mockMvc.perform(get("/primes/10?algo=pll").accept(MediaType.APPLICATION_JSON))
					.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("optional", requestParameters(
                            		        parameterWithName("algo").description("parameter to determine the algorithm to be used in request"))));
	}
	
}
