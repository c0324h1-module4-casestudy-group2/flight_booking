package com.group2.case_study.services;

import com.group2.case_study.models.Flight;

import java.util.List;

public interface IFlightService {
    Flight getFlightById(Integer flightId);

    List<Flight> findFlights(Long departureAirportId, Long arrivalAirportId);
}
