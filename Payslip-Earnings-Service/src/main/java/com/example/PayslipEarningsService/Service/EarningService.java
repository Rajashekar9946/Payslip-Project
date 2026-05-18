package com.example.PayslipEarningsService.Service;



import com.example.PayslipEarningsService.DTO.EmployeeResponse;
import com.example.PayslipEarningsService.Model.AttendanceRequest;
import com.example.PayslipEarningsService.Model.EarningsResponse;

import java.util.concurrent.CompletableFuture;

public interface EarningService {
    EmployeeResponse fetchEmployeedetails(Long employeeCode);

    CompletableFuture<EarningsResponse> calculateEarnings(AttendanceRequest request);

    EarningsResponse getEarningsByEmployeeCode(Long employeeCode);
}
