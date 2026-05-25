package com.example.PayslipDeductionService.Service;

import com.example.PayslipDeductionService.DTO.Employee;
import com.example.PayslipDeductionService.model.DeductionResponse;

public interface DeductionService {
    Employee fetchEmployeeDetails(Long employeeCode);

    DeductionResponse calculateDeductions(Long employeeCode);

    DeductionResponse getDeductionsByEmployeeCode(Long employeeCode);
}
