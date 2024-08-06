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
    public List<Booking> getBookingHistoryByUserId(Integer userId) {
        return bookingRepository.findAllByUserId(userId);
    }
}
