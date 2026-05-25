package com.example.PayslipDeductionService.Controller;

import com.example.PayslipDeductionService.Service.DeductionService;
import com.example.PayslipDeductionService.model.DeductionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deductions")
public class DeductionController {

    @Autowired
    private DeductionService deductionService;


    @GetMapping("/{employeeCode}")
    public ResponseEntity<DeductionResponse> calculateDeductions(@PathVariable Long employeeCode) {
        DeductionResponse response = deductionService.calculateDeductions(employeeCode);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetch/{employeeCode}")
    public ResponseEntity<DeductionResponse> getDedutionsByEmployeeCode(@PathVariable Long employeeCode) {
        DeductionResponse deductionResponse = deductionService.getDeductionsByEmployeeCode(employeeCode);
        return ResponseEntity.ok(deductionResponse);
    }
}
