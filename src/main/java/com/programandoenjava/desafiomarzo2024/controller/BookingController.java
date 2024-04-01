package com.programandoenjava.desafiomarzo2024.controller;

import com.programandoenjava.desafiomarzo2024.entities.Booking;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.service.booking.BookingService;
import com.programandoenjava.desafiomarzo2024.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Booking>> allBookings(){
        try {
            List<Booking> bookingList = bookingService.allBookings();
            if(!bookingList.isEmpty()){
                return new ResponseEntity<>(bookingList,HttpStatus.OK);
            }else {
                return new ResponseEntity("The booking list is empty",HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity("Error in get all bookings.",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Booking> findBookingById(@PathVariable Integer idBooking){
        try {
            return new ResponseEntity(bookingService.findRoomById(idBooking),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error in find booking by id.",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save-booking")
    public ResponseEntity<Booking> saveNewBooking(@RequestBody Booking booking){
        try{
            return new ResponseEntity<>(bookingService.saveNewBookingWithIdRoom(booking), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity("Error in save new booking.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Booking> updateBooking(@PathVariable Integer idBooking, @RequestBody Booking booking){
        try {
            return new ResponseEntity<>(bookingService.updateRoom(idBooking,booking),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error in save new booking.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Integer idBooking){
        try {
            return new ResponseEntity<>(bookingService.cancelBooking(idBooking),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error in cancel booking.",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteBooking(@PathVariable Integer idBooking){
        try{
            return new ResponseEntity(bookingService.cancelBooking(idBooking),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error in delete booking.",HttpStatus.NOT_FOUND);
        }
    }

}
