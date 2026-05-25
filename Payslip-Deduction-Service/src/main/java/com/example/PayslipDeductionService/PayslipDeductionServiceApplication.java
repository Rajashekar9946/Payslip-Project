package com.example.PayslipDeductionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("com.example.PayslipDeductionService.model")
@EnableJpaRepositories(basePackages = "com.example.PayslipDeductionService.Repository")
public class PayslipDeductionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayslipDeductionServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
