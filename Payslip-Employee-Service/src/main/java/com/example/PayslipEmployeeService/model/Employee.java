package com.example.PayslipEmployeeService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "employee_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    private Long employeeCode;
    private String name;
    private String department;
    private String employeeBand;
    private String function;
    private String subFunction;
    private String location;
    private Double salary;
    private String hireDate;


}
