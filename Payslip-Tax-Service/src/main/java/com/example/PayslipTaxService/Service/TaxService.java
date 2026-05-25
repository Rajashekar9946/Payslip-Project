package com.example.PayslipTaxService.Service;

import com.example.PayslipTaxService.model.Tax;

import java.util.List;
import java.util.Optional;

public interface TaxService {

    Tax createTax(Tax tax);

    List<Tax> getAllTaxes();

    Optional<Tax> getTaxByCodeOrPan(String searchTerm);

    Tax updateTax(Long employeeCode, Tax updatedTax);

    void deleteTax(Long employeeCode);
}
