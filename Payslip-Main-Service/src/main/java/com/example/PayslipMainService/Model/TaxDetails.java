package com.example.PayslipMainService.Model;

public class TaxDetails {
    private Long employeeCode;
    private String pan;
    private String esiNumber;
    private String pfNumber;
    private String pfUanNumber;

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getEsiNumber() {
        return esiNumber;
    }

    public void setEsiNumber(String esiNumber) {
        this.esiNumber = esiNumber;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getPfUanNumber() {
        return pfUanNumber;
    }

    public void setPfUanNumber(String pfUanNumber) {
        this.pfUanNumber = pfUanNumber;
    }
}
