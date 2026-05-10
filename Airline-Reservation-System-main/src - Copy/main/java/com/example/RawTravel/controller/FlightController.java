package com.example.RawTravel.controller;

import com.example.RawTravel.JwtUtil;
import com.example.RawTravel.model.Flight;
import com.example.RawTravel.repository.FlightRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin("*")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping
    public Flight addFlight(
            @RequestHeader("Authorization") String token,
            @RequestBody Flight flight) {

        Claims claims = JwtUtil.validateToken(token.replace("Bearer ", ""));
        String role = (String) claims.get("role");

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Access Denied ❌");
        }

        return flightRepository.save(flight);
    }

    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String source,
            @RequestParam String destination) {

        return flightRepository
                .findBySourceContainingIgnoreCaseAndDestinationContainingIgnoreCase(source, destination);
    }
}