package com.programandoenjava.desafiomarzo2024.service.booking;

import com.programandoenjava.desafiomarzo2024.entities.Booking;

public interface BookingObserver {
    void updateRoomStatus(Booking booking);
}
