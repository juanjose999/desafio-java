package com.programandoenjava.desafiomarzo2024.repository.booking;

import com.programandoenjava.desafiomarzo2024.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepositoryJpa extends JpaRepository<Booking, Integer>{
    List<Booking> findBookingsByRoomId(Integer idRoom);
}
