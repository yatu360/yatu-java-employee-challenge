package com.reliaquest.api.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reliaquest.api.model.Employee;
import com.reliaquest.api.service.EmployeeService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(List.of(new Employee()));
        mockMvc.perform(get("/employee/getAllEmployees")).andExpect(status().isOk());
    }

    @Test
    void testGetEmployeesByNameSearch() throws Exception {
        when(employeeService.getEmployeesByNameSearch("john")).thenReturn(List.of(new Employee()));
        mockMvc.perform(get("/employee/search?name=john")).andExpect(status().isOk());
    }

    @Test
    void testGetHighestSalary() throws Exception {
        when(employeeService.getHighestSalaryOfEmployees()).thenReturn(123456);
        mockMvc.perform(get("/employee/highest-salary")).andExpect(status().isOk());
    }

    @Test
    void testGetTop10HighestEarningEmployeeNames() throws Exception {
        when(employeeService.getTop10HighestEarningEmployeeNames()).thenReturn(List.of("Alice"));
        mockMvc.perform(get("/employee/top-ten-earners")).andExpect(status().isOk());
    }

    @Test
    void testCreateEmployee() throws Exception {
        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"employee_name\": \"Test\" }"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployeeByIdById() throws Exception {
        mockMvc.perform(delete("/employee/1")).andExpect(status().isOk());
    }

    @Test
    void testGetAllEmployees1() throws Exception {
        Employee emp = new Employee();
        emp.setId("1");
        emp.setEmployeeName("Alice");
        emp.setEmployeeSalary(320800);
        emp.setEmployeeAge(30);
        emp.setEmployeeTitle("Engineer");
        emp.setEmployeeEmail("alice@company.com");

        when(employeeService.getAllEmployees()).thenReturn(List.of(emp));

        mockMvc.perform(get("/employee/getAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employee_name").value("Alice"))
                .andExpect(jsonPath("$[0].employee_salary").value(320800))
                .andExpect(jsonPath("$[0].employee_email").value("alice@company.com"));
    }
}
