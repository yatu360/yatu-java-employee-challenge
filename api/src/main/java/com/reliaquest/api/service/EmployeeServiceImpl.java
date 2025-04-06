package com.reliaquest.api.service;

import com.reliaquest.api.exception.EmployeeNotFoundException;
import com.reliaquest.api.model.Employee;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final RestTemplate restTemplate;
    private final String employeeApiUrl;

    public EmployeeServiceImpl(RestTemplate restTemplate, @Value("${employee.api.base-url}") String employeeApiUrl) {
        this.restTemplate = restTemplate;
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
}
