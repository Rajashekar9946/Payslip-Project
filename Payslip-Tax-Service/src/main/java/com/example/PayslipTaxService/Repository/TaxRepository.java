package com.example.PayslipTaxService.Repository;

import com.example.PayslipTaxService.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    Optional<Tax> findByEmployeeCode(Long employeeCode);

    Optional<Tax> findByPan(String pan);
}
