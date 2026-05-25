package com.example.PayslipTaxService.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tax_table")
public class Tax {
    @Id
    private Long employeeCode;

    @NotBlank(message = "PAN is mandatory")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "PAN must be in the format ABCD1234F")
    private String pan;

    @NotBlank(message = "ESI Number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "ESI Number must be 10 digits")
    private String esiNumber;

    @NotBlank(message = "PF  Number is mandatory")
    @Size(max =22, message = "PF Number should not exceed 22 characters")
    private String pfNumber;

    @NotBlank(message = "PF UAN Number is mandatory")
    @Pattern(regexp = "\\d{12}", message = "PF UAN Number must be 12 digits")
    private String pfUanNumber;

    public Tax() {
    }

    public Tax(Long employeeCode, String pan, String esiNumber, String pfNumber, String pfUanNumber) {
        this.employeeCode = employeeCode;
        this.pan = pan;
        this.esiNumber = esiNumber;
        this.pfNumber = pfNumber;
        this.pfUanNumber = pfUanNumber;
    }

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public @NotBlank(message = "PAN is mandatory") @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "PAN must be in the format ABCD1234F") String getPan() {
        return pan;
    }

    public void setPan(@NotBlank(message = "PAN is mandatory") @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "PAN must be in the format ABCD1234F") String pan) {
        this.pan = pan;
    }

    public @NotBlank(message = "ESI Number is mandatory") @Pattern(regexp = "\\d{10}", message = "ESI Number must be 10 digits") String getEsiNumber() {
        return esiNumber;
    }

    public void setEsiNumber(@NotBlank(message = "ESI Number is mandatory") @Pattern(regexp = "\\d{10}", message = "ESI Number must be 10 digits") String esiNumber) {
        this.esiNumber = esiNumber;
    }

    public @NotBlank(message = "PF  Number is mandatory") @Size(max = 22, message = "PF Number should not exceed 22 characters") String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(@NotBlank(message = "PF  Number is mandatory") @Size(max = 22, message = "PF Number should not exceed 22 characters") String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public @NotBlank(message = "PF UAN Number is mandatory") @Pattern(regexp = "\\d{12}", message = "PF UAN Number must be 12 digits") String getPfUanNumber() {
        return pfUanNumber;
    }

    public void setPfUanNumber(@NotBlank(message = "PF UAN Number is mandatory") @Pattern(regexp = "\\d{12}", message = "PF UAN Number must be 12 digits") String pfUanNumber) {
        this.pfUanNumber = pfUanNumber;
    }
}
