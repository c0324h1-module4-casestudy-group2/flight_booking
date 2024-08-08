package com.group2.case_study.controllers;

import com.group2.case_study.models.Airport;
import com.group2.case_study.models.Booking;
import com.group2.case_study.models.Flight;
import com.group2.case_study.services.IAirportService;
import com.group2.case_study.services.IBookingService;
import com.group2.case_study.services.IFlightService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private IAirportService airportService;

    @Autowired
    private IFlightService flightService;

    @Autowired
    private IBookingService bookingService;

//    @GetMapping
//    public String list(){
//        return "flight/home";
//    }

//    @GetMapping("login")
//    public String login(){
//        return "login/login";
//    }

    @GetMapping("/home")
    public String showSearchForm(Model model, Principal principal) {
        Iterable<Airport> airports = airportService.findAll();
        model.addAttribute("airports", airports);

        if (principal != null) {
            String username = principal.getName(); // Lấy tên người dùng từ Principal
            model.addAttribute("username", username); // Thêm tên người dùng vào mô hình
        }

        return "flight/home";
    }

 

    @GetMapping("/booked")
    public String booked(@RequestParam("booked") String booked, Model model) {
        Booking booking = bookingService.findByCode(booked);
        model.addAttribute("booking", booking);
        return "flight/Tickets-booked";
    }
}
