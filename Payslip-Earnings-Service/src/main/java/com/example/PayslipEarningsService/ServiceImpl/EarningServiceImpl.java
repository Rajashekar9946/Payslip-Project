package com.example.PayslipEarningsService.ServiceImpl;

import com.example.PayslipEarningsService.DTO.EmployeeResponse;
import com.example.PayslipEarningsService.Model.AttendanceRequest;
import com.example.PayslipEarningsService.Model.EarningsResponse;
import com.example.PayslipEarningsService.Repository.AttendenceRequestRepository;
import com.example.PayslipEarningsService.Repository.EarningsRepository;
import com.example.PayslipEarningsService.Service.EarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class EarningServiceImpl implements EarningService {


    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EarningsRepository earningsRepository;
    @Autowired
    private AttendenceRequestRepository attendenceRequestRepository;

    @Autowired
    private EarningsCalculator earningsCalculator;

    private final String employeeServiceUrl = "http://localhost:9099/employees/";

    @Override
    public EmployeeResponse fetchEmployeedetails(Long employeeCode) {
        return Optional.ofNullable(employeeCode).map(code -> {
            String url = employeeServiceUrl + code;
            return Optional.ofNullable(restTemplate.getForObject(url, EmployeeResponse.class))
                    .orElseThrow(() -> {
                        return new RuntimeException("Employee details not found for code" + code);
                    });
        }).orElseThrow(() -> new IllegalArgumentException("Employee code can't be null"));
    }

    @Override
    public CompletableFuture<EarningsResponse> calculateEarnings(AttendanceRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            Long employeeCode = Optional.ofNullable(request.getEmployeeCode())
                    .orElseThrow(() -> new IllegalArgumentException("Employee code can't be null"));
            EmployeeResponse employeeResponse = fetchEmployeedetails(employeeCode);

            EarningsResponse earningsResponse = earningsCalculator.calculateEarnings(request, employeeResponse);
            earningsRepository.save(earningsResponse);
            attendenceRequestRepository.save(request);

            return earningsResponse;
        });
    }

    @Override
    public EarningsResponse getEarningsByEmployeeCode(Long employeeCode) {
        return earningsRepository.findByEmployeeCode(employeeCode)
                .map(earnings -> EarningsResponse.builder()
                        .employeeCode(earnings.getEmployeeCode())
                        .payPeriod(earnings.getPayPeriod())
                        .baseSalary(earnings.getBaseSalary())
                        .houseRentAllowance(earnings.getHouseRentAllowance())
                        .reconciledFlexiPay(earnings.getReconciledFlexiPay())
                        .variablePay(earnings.getVariablePay())
                        .totalEarnings(earnings.getTotalEarnings())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("No Earnings data found for employeeCode:" + employeeCode));
    }

    @Override
    public EarningsResponse getEarningsByEmployeeCodeAndPayPeriod(Long employeeCode, String payPeriod) {
        return earningsRepository.findByEmployeeCodeAndPayPeriod(employeeCode,payPeriod)
                .map(earnings->EarningsResponse.builder()
                        .employeeCode(earnings.getEmployeeCode())
                        .payPeriod(earnings.getPayPeriod())
                        .baseSalary(earnings.getBaseSalary())
                        .houseRentAllowance(earnings.getHouseRentAllowance())
                        .reconciledFlexiPay(earnings.getReconciledFlexiPay())
                        .variablePay(earnings.getVariablePay())
                        .totalEarnings(earnings.getTotalEarnings())
                        .build())
                .orElseThrow(()->new NoSuchElementException("No earnings data found for employeeCode:"+employeeCode+"and payPeriod:"+payPeriod));
    }

}
