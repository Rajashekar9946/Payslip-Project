package com.example.PayslipMainService.Model;

public class Payslip {
    private Employee employee;
    private BankDetails bankDetails;
    private TaxDetails taxDetails;
    private Earnings earnings;
    private Deductions deductions;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public TaxDetails getTaxDetails() {
        return taxDetails;
    }

    public void setTaxDetails(TaxDetails taxDetails) {
        this.taxDetails = taxDetails;
    }

    public Earnings getEarnings() {
        return earnings;
    }

    public void setEarnings(Earnings earnings) {
        this.earnings = earnings;
    }

    public Deductions getDeductions() {
        return deductions;
    }

    public void setDeductions(Deductions deductions) {
        this.deductions = deductions;
    }
}
