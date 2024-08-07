package com.group2.case_study.services;

import com.group2.case_study.models.Booking;

public interface IBookingService {
    Booking findById(int flightId);

    void save(Booking booking);

    Booking findByCode(String booked);
}
