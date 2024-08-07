package com.group2.case_study.repositories;

import com.group2.case_study.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT * FROM bookings b WHERE b.code_booking = :code", nativeQuery = true)
    Booking findBookingByCode(@Param("code") String code);
}
