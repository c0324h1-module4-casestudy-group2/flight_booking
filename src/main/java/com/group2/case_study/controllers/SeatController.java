package com.group2.case_study.controllers;

import com.group2.case_study.models.Seat;
import com.group2.case_study.services.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class SeatController {
    @Autowired
    private ISeatService seatService;

    @GetMapping("/{flightId}/seats")
    public String getSeatByFlightId(@PathVariable Integer flightId, Model model) {
        List<List<Seat>> seatRows = seatService.getSeatsGroupedByRows(flightId);
        model.addAttribute("seatRows", seatRows);
        model.addAttribute("flightId", flightId);
        return "seat/seat";
    }

    @PostMapping("/{flightId}/confirm-booking")
    public String confirmBooking(@PathVariable Integer flightId, @RequestParam("seatIds") List<Integer> seatIds) {
        LocalDateTime holdExpiration = LocalDateTime.now().plusMinutes(1);
        seatService.updateSeatStatus(seatIds, "UNAVAILABLE", holdExpiration);
        return "redirect:/flights/" + flightId + "/seats";
    }
}
