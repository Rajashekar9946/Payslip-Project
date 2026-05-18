package com.example.PayslipEarningsService.Repository;

import com.example.PayslipEarningsService.Model.EarningsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EarningsRepository extends JpaRepository<EarningsResponse, Long> {
    Optional<EarningsResponse> findByEmployeeCode(Long employeeCode);
    Optional<EarningsResponse> findByEmployeeCodeAndPayPeriod(Long employeeCode, String payPeriod);
}
