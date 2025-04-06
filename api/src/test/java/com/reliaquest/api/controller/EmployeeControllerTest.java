package com.reliaquest.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reliaquest.api.exception.EmployeeNotFoundException;
import com.reliaquest.api.model.Employee;
import com.reliaquest.api.service.EmployeeService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void shouldReturnListOfEmployees() throws Exception {
        List<Employee> mockList = List.of(
                new Employee(1L, "Alice", "Smith", "Engineer", 70000),
                new Employee(2L, "Bob", "Jones", "Manager", 90000));

        when(employeeService.getAllEmployees()).thenReturn(mockList);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Alice"))
                .andExpect(jsonPath("$[1].lastName").value("Jones"));
    }

    @Test
    void shouldReturnEmployeeById() throws Exception {
        Employee mockEmployee = new Employee(1L, "Alice", "Smith", "Engineer", 70000);

        when(employeeService.getEmployeeById(1L)).thenReturn(mockEmployee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alice"))
                .andExpect(jsonPath("$.position").value("Engineer"));
    }

    @Test
    void shouldReturn404IfEmployeeNotFound() throws Exception {
        when(employeeService.getEmployeeById(999L))
                .thenThrow(new EmployeeNotFoundException("Employee not found"));

        mockMvc.perform(get("/employees/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Employee not found"));
    }
}
