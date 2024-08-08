package com.group2.case_study.repositories;

import com.group2.case_study.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AirportRepository extends JpaRepository<Airport , Long> {

    @Query("SELECT a FROM Airport a WHERE a.airportCode = :airportCode")
    Airport findByAirportCode(String airportCode);
}
