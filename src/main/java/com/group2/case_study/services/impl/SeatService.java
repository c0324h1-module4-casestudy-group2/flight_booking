package com.group2.case_study.services.impl;

import com.group2.case_study.models.Seat;
import com.group2.case_study.repositories.ISeatRepository;
import com.group2.case_study.services.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService implements ISeatService {
    @Autowired
    private ISeatRepository seatRepository;

    @Override
    public List<List<Seat>> getSeatsGroupedByRows(Integer flightId) {
        List<Seat> seats = seatRepository.findSeatsByFlightId(flightId);

        // Nhóm các ghế thành các hàng 6 ghế
        List<List<Seat>> seatRows = new ArrayList<>();
        for (int i = 0; i < seats.size(); i += 6) {
            seatRows.add(seats.subList(i, Math.min(i + 6, seats.size())));
        }

        return seatRows;
    }
}
