package com.example.PayslipEmployeeService.ServiceImpl;

import com.example.PayslipEmployeeService.Exception.EmployeeNotFoundException;
import com.example.PayslipEmployeeService.Repository.EmployeeRepository;
import com.example.PayslipEmployeeService.Service.EmployeeService;
import com.example.PayslipEmployeeService.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        Employee saveEmployee = employeeRepository.save(employee);
        return saveEmployee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return Optional.of(employeeRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new EmployeeNotFoundException("No Employee Not Found."));
    }

    @Override
    public Optional<Employee> getEmployeeByCodeOrName(String searchTerm) {
        return Optional.of(employeeRepository.findAll().stream().filter(employee -> isNumeric(searchTerm)
                        ? employee.getEmployeeCode().equals(Long.parseLong(searchTerm))
                        : employee.getName().equalsIgnoreCase(searchTerm))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found:" + searchTerm)));
    }

    @Override
    public Optional<Double> getSalaryByEmployeeCode(Long employeeCode) {
        Optional<Employee> employee = employeeRepository.findByEmployeeCode(employeeCode);
        return employee.map(emp -> emp.getSalary());
    }

    @Override
    public Employee updateEmployee(Long employeeCode, Employee employee) {
        Optional<Employee> optionalEmployee = Optional.of(employeeRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found :" + employeeCode)));
        Employee updateEmployee = optionalEmployee.get();
        updateEmployee.setEmployeeCode(employee.getEmployeeCode());
        updateEmployee.setName(employee.getName());
        updateEmployee.setDepartment(employee.getDepartment());
        updateEmployee.setEmployeeBand(employee.getEmployeeBand());
        updateEmployee.setFunction(employee.getFunction());
        updateEmployee.setSubFunction(employee.getSubFunction());
        updateEmployee.setLocation(employee.getLocation());
        updateEmployee.setSalary(employee.getSalary());
        updateEmployee.setHireDate(employee.getHireDate());
        return updateEmployee;
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
