package com.reliaquest.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import com.reliaquest.api.model.Employee;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RestClientTest(EmployeeServiceImpl.class)
@Import(EmployeeServiceImplTest.TestConfig.class)
@TestPropertySource(properties = {"employee.api.base-url=http://localhost:8112/api/v1/employee"})
public class EmployeeServiceImplTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockRestServiceServer server;

    private final String baseUrl = "http://localhost:8112/api/v1/employee";

    private final String mockJson =
            """
            [
              {
                "id": "1",
                "employee_name": "Alice",
                "employee_salary": 100000,
                "employee_age": 30,
                "employee_title": "Engineer",
                "employee_email": "alice@company.com"
              },
              {
                "id": "2",
                "employee_name": "Bob",
                "employee_salary": 120000,
                "employee_age": 32,
                "employee_title": "Manager",
                "employee_email": "bob@company.com"
              }
            ]
            """;

    @BeforeEach
    void setup() {
        server.reset();
    }

    @Test
    void testGetAllEmployees() {
        server.expect(requestTo(baseUrl)).andRespond(withSuccess(mockJson, MediaType.APPLICATION_JSON));
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(2, employees.size());
    }

    @Test
    void testGetEmployeesByNameSearch() {
        server.expect(requestTo(baseUrl)).andRespond(withSuccess(mockJson, MediaType.APPLICATION_JSON));
        List<Employee> result = employeeService.getEmployeesByNameSearch("bob");
        assertEquals(1, result.size());
        assertEquals("Bob", result.get(0).getEmployeeName());
    }

    @Test
    void testGetHighestSalaryOfEmployees() {
        server.expect(requestTo(baseUrl)).andRespond(withSuccess(mockJson, MediaType.APPLICATION_JSON));
        Integer highest = employeeService.getHighestSalaryOfEmployees();
        assertEquals(120000, highest);
    }

    @Test
    void testGetTop10HighestEarningEmployeeNames() {
        server.expect(requestTo(baseUrl)).andRespond(withSuccess(mockJson, MediaType.APPLICATION_JSON));
        List<String> top = employeeService.getTop10HighestEarningEmployeeNames();
        assertEquals("Bob", top.get(0));
    }
}
