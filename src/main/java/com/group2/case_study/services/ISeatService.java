package com.group2.case_study.services;

import com.group2.case_study.models.Flight;
import com.group2.case_study.models.Seat;

import java.util.List;

public interface ISeatService {

    List<List<Seat>> getSeatsGroupedByRows(Integer flightId);
}
