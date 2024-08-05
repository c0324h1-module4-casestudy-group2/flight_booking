package com.group2.case_study.services.impl;

import com.group2.case_study.models.Flight;
import com.group2.case_study.repositories.IFlightRepository;
import com.group2.case_study.services.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService implements IFlightService {

    @Autowired
    private IFlightRepository flightRepository;

    @Override
    public Flight getFlightById(Integer flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }
}
