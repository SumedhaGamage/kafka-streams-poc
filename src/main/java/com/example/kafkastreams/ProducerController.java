package com.example.kafkastreams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class ProducerController {

    @Autowired
    StreamBridge streamBridge;

    @GetMapping(path = "/{message}")
    public ResponseEntity<String> name(@PathVariable("message") String message) {
        streamBridge.send(EmployeeProcessor.EMPLOYEEHOURINPUT, message);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
}
