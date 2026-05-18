package com.example.PayslipEmployeeService.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_table")
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

    public Employee() {
    }

    public Employee(Long employeeCode, String name, String department, String employeeBand, String function, String subFunction, String location, Double salary, String hireDate) {
        this.employeeCode = employeeCode;
        this.name = name;
        this.department = department;
        this.employeeBand = employeeBand;
        this.function = function;
        this.subFunction = subFunction;
        this.location = location;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeBand() {
        return employeeBand;
    }

    public void setEmployeeBand(String employeeBand) {
        this.employeeBand = employeeBand;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getSubFunction() {
        return subFunction;
    }

    public void setSubFunction(String subFunction) {
        this.subFunction = subFunction;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeCode=" + employeeCode +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", employeeBand='" + employeeBand + '\'' +
                ", function='" + function + '\'' +
                ", subFunction='" + subFunction + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
