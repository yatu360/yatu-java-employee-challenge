package com.reliaquest.api.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmployeeTest {

    @Test
    void shouldCreateEmployeeObjectCorrectly() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPosition("Developer");
        employee.setSalary(75000.0);

        assertEquals(1L, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("Developer", employee.getPosition());
        assertEquals(75000.0, employee.getSalary());
    }

    @Test
    void shouldCompareEmployeesCorrectly() {
        Employee e1 = new Employee(1L, "Jane", "Smith", "Manager", 90000.0);
        Employee e2 = new Employee(1L, "Jane", "Smith", "Manager", 90000.0);

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }
}
