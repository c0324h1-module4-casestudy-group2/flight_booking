package com.group2.case_study.services;

import com.group2.case_study.models.Booking;
import com.group2.case_study.models.User;

import java.util.List;

public interface IBookingService {
    List<Booking> getBookingHistoryByUserId(Integer userId);
}
