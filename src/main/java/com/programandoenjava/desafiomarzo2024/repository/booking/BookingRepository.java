package com.programandoenjava.desafiomarzo2024.repository.booking;

import com.programandoenjava.desafiomarzo2024.entities.Booking;
import com.programandoenjava.desafiomarzo2024.entities.Room;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    List<Booking> allBookings();
    Optional<Booking> findBookingById(Integer idBooking);
    List<Booking> findBookingsByRoomId(Integer idRoom);
    Booking saveNewBookingWithIdRoom(Booking booking);
    Booking updateBooking(Integer idBooking, Booking booking);
    Booking cancelBooking(Integer idBooking);
    Boolean deleteBookingById(Integer idBooking);

}
