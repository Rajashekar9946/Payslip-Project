package com.example.PayslipBankService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.PayslipBankService.model")
@EnableJpaRepositories(basePackages = "com.example.payslipBankService.Repository")
public class PayslipBankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayslipBankServiceApplication.class, args);
    }

}
