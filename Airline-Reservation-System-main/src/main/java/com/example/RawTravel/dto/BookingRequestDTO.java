package com.example.RawTravel.dto;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private Long flightId;
    private int seats;
}