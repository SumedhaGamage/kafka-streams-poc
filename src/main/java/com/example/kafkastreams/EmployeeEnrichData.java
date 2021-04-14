package com.example.kafkastreams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEnrichData {
    private EmployeeDetails employeeDetails;
    private EmployeeHours employeeHours;
}
