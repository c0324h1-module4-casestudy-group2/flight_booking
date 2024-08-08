package com.group2.case_study.controllers;

import com.group2.case_study.models.Airport;
import com.group2.case_study.models.Booking;
import com.group2.case_study.models.Flight;
import com.group2.case_study.services.IAirportService;
import com.group2.case_study.services.IBookingService;
import com.group2.case_study.services.IFlightService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private IAirportService airportService;

    @Autowired
    private IFlightService flightService;

    @Autowired
    private IBookingService bookingService;

    @GetMapping("/home")
    public String showSearchForm(Model model, Principal principal) {
        Iterable<Airport> airports = airportService.findAll();
        List<Flight> flights = flightService.findAll();
        model.addAttribute("airports", airports);
        model.addAttribute("flights", flights);
        if (principal != null) {
            String username = principal.getName(); // Lấy tên người dùng từ Principal
            model.addAttribute("username", username); // Thêm tên người dùng vào mô hình
        }
        return "flight/home";
    }

//    @PostMapping("/flightDate")
//    public String searchFlightDate(
//            @RequestParam("selectedDate") String selectedDate,
//            @RequestParam("departureAirportCode") String departureAirportCode,
//            @RequestParam("arrivalAirportCode") String arrivalAirportCode,
//            @RequestParam("passengers") int passengers,
//            Model model) {
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
//
//        LocalDateTime departureTime = null;
//        departureTime = LocalDateTime.parse(selectedDate, formatter);
//
//        List<Flight> flights = flightService.findFlightsByDateAndAirports(passengers , departureTime, departureAirportCode, arrivalAirportCode);
//
//        Airport departureAirport = airportService.findByAirportCode(departureAirportCode);
//        Airport arrivalAirport = airportService.findByAirportCode(arrivalAirportCode);
//
//        model.addAttribute("passengers" , passengers);
//        model.addAttribute("flights", flights);
//        model.addAttribute("departureAirportCode", departureAirportCode);
//        model.addAttribute("arrivalAirportCode", arrivalAirportCode);
//        model.addAttribute("departureAirport", departureAirport);
//        model.addAttribute("arrivalAirport", arrivalAirport);
//        return "flight/list";
//    }

    @PostMapping("/flights/search")
    public String searchFlights(@RequestParam ("departureAirportId") Integer departureAirportId,
                                @RequestParam ("arrivalAirportId") Integer arrivalAirportId,
                                @RequestParam ("departure-date") String departureDate,
                                @RequestParam ("passengers") Integer passengers,
                                Model model
                                ) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(departureDate, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd-MM-yyyy.");
        }
        Airport departureAirport = airportService.findById(departureAirportId);
        Airport arrivalAirport = airportService.findById(arrivalAirportId);
        List<Flight> flights = flightService.findAllFlights(localDate, arrivalAirportId, departureAirportId);
        model.addAttribute("flights", flights);
        model.addAttribute("passengers" , passengers);
        model.addAttribute("departureAirport", departureAirport);
        model.addAttribute("arrivalAirport", arrivalAirport);
        return "flight/list";
    }


//    @PostMapping("/flights")
//    public String searchFlights(@RequestParam("departureAirportId") Long departureAirportId,
//                                @RequestParam("arrivalAirportId") Long arrivalAirportId,
//                                @RequestParam(value = "departure-date", defaultValue = "") String departureDateStr,
//                                @RequestParam(value = "return-date", defaultValue = "") String returnDateStr,
//                                @RequestParam("passengers") int passengers,
//                                @RequestParam("userName") String userName,
//                                HttpSession session,
//                                Model model) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//
//        LocalDateTime departureTime = null;
//        LocalDateTime arrivalTime = null;
//
//        if (!departureDateStr.isEmpty()) {
//            departureTime = LocalDateTime.parse(departureDateStr, formatter);
//        }
//        if (!returnDateStr.isEmpty()) {
//            arrivalTime = LocalDateTime.parse(returnDateStr, formatter);
//        }
//
//        List<Flight> flights;
////        flights = flightService.findFlights(departureAirportId, arrivalAirportId);
//        if (departureTime != null && arrivalTime != null) {
//            flights = flightService.findFlightDate(passengers, departureAirportId, arrivalAirportId, departureTime, arrivalTime);
//        } else {
//            flights = flightService.findFlights(passengers, departureAirportId, arrivalAirportId);
//        }
//
//        Airport departureAirport = airportService.findById(departureAirportId);
//        Airport arrivalAirport = airportService.findById(arrivalAirportId);
//
//        model.addAttribute("passengers" , passengers);
//        model.addAttribute("flights", flights);
//        model.addAttribute("airports", airportService.findAll());
//        model.addAttribute("departureAirport", departureAirport);
//        model.addAttribute("arrivalAirport", arrivalAirport);
//        session.setAttribute("userName", userName);
//        return "flight/list";
//    }

    @GetMapping("/booked")
    public String booked(@RequestParam("booked") String booked, Model model) {
        Booking booking = bookingService.findByCode(booked);
        model.addAttribute("booking", booking);
        return "flight/Tickets-booked";
    }

    @GetMapping("/introduction")
    public String introduction() {
        return "flight/introduction";
    }
}
