package com.devdutt.circuit.breaker.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerRestController {

	Logger logger = LoggerFactory.getLogger(CircuitBreakerRestController.class);

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/getInvoice")
	@CircuitBreaker(name = "getInvoiceCB", fallbackMethod = "getInvoiceFallBack")
	public String getInvoice() {
		logger.info("getInvoice() call starts here");
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8888/invoice/rest/find/1",
				String.class);
		logger.info("Response:- " + response.getStatusCode());
		return response.getBody();
	}

	public String getInvoiceFallBack(Exception e) {
		logger.info("-----RESPONSE FROM FALLBACK METHOD------");
		return "SERVICE IS DOWN..! PLEASE TRY AFTER SOMETIMES...";
	}
}
