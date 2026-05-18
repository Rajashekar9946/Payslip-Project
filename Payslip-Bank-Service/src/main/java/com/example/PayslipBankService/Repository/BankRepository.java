package com.example.PayslipBankService.Repository;

import com.example.PayslipBankService.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByEmployeeCode(Long employeeCode);
}
