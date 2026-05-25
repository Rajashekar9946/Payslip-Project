package com.example.PayslipDeductionService.Repository;

import com.example.PayslipDeductionService.model.DeductionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeductionRepository extends JpaRepository<DeductionResponse, Long> {

    Optional<DeductionResponse> findByEmployeeCode(Long employeeCode);
}
