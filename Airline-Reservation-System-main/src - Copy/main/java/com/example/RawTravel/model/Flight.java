package com.example.RawTravel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airline;
    private String source;
    private String destination;
    private double price;

    // ✅ ADD THIS (IMPORTANT)
    private int seatsAvailable;
}