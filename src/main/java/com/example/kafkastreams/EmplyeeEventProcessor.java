package com.example.kafkastreams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.messaging.handler.annotation.SendTo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBinding(EmployeeProcessor.class)
public class EmplyeeEventProcessor {

    @StreamListener
    @SendTo(EmployeeProcessor.EMPLOYEEHOUROUT)
    public KStream<String, EmployeeEnrichData> processor(
        @Input(EmployeeProcessor.EMPLOYEEHOURINPUT) KStream<String, EmployeeHours> hours,
        @Input(EmployeeProcessor.EMPLOYEEDETAILINPUT) KTable<String, EmployeeDetails> details)
    {        
        hours.foreach((k,v) -> log.info("key: {} value: {}", k,v));
        // ValueJoiner<EmployeeHours, EmployeeDetails, EmployeeEnrichData> joiner = (hour, detail) -> new EmployeeEnrichData(detail, hour);
        
        KStream<String, EmployeeEnrichData> stream = hours.join(details, (hour, detail) -> new EmployeeEnrichData(detail, hour))
        .map(KeyValue::new);

        stream.foreach((a,b) -> log.info("enrich data for sid: {}  data: {}", a, b), Named.as("print"));
        return stream;

    }
    
}
