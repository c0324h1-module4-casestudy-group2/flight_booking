package com.group2.case_study.repositories;

import com.group2.case_study.models.Flight;
import com.group2.case_study.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, Integer> {

    @Query("SELECT s FROM Seat s WHERE s.flight.flightId = :flightId")
    List<Seat> findSeatsByFlightId(@Param("flightId") Integer flightId);
}
