package com.reliaquest.api.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmployeeTest {
    @Test
    void testEmployeeFields() {
        Employee employee = new Employee();
        employee.setId("uuid");
        employee.setEmployeeName("John Doe");
        employee.setEmployeeSalary(90000);
        employee.setEmployeeAge(35);
        employee.setEmployeeTitle("Software Engineer");
        employee.setEmployeeEmail("john.doe@example.com");

        assertEquals("uuid", employee.getId());
        assertEquals("John Doe", employee.getEmployeeName());
        assertEquals(90000, employee.getEmployeeSalary());
        assertEquals(35, employee.getEmployeeAge());
        assertEquals("Software Engineer", employee.getEmployeeTitle());
        assertEquals("john.doe@example.com", employee.getEmployeeEmail());
    }
}
