package com.example.RawTravel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seats;

    private String status;   // ✅ ADD THIS

    @ManyToOne
    private User user;

    @ManyToOne
    private Flight flight;
}