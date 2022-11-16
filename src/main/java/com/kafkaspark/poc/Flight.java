package com.kafkaspark.poc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    private String flightNumber;
    private String departure;
    private String arrival;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private String action;
}
