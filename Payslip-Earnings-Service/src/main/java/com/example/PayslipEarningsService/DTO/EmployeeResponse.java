package com.example.PayslipEarningsService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
    private Long employeeCode;
    private String name;
    private String department;
    private String employeeBand;
    private String function;
    private String subFunction;
    private Double salary;
    private String hireDate;

}
