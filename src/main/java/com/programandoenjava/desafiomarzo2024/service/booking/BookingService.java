package com.programandoenjava.desafiomarzo2024.service.booking;

import com.programandoenjava.desafiomarzo2024.entities.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> allBookings();
    Optional<Booking> findBookingById(Integer idBooking);
    Booking saveNewBookingWithIdRoom(Booking booking);
    Booking updateBooking(Integer idBooking, Booking booking);
    Booking cancelBooking(Integer idBooking);
    void deleteBookingById(Integer idBooking);
}
