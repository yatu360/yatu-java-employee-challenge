package com.reliaquest.api.service;

import com.reliaquest.api.exception.EmployeeNotFoundException;
import com.reliaquest.api.model.Employee;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

    @Override
    public Employee getEmployeeById(Long id) {
        try {
            String url = employeeApiUrl + "/" + id;
            return restTemplate.getForObject(url, Employee.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
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
