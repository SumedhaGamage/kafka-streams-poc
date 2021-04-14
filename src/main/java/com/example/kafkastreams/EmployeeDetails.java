package com.example.kafkastreams;


import lombok.Data;

@Data
public class EmployeeDetails {
    private String sid;
    private String name;
    private String department;
    private String manager;
}
