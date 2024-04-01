package com.programandoenjava.desafiomarzo2024.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Integer idBooking;

    private String fullNameClient;

    private Integer daysOfBooking;

    @Column(name = "check_in")
    private String checkIn;

    @Column(name = "check_out")
    private String checkOut;

    @OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "booking")
    private List<Room> roomList;

    private Boolean cancelled = false;

    private double totalPriceBooking;

    public Booking(String fullNameClient,Integer daysOfBooking, String checkIn, String checkOut, List<Room> roomList, double totalPriceBooking) {
        this.fullNameClient = fullNameClient;
        this.daysOfBooking = daysOfBooking;
        this.roomList = roomList;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cancelled = false;
        this.totalPriceBooking = totalPriceBooking;
    }

}
