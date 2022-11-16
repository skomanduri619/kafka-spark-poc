package com.kafkaspark.poc.controller;

import com.kafkaspark.poc.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/kafka-spark-poc")
public class KafkaSparkPocController {

    private final ProducerService producerService;

    public KafkaSparkPocController(ProducerService producerService) {
        this.producerService = producerService;
    }


    @GetMapping("/run")
    public ResponseEntity<Object> runPoc() throws URISyntaxException, IOException {
            producerService.publishFile();
        return ResponseEntity.ok().body("SUCCESS");
    }
}
