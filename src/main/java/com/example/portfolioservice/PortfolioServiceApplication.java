package com.example.portfolioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PortfolioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioServiceApplication.class, args);
	}

	//@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
