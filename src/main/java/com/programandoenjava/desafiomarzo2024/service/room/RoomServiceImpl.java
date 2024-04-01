package com.programandoenjava.desafiomarzo2024.service.room;

import com.programandoenjava.desafiomarzo2024.entities.Booking;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.repository.booking.BookingRepository;
import com.programandoenjava.desafiomarzo2024.repository.room.RoomRepository;
import com.programandoenjava.desafiomarzo2024.service.booking.BookingObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class RoomServiceImpl implements RoomService, BookingObserver {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public List<Room> allRooms() {
        return repository.allRooms();
    }

    @Override
    public Optional<Room> findRoomById(Integer idRoom) {
        return repository.findRoomById(idRoom);
    }

    @Override
    public Room saveNewRoom(Room room) {
        return repository.saveNewRoom(room);
    }
    @Override
    public  Boolean isRoomAvailableForBooking(Room room, Booking booking){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime current = LocalDateTime.now();
        String dateCurrent = current.format(formatter);
        String dateInit = booking.getCheckIn();
        String dateEnd = booking.getCheckOut();

        LocalDateTime startDate = LocalDateTime.parse(dateInit, formatter);
        LocalDateTime endDate = LocalDateTime.parse(dateEnd, formatter);
        LocalDateTime actually = LocalDateTime.parse(dateCurrent, formatter);

        Optional<Booking> bookings = bookingRepository.findBookingById(room.getIdRoom());
        for (Booking existingBooking : Booking) {
            if (existingBooking.getIdBooking() != booking.getIdBooking()) {
                LocalDateTime existingStartDate = LocalDateTime.parse(existingBooking.getCheckIn(), formatter);
                LocalDateTime existingEndDate = LocalDateTime.parse(existingBooking.getCheckOut(), formatter);
                if (startDate.isBefore(existingEndDate) && endDate.isAfter(existingStartDate)) {
                    return false; // La habitación no está disponible si hay una reserva que se solapa
                }
            }
        }
        return true;
    }
    @Override
    public void updateRoomStatus(Booking booking) {
        for (Room room : booking.getRoomList()) {
            boolean isAvailable = isRoomAvailableForBooking(room, booking);
            room.setAvailable(isAvailable);
            repository.updateRoom(room.getIdRoom(), room); // Actualiza el estado de la habitación en la base de datos
        }
    }
    @Override
    public Boolean deleteRoomById(Integer id) {
        return repository.deleteRoomById(id);
    }

    @Override
    public void updateRoomStatus(Booking booking) {
        for(Room room : booking.getRoomList()){
            room.setAvailable(false);
            repository.saveNewRoom(room);
        }
    }
}
