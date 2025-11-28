package com.example.PayslipEmployeeService.Service;

import com.example.PayslipEmployeeService.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Optional<Employee> getEmployeeByCodeOrName(String searchTerm);

    Optional<Double> getSalaryByEmployeeCode(Long employeeCode);

    Employee updateEmployee(Long employeeCode, Employee employee);
}
