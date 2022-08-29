package com.devdutt.circuit.breaker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Resilience4jCircuitBreakerApplication {

	@Bean
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Resilience4jCircuitBreakerApplication.class, args);
	}

}
