package com.reliaquest.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import com.reliaquest.api.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;

@RestClientTest(EmployeeServiceImpl.class)
@TestPropertySource(properties = {"employee.api.base-url=http://localhost:8112/api/v1/employee"})
public class EmployeeServiceImplTest {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    void shouldFetchEmployeeById() {
        String json =
                """
            {
              "id": 1,
              "firstName": "Alice",
              "lastName": "Smith",
              "position": "Engineer",
              "salary": 70000.0
            }
        """;

        mockServer
                .expect(requestTo("http://localhost:8112/api/v1/employee/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        Employee employee = employeeService.getEmployeeById(1L);

        assertEquals("Alice", employee.getFirstName());
    }
}
