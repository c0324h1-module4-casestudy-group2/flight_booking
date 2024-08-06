package com.group2.case_study.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table (name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flightId")
    private Flight flight;

    @Column
    private LocalDateTime bookingDate;

    @Column
    private Integer numberOfSeats;

    @Column
    private Double totalPrice;

    @Column(length = 50)
    private String status;

    @Column(length = 100)
    private String name;

    @Column
    private Integer age;

    @Column(length = 10)
    private String gender;

    @Column(length = 50)
    private String country;

    @Column(length = 6)
    private String codeBooking;
}
