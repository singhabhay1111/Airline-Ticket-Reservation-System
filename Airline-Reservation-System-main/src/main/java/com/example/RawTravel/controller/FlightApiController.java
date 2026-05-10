package com.example.RawTravel.controller;

import com.example.RawTravel.model.Flight;
import com.example.RawTravel.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin("*")
public class FlightApiController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String source,
            @RequestParam String destination) {

        System.out.println("SOURCE = " + source);
        System.out.println("DEST = " + destination);

        return flightService.searchFlights(source, destination);
    }
}