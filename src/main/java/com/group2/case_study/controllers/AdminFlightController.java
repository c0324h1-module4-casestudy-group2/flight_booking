package com.group2.case_study.controllers;

import com.group2.case_study.models.Airport;
import com.group2.case_study.models.Flight;
import com.group2.case_study.services.IAdminAirportService;
import com.group2.case_study.services.IAdminFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/flights")
public class AdminFlightController {

    @Autowired
    private IAdminFlightService adminFlightService;

    @Autowired
    private IAdminAirportService adminAirportService;

    @GetMapping
    public String listFlights(Model model) {
        List<Flight> flights = adminFlightService.getAllFlights();
        model.addAttribute("flights", flights);
        return "admin/flight/flight-management";
    }

    @GetMapping("/new")
    public String createFlightForm(Model model) {
        Flight flight = new Flight();
        List<Airport> airports = adminAirportService.getAllAirports();
        model.addAttribute("flight", flight);
        model.addAttribute("airports", airports);
        return "admin/flight/flight-form-new";
    }

    @PostMapping("/new")
    public String saveFlight(@ModelAttribute("flight") Flight flight, RedirectAttributes redirectAttributes, Model model) {
        if (flight.getDepartureAirport().equals(flight.getArrivalAirport())) {
            model.addAttribute("error", "Sân bay khởi hành và sân bay đến không được trùng nhau.");
            List<Airport> airports = adminAirportService.getAllAirports();
            model.addAttribute("airports", airports);
            return "admin/flight/flight-form-new";
        }
        if (flight.getDepartureTime().isAfter(flight.getArrivalTime()) || flight.getDepartureTime().equals(flight.getArrivalTime())) {
            model.addAttribute("error", "Thời gian khởi hành và thời gian đến không hợp lệ.");
            List<Airport> airports = adminAirportService.getAllAirports();
            model.addAttribute("airports", airports);
            return "admin/flight/flight-form-new";
        }
        adminFlightService.saveFlight(flight);
        redirectAttributes.addFlashAttribute("message", "Thêm chuyến bay thành công!");
        return "redirect:/admin/flights";
    }

    @GetMapping("/edit/{id}")
    public String editFlightForm(@PathVariable Integer id, Model model) {
        Flight flight = adminFlightService.getFlightById(id);
        if (flight == null) {
            return "redirect:/admin/flights";
        }
        List<Airport> airports = adminAirportService.getAllAirports();
        model.addAttribute("flight", flight);
        model.addAttribute("airports", airports);
        return "admin/flight/flight-form-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateFlight(@PathVariable Integer id, @ModelAttribute("flight") Flight flight, RedirectAttributes redirectAttributes, Model model) {
        if (flight.getDepartureAirport().equals(flight.getArrivalAirport())) {
            model.addAttribute("error", "Sân bay khởi hành và sân bay đến không được trùng nhau.");
            List<Airport> airports = adminAirportService.getAllAirports();
            model.addAttribute("airports", airports);
            return "admin/flight/flight-form-edit";
        }
        if (flight.getDepartureTime().isAfter(flight.getArrivalTime()) || flight.getDepartureTime().equals(flight.getArrivalTime())) {
            model.addAttribute("error", "Thời gian khởi hành và thời gian đến không hợp lệ.");
            List<Airport> airports = adminAirportService.getAllAirports();
            model.addAttribute("airports", airports);
            return "admin/flight/flight-form-edit";
        }
        flight.setFlightId(id);
        adminFlightService.saveFlight(flight);
        redirectAttributes.addFlashAttribute("message", "Cập nhật chuyến bay thành công!");
        return "redirect:/admin/flights";
    }

    @PostMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        adminFlightService.deleteFlight(id);
        redirectAttributes.addFlashAttribute("message", "Xóa chuyến bay thành công!");
        return "redirect:/admin/flights";
    }

    @GetMapping("/view/{id}")
    public String viewFlight(@PathVariable Integer id, Model model) {
        Flight flight = adminFlightService.getFlightById(id);
        if (flight == null) {
            return "redirect:/admin/flights";
        }
        model.addAttribute("flight", flight);
        return "admin/flight/flight-form-view";
    }
}
