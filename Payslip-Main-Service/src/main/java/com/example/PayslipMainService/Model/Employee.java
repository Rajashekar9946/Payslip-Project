package com.example.PayslipMainService.Model;

public class Employee {
    private Long employeeCode;
    private String name;
    private String department;
    private String employeeBand;
    private String function;
    private String subFunction;
    private String location;
    private Double salary;
    private String hireDate;

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
