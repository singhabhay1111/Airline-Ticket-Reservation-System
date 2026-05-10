package com.example.RawTravel.controller;

import com.example.RawTravel.JwtUtil;
import com.example.RawTravel.dto.BookingRequestDTO;
import com.example.RawTravel.dto.BookingResponseDTO;
import com.example.RawTravel.service.BookingService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingResponseDTO bookFlight(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody BookingRequestDTO request) {

        Long userId;

        if (token != null && token.startsWith("Bearer ")) {
            Claims claims = JwtUtil.validateToken(token.replace("Bearer ", ""));
            userId = Long.parseLong(claims.getSubject());
        } else {
            userId = 1L;   // TEMP for testing
        }

        return bookingService.bookFlight(
                userId,
                request.getFlightId(),
                request.getSeats()
        );
    }
}