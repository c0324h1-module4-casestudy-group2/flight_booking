package com.group2.case_study.services.impl;

import com.group2.case_study.models.Flight;
import com.group2.case_study.repositories.IFlightRepository;
import com.group2.case_study.services.IFlightService;
import com.group2.case_study.services.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService implements IFlightService {

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private ISeatService seatService;


    @Override
    public Flight getFlightById(Integer flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    @Override
    public List<Flight> findFlights(Long departureAirportId, Long arrivalAirportId) {
        List<Flight> flights = flightRepository.findByDepartureAirport_AirportIdAndArrivalAirport_AirportId(departureAirportId, arrivalAirportId);
        for (Flight flight : flights) {
            int availableSeats = (int) seatService.countAvailableSeatsByFlightId(flight.getFlightId());
            flight.setSeatCapacity(availableSeats);
        }
        return flights;
    }

    @Override
    public List<Flight> findFlightDate(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        List<Flight> flights = flightRepository.findFlightByAirportAndDate(departureAirportId, arrivalAirportId, departureTime, arrivalTime);
        for (Flight flight : flights) {
            int availableSeats = (int) seatService.countAvailableSeatsByFlightId(flight.getFlightId());
            flight.setSeatCapacity(availableSeats);
        }
        return flights;
    }

    @Override
    public List<Flight> findFlightsByDateAndAirports(LocalDateTime departureTime, String departureAirportCode, String arrivalAirportCode) {
        List<Flight> flights = flightRepository.findFlightByDate(departureTime,departureAirportCode,arrivalAirportCode);
        for (Flight flight : flights) {
            int availableSeats = (int) seatService.countAvailableSeatsByFlightId(flight.getFlightId());
            flight.setSeatCapacity(availableSeats);
        }
        return flights;
    }
}
