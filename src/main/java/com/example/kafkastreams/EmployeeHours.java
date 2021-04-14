package com.example.kafkastreams;

import lombok.Data;

@Data
public class EmployeeHours {
    private String sid;
    private Long hours;
}
