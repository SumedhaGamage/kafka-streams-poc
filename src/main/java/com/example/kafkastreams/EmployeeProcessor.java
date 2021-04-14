package com.example.kafkastreams;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface EmployeeProcessor {

     String EMPLOYEEHOURINPUT = "employee_hours";
     String EMPLOYEEDETAILINPUT = "employee_details";
     String EMPLOYEEHOUROUT = "employee_enrich";

     @Input(EMPLOYEEHOURINPUT)
     KStream<String, Long> employeeHours();

     @Input(EMPLOYEEDETAILINPUT)
     KTable<String, String> employeeDetails();

     @Output(EMPLOYEEHOUROUT)
     KStream<String, String> employeeOutput();

}
