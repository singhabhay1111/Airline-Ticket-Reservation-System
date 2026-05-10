package com.example.RawTravel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponseDTO {
    private Long bookingId;
    private String passengerName;
    private String flightName;
    private int seats;
    private String status;
}