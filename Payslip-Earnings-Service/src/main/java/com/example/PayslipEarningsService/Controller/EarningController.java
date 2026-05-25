package com.example.PayslipEarningsService.Controller;

import com.example.PayslipEarningsService.Model.AttendanceRequest;
import com.example.PayslipEarningsService.Model.EarningsResponse;
import com.example.PayslipEarningsService.Service.EarningService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/earnings")
public class EarningController {
    @Autowired
    private EarningService earningService;


    @PostMapping("/calculate")
    public CompletableFuture<ResponseEntity<EarningsResponse>> calculateEarnings(@RequestBody AttendanceRequest request) {
        return earningService.calculateEarnings(request)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{employeeCode}")
    public ResponseEntity<EarningsResponse> getEarningsByEmployeeCode(@PathVariable Long employeeCode) {
        EarningsResponse response = earningService.getEarningsByEmployeeCode(employeeCode);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/employee/{employeeCode}/payPeriod/{payPeriod}")
    public ResponseEntity<EarningsResponse> getEarningsByEmployeeCodeAndPayPeriod(
            @PathVariable Long employeeCode, @PathVariable String payPeriod) {
        EarningsResponse earningsResponse = earningService.getEarningsByEmployeeCodeAndPayPeriod(employeeCode, payPeriod);
        return ResponseEntity.ok(earningsResponse);
    }

    @GetMapping("totalEarnings/{employeeCode}")
    public ResponseEntity<Double> fetchTotalEarnings(@PathVariable Long employeeCode) {
        EarningsResponse earningsResponse = earningService.getEarningsByEmployeeCode(employeeCode);
        Double totalEarnings = earningsResponse.getTotalEarnings();
        return ResponseEntity.ok(totalEarnings);
    }

}
