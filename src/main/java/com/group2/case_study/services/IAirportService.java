package com.group2.case_study.services;

import com.group2.case_study.models.Airport;

import java.util.List;

public interface IAirportService {

    List<Airport> findAll();

    Airport findById(Long AirportId);
}
