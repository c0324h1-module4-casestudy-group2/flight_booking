package com.group2.case_study.services.impl;

import com.group2.case_study.models.Flight;
import com.group2.case_study.repositories.IFlightRepository;
import com.group2.case_study.services.IFlightService;
import com.group2.case_study.services.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<Flight> findFlights(int passengers ,Long departureAirportId, Long arrivalAirportId) {
        List<Flight> flights = flightRepository.findByDepartureAirport_AirportIdAndArrivalAirport_AirportId(departureAirportId, arrivalAirportId);
        List<Flight> flightsToRemove = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Flight flight : flights) {
            int availableSeats = (int) seatService.countAvailableSeatsByFlightId(flight.getFlightId());
            flight.setSeatCapacity(availableSeats);

            if (availableSeats < passengers) {
                flightsToRemove.add(flight);
            }

            if (flight.getDepartureTime().isBefore(now)) {
                flightsToRemove.add(flight);
            }
        }
        flights.removeAll(flightsToRemove);

        return flights;
    }

    @Override
    public List<Flight> findFlightDate(int passengers ,Long departureAirportId, Long arrivalAirportId, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        List<Flight> flights = flightRepository.findFlightByAirportAndDate(departureAirportId, arrivalAirportId, departureTime, arrivalTime);
        List<Flight> flightsToRemove = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Flight flight : flights) {
            int availableSeats = (int) seatService.countAvailableSeatsByFlightId(flight.getFlightId());
            flight.setSeatCapacity(availableSeats);

            if (availableSeats < passengers) {
                flightsToRemove.add(flight);
            }

            if (flight.getDepartureTime().isBefore(now)) {
                flightsToRemove.add(flight);
            }
        }
        flights.removeAll(flightsToRemove);

        return flights;
    }

    @Override
    public List<Flight> findFlightsByDateAndAirports(int passengers , LocalDateTime departureTime, String departureAirportCode, String arrivalAirportCode) {
        List<Flight> flights = flightRepository.findFlightByDate(departureTime,departureAirportCode,arrivalAirportCode);
        List<Flight> flightsToRemove = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Flight flight : flights) {
            int availableSeats = (int) seatService.countAvailableSeatsByFlightId(flight.getFlightId());
            flight.setSeatCapacity(availableSeats);

            if (availableSeats < passengers) {
                flightsToRemove.add(flight);
            }

            if (flight.getDepartureTime().isBefore(now)) {
                flightsToRemove.add(flight);
            }
        }
        flights.removeAll(flightsToRemove);

        return flights;
    }

    @Override
    public Flight findById(int flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }
}
