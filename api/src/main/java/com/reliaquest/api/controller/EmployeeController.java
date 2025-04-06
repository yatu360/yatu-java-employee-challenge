package com.reliaquest.api.controller;

import com.reliaquest.api.model.Employee;
import com.reliaquest.api.service.EmployeeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("Received request to fetch employee with ID: {}", id);
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/highest-salary")
    public ResponseEntity<Integer> getHighestSalary() {
        return ResponseEntity.ok(employeeService.getHighestSalaryOfEmployees());
    }

    @GetMapping("/top-ten-earners")
    public ResponseEntity<List<String>> getTop10HighestEarningEmployeeNames() {
        return ResponseEntity.ok(employeeService.getTop10HighestEarningEmployeeNames());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.getEmployeesByNameSearch(name));
    }
}
