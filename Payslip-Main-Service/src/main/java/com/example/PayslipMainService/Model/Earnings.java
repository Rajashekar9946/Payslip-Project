package com.example.PayslipMainService.Model;

public class Earnings {
    private Long employeeCode;
    private String payPeriod;
    private Double baseSalary;
    private Double houseRentAllowance;
    private Double reconciledFlexiPay;
    private Double variablePay;
    private Double totalEarnings;

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getHouseRentAllowance() {
        return houseRentAllowance;
    }

    public void setHouseRentAllowance(Double houseRentAllowance) {
        this.houseRentAllowance = houseRentAllowance;
    }

    public Double getReconciledFlexiPay() {
        return reconciledFlexiPay;
    }

    public void setReconciledFlexiPay(Double reconciledFlexiPay) {
        this.reconciledFlexiPay = reconciledFlexiPay;
    }

    public Double getVariablePay() {
        return variablePay;
    }

    public void setVariablePay(Double variablePay) {
        this.variablePay = variablePay;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }
}
