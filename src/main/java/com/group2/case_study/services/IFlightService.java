package com.group2.case_study.services;

import com.group2.case_study.models.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IFlightService {
    Flight getFlightById(Integer flightId);

    List<Flight> findFlights(int passengers ,Long departureAirportId, Long arrivalAirportId);

    List<Flight> findFlightDate(int passengers ,Long departureAirportId, Long arrivalAirportId, LocalDateTime departureTime, LocalDateTime arrivalTime);

    List<Flight> findFlightsByDateAndAirports(int passengers ,LocalDateTime departureTime, String departureAirportCode, String arrivalAirportCode);

    Flight findById(int flightId);

    List<Flight> findAll();

    List<Flight> findAllFlights(LocalDate localDate, Integer arrivalAirportId, Integer departureAirportId);
}
