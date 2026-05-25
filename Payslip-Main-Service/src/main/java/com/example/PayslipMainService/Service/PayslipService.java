package com.example.PayslipMainService.Service;

import com.example.PayslipMainService.Model.*;
import com.example.PayslipMainService.PdfGenerator.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class PayslipService {

    private static final String EMPLOYEE_URL = "http://localhost:9099/employees/";
    private static final String BANK_URL = "http://localhost:9091/banks/";
    private static final String TAX_URL = "http://localhost:9092/taxes/";
    private static final String EARNINGS_URL = "http://localhost:9094/earnings/";
    private static final String DEDUCTIONS_URL = "http://localhost:9095/deductions/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PdfGenerator pdfGenerator;

    public void generatePayslip(Long employeeCode, String payPeriod, HttpServletResponse response) throws Exception {
        CompletableFuture<Employee> employeeFuture = getEmployee(employeeCode);
        CompletableFuture<BankDetails> bankFuture = getBank(employeeCode);
        CompletableFuture<TaxDetails> taxFuture = getTax(employeeCode);
        CompletableFuture<Earnings> earningFuture = getEarnings(employeeCode, payPeriod);
        CompletableFuture<Deductions> deductionFuture = getDeductions(employeeCode);

        CompletableFuture.allOf(employeeFuture, bankFuture, taxFuture, earningFuture, deductionFuture).join();

        Employee employee = employeeFuture.get();
        BankDetails bank = bankFuture.get();
        TaxDetails tax = taxFuture.get();
        Earnings earnings = earningFuture.get();
        Deductions deductions = deductionFuture.get();

        if (earnings == null || !payPeriod.equals(earnings.getPayPeriod())) {
            response.setContentType("text/plain");
            response.getWriter().write("payslip not generated");
            return;
        }

        pdfGenerator.generatePaySlipPdf(employee, bank, tax, earnings, deductions, response);
    }

    @Async
    public CompletableFuture<Employee> getEmployee(Long code) {
        try {
            return CompletableFuture.completedFuture(
                    restTemplate.getForObject(EMPLOYEE_URL + code, Employee.class));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Failled to fetch employee details:" + e.getMessage());
        }
    }

    @Async
    public CompletableFuture<BankDetails> getBank(Long code) {
        try {
            return CompletableFuture.completedFuture(
                    restTemplate.getForObject(BANK_URL + code, BankDetails.class));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Failled to fetch bank details:" + e.getMessage());
        }
    }

    @Async
    public CompletableFuture<TaxDetails> getTax(Long code) {
        try {
            return CompletableFuture.completedFuture(
                    restTemplate.getForObject(TAX_URL + code, TaxDetails.class));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Failled to fetch tax details:" + e.getMessage());
        }
    }

    @Async
    public CompletableFuture<Earnings> getEarnings(Long code, String period) {
        try {
            return CompletableFuture.completedFuture(
                    restTemplate.getForObject(EARNINGS_URL + code + "?payPeriod=" + period, Earnings.class));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return CompletableFuture.completedFuture(null);
        }
    }

    @Async
    public CompletableFuture<Deductions> getDeductions(Long code) {
        try {
            return CompletableFuture.completedFuture(
                    restTemplate.getForObject(DEDUCTIONS_URL + code, Deductions.class));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Failled to fetch deductions details:" + e.getMessage());
        }
    }

}
