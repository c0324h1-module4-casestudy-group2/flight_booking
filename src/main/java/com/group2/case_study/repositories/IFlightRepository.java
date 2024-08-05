package com.group2.case_study.repositories;

import com.group2.case_study.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByDepartureAirport_AirportIdAndArrivalAirport_AirportId(Long departureAirportId, Long arrivalAirportId);
}
