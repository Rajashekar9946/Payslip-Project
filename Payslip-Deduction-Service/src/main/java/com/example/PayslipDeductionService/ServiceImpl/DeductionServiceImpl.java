package com.example.PayslipDeductionService.ServiceImpl;

import com.example.PayslipDeductionService.DTO.Employee;
import com.example.PayslipDeductionService.Repository.DeductionRepository;
import com.example.PayslipDeductionService.Service.DeductionService;
import com.example.PayslipDeductionService.Util.MathUtil;
import com.example.PayslipDeductionService.model.DeductionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class DeductionServiceImpl implements DeductionService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeductionRepository deductionRepository;

    private final String earningServiceUrl = "http://localhost:9094/earnings/totalEarnings";

    @Override
    public Employee fetchEmployeeDetails(Long employeeCode) {
        return Optional.ofNullable(employeeCode)
                .map(code -> {
                    String url = "http://localhost:9099/employees/" + code;
                    return Optional.ofNullable(restTemplate.getForObject(url, Employee.class))
                            .orElseThrow(() -> new RuntimeException("EmployeeDetails Not Found for Code" + code));
                })
                .orElseThrow(() -> new IllegalArgumentException("Employee code can't be bull"));
    }

    @Override
    public DeductionResponse calculateDeductions(Long employeeCode) {
        DeductionResponse deductionResponse = getDeductionsByEmployeeCode(employeeCode);
        String url = earningServiceUrl + "/" + employeeCode;
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        Employee employeeResponse = fetchEmployeeDetails(employeeCode);
        Double annualSalary = employeeResponse.getSalary();
        Double monthlySalary = annualSalary / 12;

        return Optional.ofNullable(response)
                .filter(res -> res.getStatusCode() == HttpStatus.OK && res.getBody() != null)
                .map(ResponseEntity::getBody)
                .map(totalEarnings -> {
                    double monthlyTaxDeduction = calculateMonthlyTax(monthlySalary);
                    double providentFund = MathUtil.setToTwoDecimalPlaces(totalEarnings * 0.08);
                    double netPay = MathUtil.setToTwoDecimalPlaces(totalEarnings - monthlyTaxDeduction - providentFund);
                    String payPeriod = deductionResponse.getPayPeriod();

                    DeductionResponse deduction = new DeductionResponse(employeeCode, totalEarnings, monthlyTaxDeduction, providentFund, netPay, payPeriod);
                    deductionRepository.save(deduction);

                    return deduction;
                })
                .orElseThrow(() -> new RuntimeException("Failed to fetch total earnings for employeeCode:" + employeeCode));
    }

    private double calculateMonthlyTax(double monthlySalary) {
        return Optional.of(monthlySalary)
                .map(earnings -> {
                    double tax = 0.0;
                    tax += Optional.of(earnings)
                            .filter(e -> e > 1500000 / 12)
                            .map(e -> (e - 1500000 / 12) * 0.30)
                            .orElse(0.0);
                    earnings = Math.min(earnings, 1500000 / 12);
                    tax += Optional.of(earnings)
                            .filter(e -> e > 1200000 / 12)
                            .map(e -> (e - 1200000 / 12) * 0.20)
                            .orElse(0.0);
                    earnings = Math.min(earnings, 1200000 / 12);
                    tax += Optional.of(earnings)
                            .filter(e -> e > 1000000 / 12)
                            .map(e -> (e - 1000000 / 12) * 0.15)
                            .orElse(0.0);
                    earnings = Math.min(earnings, 700000 / 12);
                    tax += Optional.of(earnings)
                            .filter(e -> e > 700000 / 12)
                            .map(e -> (e - 700000 / 12) * 0.10)
                            .orElse(0.0);
                    earnings = Math.min(earnings, 700000 / 12);

                    tax += Optional.of(earnings)
                            .filter(e -> e > 300000 / 12)
                            .map(e -> (e - 300000 / 12) * 0.05)
                            .orElse(0.0);

                    return MathUtil.setToTwoDecimalPlaces(tax);
                })
                .orElse(0.0);
    }

    public DeductionResponse getDeductionsByEmployeeCode(Long employeeCode) {
        return deductionRepository.findByEmployeeCode(employeeCode)
                .map(deduction -> new DeductionResponse(
                        deduction.getEmployeeCode(),
                        MathUtil.setToTwoDecimalPlaces(deduction.getTotalEarnings()),
                        MathUtil.setToTwoDecimalPlaces(deduction.getTaxDeduction()),
                        MathUtil.setToTwoDecimalPlaces(deduction.getProvidentFund()),
                        MathUtil.setToTwoDecimalPlaces(deduction.getNetPay()),
                        deduction.getPayPeriod()
                ))
                .orElseThrow(() -> new RuntimeException("No Deduction found for employeeCode:" + employeeCode));
    }
}
