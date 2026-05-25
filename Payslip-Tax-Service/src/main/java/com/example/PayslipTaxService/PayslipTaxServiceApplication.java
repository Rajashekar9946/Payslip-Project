package com.example.PayslipTaxService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.PayslipTaxService.model")
@EnableJpaRepositories(basePackages = "com.example.PayslipTaxService.Repository")
public class PayslipTaxServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayslipTaxServiceApplication.class, args);
	}

}
