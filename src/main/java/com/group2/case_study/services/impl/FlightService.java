package com.group2.case_study.services.impl;

import com.group2.case_study.models.Flight;
import com.group2.case_study.repositories.IFlightRepository;
import com.group2.case_study.services.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService implements IFlightService {

    @Autowired
    private IFlightRepository flightRepository;

    @Override
    public Flight getFlightById(Integer flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    @Override
    public List<Flight> findFlights(Long departureAirportId, Long arrivalAirportId) {
        return flightRepository.findByDepartureAirport_AirportIdAndArrivalAirport_AirportId(departureAirportId,arrivalAirportId);
    }
//
//    @Override
//    public List<Flight> findFlightDate(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureTime, LocalDateTime arrivalTime) {
//        return flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateBetween(departureAirportId, arrivalAirportId, departureTime, arrivalTime);
//    }
}
