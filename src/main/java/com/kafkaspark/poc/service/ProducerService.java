package com.kafkaspark.poc.service;

import com.kafkaspark.poc.kafka.KafkaProducer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ProducerService {

    private final KafkaProducer kafkaProducer;

    public ProducerService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void publishFile() throws URISyntaxException, IOException {
        URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource("flights.csv")).toURI();
        try(Stream<String> lines = Files.lines(Paths.get(uri))) {
            lines.forEach(kafkaProducer::sendMessage);
        }
    }
}
