package com.example.PayslipMainService.Controller;

import com.example.PayslipMainService.Service.PayslipService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaySlipController {

    @Autowired
    private PayslipService payslipService;

    @PostMapping("/generatePayslip")
    public void generatePayslip(@RequestParam Long employeeCode, @RequestParam String payPeriod, HttpServletResponse response) throws Exception {
        payslipService.generatePayslip(employeeCode, payPeriod, response);
    }

}
