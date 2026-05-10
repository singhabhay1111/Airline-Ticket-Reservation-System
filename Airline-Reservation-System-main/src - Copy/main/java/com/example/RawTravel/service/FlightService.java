package com.example.RawTravel.service;

import com.example.RawTravel.model.Flight;
import com.example.RawTravel.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepo;

    public List<Flight> searchFlights(String source, String destination) {

        List<Flight> flights =
                flightRepo.findBySourceContainingIgnoreCaseAndDestinationContainingIgnoreCase(source, destination);

        if (flights.isEmpty()) {
            return List.of(
                    new Flight(null, "IndiGo", source, destination, 4500, 50),
                    new Flight(null, "Air India", source, destination, 5500, 40)
            );
        }

        return flights;
    }
}