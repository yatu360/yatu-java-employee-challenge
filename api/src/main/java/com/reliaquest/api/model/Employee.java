package com.reliaquest.api.model;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private double salary;
}
