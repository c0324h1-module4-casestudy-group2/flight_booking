package com.group2.case_study.repositories;

import com.group2.case_study.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByDepartureAirport_AirportIdAndArrivalAirport_AirportId(Long departureAirportId, Long arrivalAirportId);

    @Query("SELECT f FROM Flight f WHERE f.departureAirport.airportId = :departureAirportId AND f.arrivalAirport.airportId = :arrivalAirportId AND f.departureTime >= :departureTime AND f.arrivalTime <= :arrivalTime")
    List<Flight> findFlightByAirportAndDate(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureTime, LocalDateTime arrivalTime);

    @Query("SELECT f FROM Flight f WHERE DATE(f.departureTime) = DATE(:departureTime) AND f.departureAirport.airportCode = :departureAirportCode AND f.arrivalAirport.airportCode = :arrivalAirportCode")
    List<Flight> findFlightByDate(LocalDateTime departureTime, String departureAirportCode, String arrivalAirportCode);

    @Query("SELECT f FROM Flight f WHERE FUNCTION('DATE', f.departureTime) = :localDate AND f.arrivalAirport.airportId = :arrivalAirportId AND f.departureAirport.airportId = :departureAirportId")
    List<Flight> findFlights(
            @Param("localDate") LocalDate localDate,
            @Param("arrivalAirportId") Integer arrivalAirportId,
            @Param("departureAirportId") Integer departureAirportId
    );
}
