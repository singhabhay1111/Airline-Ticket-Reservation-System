package com.example.RawTravel.service;

import com.example.RawTravel.dto.BookingResponseDTO;
import com.example.RawTravel.model.Booking;
import com.example.RawTravel.model.Flight;
import com.example.RawTravel.model.User;
import com.example.RawTravel.repository.BookingRepository;
import com.example.RawTravel.repository.FlightRepository;
import com.example.RawTravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    public BookingResponseDTO bookFlight(Long userId, Long flightId, int seats) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (flight.getSeatsAvailable() < seats) {
            throw new RuntimeException("Not enough seats");
        }

        // reduce seats
        flight.setSeatsAvailable(flight.getSeatsAvailable() - seats);
        flightRepository.save(flight);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setSeats(seats);
        booking.setStatus("CONFIRMED");

        bookingRepository.save(booking);

        // ✅ RETURN DTO
        return BookingResponseDTO.builder()
                .bookingId(booking.getId())
                .passengerName(user.getName())
                .flightName(flight.getAirline())
                .seats(seats)
                .status("CONFIRMED")
                .build();
    }
}