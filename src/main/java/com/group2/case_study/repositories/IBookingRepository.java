package com.group2.case_study.repositories;

import com.group2.case_study.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByUserId(Integer userId);
}
