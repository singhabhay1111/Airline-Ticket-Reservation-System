package com.example.RawTravel.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FlightApiService {

    @Value("${flight.api.provider}")
    private String provider;

    @Value("${aviation.api.key}")
    private String apiKey;

    public List<Map<String, Object>> searchFlights(String source, String destination) {

        // ✅ USE AVIATION API
        if (provider.equalsIgnoreCase("aviation")) {

            try {
                RestTemplate restTemplate = new RestTemplate();

                String url = "http://api.aviationstack.com/v1/flights?access_key=" + apiKey;

                Map response = restTemplate.getForObject(url, Map.class);

                if (response == null || !response.containsKey("data")) {
                    return new ArrayList<>();
                }

                List<Map<String, Object>> data =
                        (List<Map<String, Object>>) response.get("data");

                return data.subList(0, Math.min(20, data.size()));

            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

        // ✅ FUTURE API (DUMMY FOR NOW)
        else {

            List<Map<String, Object>> dummy = new ArrayList<>();

            dummy.add(Map.of(
                    "airline", Map.of("name", "IndiGo"),
                    "departure", Map.of("airport", source),
                    "arrival", Map.of("airport", destination),
                    "flight_status", "On Time"
            ));

            return dummy;
        }
    }
}