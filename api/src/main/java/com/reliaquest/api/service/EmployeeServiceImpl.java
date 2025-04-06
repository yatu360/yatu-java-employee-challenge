package com.reliaquest.api.service;

import com.reliaquest.api.exception.EmployeeNotFoundException;
import com.reliaquest.api.model.Employee;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final RestTemplate restTemplate;
    private final String employeeApiUrl;

    public EmployeeServiceImpl(RestTemplateBuilder builder, @Value("${employee.api.base-url}") String employeeApiUrl) {
        this.restTemplate = builder.build();
        this.employeeApiUrl = employeeApiUrl;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Employee[] response = restTemplate.getForObject(employeeApiUrl, Employee[].class);
        return Arrays.asList(response);
    }

    public Employee getEmployeeById(Long id) {
        log.info("Fetching employee by ID: {}", id);
        try {
            ResponseEntity<Employee> response = restTemplate.getForEntity(employeeApiUrl + "/" + id, Employee.class);
            Employee employee = response.getBody();
            log.debug("Received response: {}", employee);
            return employee;
        } catch (Exception e) {
            log.error("Error fetching employee by ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return restTemplate.postForObject(employeeApiUrl, employee, Employee.class);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        String url = employeeApiUrl + "/" + id;
        restTemplate.put(url, employee);
        return getEmployeeById(id); // fetch updated employee
    }

    @Override
    public void deleteEmployee(Long id) {
        String url = employeeApiUrl + "/" + id;
        try {
            restTemplate.delete(url);
        } catch (HttpClientErrorException.NotFound e) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }
}
