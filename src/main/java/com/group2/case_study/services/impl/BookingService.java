package com.group2.case_study.services.impl;

import com.group2.case_study.models.Booking;
import com.group2.case_study.repositories.IBookingRepository;
import com.group2.case_study.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private IBookingRepository bookingRepository;
    @Override
    public Booking findById(int flightId) {
        return bookingRepository.findById(flightId).get();
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking findByCode(String booked) {
        return bookingRepository.findBookingByCode(booked);
    }

    @Override
    public List<Booking> getBookingHistoryByUserId(Integer userId) {
        return bookingRepository.findAllByUserId(userId);
    }
}
