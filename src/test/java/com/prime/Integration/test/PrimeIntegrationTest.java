package com.prime.Integration.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PrimeIntegrationTest {
	@LocalServerPort
	private int port;
	
	@Rule
	public JUnitRestDocumentation restDoc = new JUnitRestDocumentation("target/generated-snippet");
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void givenValidInputOf_10_ExpectAnOkAndValidJsonResponse() throws IOException {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/primes/10", String.class);
		System.out.println(responseEntity);
		ReadContext ctx = JsonPath.parse(responseEntity.getBody());
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(10 , equalTo(ctx.read("$.Initial")));
		assertThat(ctx.read("$.Primes"), hasItems(2,3,5,7));
	}
	
	@Test
	public void givenValidInputOf_10_WithOptionalAlgorithmExpectAnOkAndValidJsonResponse() throws IOException {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/primes/10?algo=pll", String.class);
		System.out.println(responseEntity);
		ReadContext ctx = JsonPath.parse(responseEntity.getBody());
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(10 , equalTo(ctx.read("$.Initial")));
		assertThat(ctx.read("$.Primes"), hasItems(2,3,5,7));
	}
	
	@Test
	public void givenInvalidInputOf_1_ExpectErrorDetailedMessage() throws IOException {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/primes/1", String.class);
		System.out.println(responseEntity);
		String message = "input must be greater than 2";
		ReadContext ctx = JsonPath.parse(responseEntity.getBody());
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
		assertThat(message , equalTo(ctx.read("$.message")));
		assertNotNull(ctx.read("$.additionalMessage"));
	}
	
	@Test
	public void givenInvalidInputOf_1_WithOptionalParameterExpectErrorDetailedMessage() throws IOException {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/primes/1?algo=pll", String.class);
		System.out.println(responseEntity);
		String message = "input must be greater than 2";
		ReadContext ctx = JsonPath.parse(responseEntity.getBody());
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
		assertThat(message , equalTo(ctx.read("$.message")));
		assertNotNull(ctx.read("$.additionalMessage"));
	}
	
	@TestConfiguration
	static class Config {

		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().setConnectTimeout(1000).setReadTimeout(1000);
		}

	}

}
