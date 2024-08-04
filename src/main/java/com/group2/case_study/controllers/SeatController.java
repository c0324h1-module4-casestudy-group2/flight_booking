package com.group2.case_study.controllers;

import com.group2.case_study.models.Seat;
import com.group2.case_study.services.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SeatController {
    @Autowired
    private ISeatService seatService;

    @GetMapping("/flights/{flightId}/seats")
    public String getSeatByFlightId(@PathVariable Integer flightId, Model model) {
        List<List<Seat>> seatRows = seatService.getSeatsGroupedByRows(flightId);
        model.addAttribute("seatRows", seatRows);
        model.addAttribute("flightId", flightId);
        return "user/seat";
    }
}
