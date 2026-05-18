package com.example.PayslipEarningsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("com.example.PayslipEarningsService.Model")
@EnableJpaRepositories(basePackages = "com.example.PayslipEarningsService.Repository")
public class PayslipEarningsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayslipEarningsServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
