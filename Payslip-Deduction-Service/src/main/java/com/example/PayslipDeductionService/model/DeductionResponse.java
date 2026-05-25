package com.example.PayslipDeductionService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deduction_table")
public class DeductionResponse {
    @Id
    private Long employeeCode;
    private Double totalEarnings;
    private Double taxDeduction;
    private Double providentFund;
    private Double netPay;
    private String payPeriod;

    public DeductionResponse() {
    }

    public DeductionResponse(Long employeeCode, Double totalEarnings, Double taxDeduction, Double providentFund, Double netPay, String payPeriod) {
        this.employeeCode = employeeCode;
        this.totalEarnings = totalEarnings;
        this.taxDeduction = taxDeduction;
        this.providentFund = providentFund;
        this.netPay = netPay;
        this.payPeriod = payPeriod;
    }

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public Double getTaxDeduction() {
        return taxDeduction;
    }

    public void setTaxDeduction(Double taxDeduction) {
        this.taxDeduction = taxDeduction;
    }

    public Double getProvidentFund() {
        return providentFund;
    }

    public void setProvidentFund(Double providentFund) {
        this.providentFund = providentFund;
    }

    public Double getNetPay() {
        return netPay;
    }

    public void setNetPay(Double netPay) {
        this.netPay = netPay;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    @Override
    public String toString() {
        return "DeductionResponse{" +
                "employeeCode=" + employeeCode +
                ", totalEarnings=" + totalEarnings +
                ", taxDeduction=" + taxDeduction +
                ", providentFund=" + providentFund +
                ", netPay=" + netPay +
                ", payPeriod='" + payPeriod + '\'' +
                '}';
    }
}
