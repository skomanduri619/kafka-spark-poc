package com.kafkaspark.poc.service;

import com.kafkaspark.poc.Flight;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Consumer {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private SQLContext sqlContext;

    @KafkaListener(topics = "poc-events",groupId = "group_id")
    public void consumeMessage(String message){
        try {
            JavaRDD<String> flight = sc.parallelize(Arrays.asList(message.split("-")));
            JavaRDD<Flight> flights = flight.map((Function<String, Flight>) v1 -> {
                String[] data = v1.split(",");
                Flight.FlightBuilder builder = Flight.builder().flightNumber(data[0])
                        .departure(data[1])
                        .arrival(data[2])
                        .departureDate(data[3])
                        .departureTime(data[4])
                        .arrivalTime(data[5])
                        .action(data[6]);
                return builder.build();
            });

            //Converitng to flight dataset
            Dataset<Flight> flightDataset =  sqlContext.createDataset(flights.rdd(), Encoders.bean(Flight.class));

            flightDataset.filter("action = 'CANCEL'").show();
            System.out.println(flights);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}