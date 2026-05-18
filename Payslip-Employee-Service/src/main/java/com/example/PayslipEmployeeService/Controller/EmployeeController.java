package com.example.PayslipEmployeeService.Controller;

import com.example.PayslipEmployeeService.Service.EmployeeService;
import com.example.PayslipEmployeeService.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee createEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employee = employeeService.getAllEmployee();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/{searchTerm}")
    public ResponseEntity<Optional<Employee>> getEmployeeByCodeOrName(@PathVariable String searchTerm) {
        Optional<Employee> employee = employeeService.getEmployeeByCodeOrName(searchTerm);
        return employee.isPresent() ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{employeeCode}/salary")
    public ResponseEntity<Optional<Double>> getSalaryByEmployeeCode(@PathVariable Long employeeCode) {
        Optional<Double> employeeSalary = employeeService.getSalaryByEmployeeCode(employeeCode);
        return employeeSalary.isPresent() ? new ResponseEntity<>(employeeSalary, HttpStatus.OK)
                : new ResponseEntity<>(employeeSalary, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{employeeCode}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeCode, @RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employeeCode, employee);
        return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
    }
}
