package com.group2.case_study.controllers;

import com.group2.case_study.models.Airport;
import com.group2.case_study.models.Flight;
import com.group2.case_study.services.IAirportService;
import com.group2.case_study.services.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private IAirportService airportService;

    @Autowired
    private IFlightService flightService;

//    @GetMapping
//    public String list(){
//        return "flight/home";
//    }

    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    @GetMapping
    public String showSearchForm(Model model) {
        Iterable<Airport> airports =  airportService.findAll();
        model.addAttribute("airports", airports);
        return "flight/home";
    }

    @PostMapping("/searchFlights")
    public String searchFlights(@RequestParam("departureAirportId") Long departureAirportId,
                                @RequestParam("arrivalAirportId") Long arrivalAirportId,
//                                @RequestParam("departure-date") LocalDateTime departureTime,
//                                @RequestParam("return-date") LocalDateTime arrivalTime,
                                Model model) {
        List<Flight> flights;
        flights = flightService.findFlights(departureAirportId, arrivalAirportId);
//        if (departureTime != null && arrivalTime != null) {
//            flights = flightService.findFlightDate(departureAirportId, arrivalAirportId, departureTime, arrivalTime);
//        } else {
//            flights = flightService.findFlights(departureAirportId, arrivalAirportId);
//        }

        Airport departureAirport = airportService.findById(departureAirportId);
        Airport arrivalAirport = airportService.findById(arrivalAirportId);


        model.addAttribute("flights", flights);
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("departureAirport", departureAirport);
        model.addAttribute("arrivalAirport", arrivalAirport);
        return "flight/list";
    }
}
