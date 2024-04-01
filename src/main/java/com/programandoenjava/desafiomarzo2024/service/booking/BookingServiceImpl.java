package com.programandoenjava.desafiomarzo2024.service.booking;

import com.programandoenjava.desafiomarzo2024.entities.Booking;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.repository.booking.BookingRepository;
import com.programandoenjava.desafiomarzo2024.service.room.RoomService;
import com.programandoenjava.desafiomarzo2024.service.room.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{
    private List<BookingObserver> observers = new ArrayList<>();

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomService roomService;

    @Override
    public List<Booking> allBookings() {
        return bookingRepository.allRooms();
    }

    @Override
    public Optional<Booking> findBookingById(Integer idBooking) {
        return bookingRepository.findBookingById(idBooking);
    }

    @Override
    public Booking saveNewBookingWithIdRoom(Booking booking) {
        double total = 0.0;
        List<Room> rooms = new ArrayList<>();
        addObserver((RoomServiceImpl) roomService);
        for (Room roomId : booking.getRoomList()) {
            Optional<Room> roomOptional = roomService.findRoomById(roomId.getIdRoom());
            if (roomOptional.isPresent()) {
                Room retrievedRoom = roomOptional.get();
                rooms.add(retrievedRoom);
                total += retrievedRoom.getPriceRoomForNigth();
            }
        }
        total *= booking.getDaysOfBooking();
        booking.setTotalPriceBooking(total);
        booking.setRoomList(rooms);

        ((RoomServiceImpl) roomService).updateRoom(booking);

        return bookingRepository.saveNewBookingWithIdRoom(booking);
    }
    private void addObserver(RoomServiceImpl observer) {
        observers.add(observer);
    }

    @Override
    public Booking updateBooking(Integer idBooking, Booking booking) {
        return bookingRepository.updateBooking(idBooking, booking);
    }

    @Override
    public Booking cancelBooking(Integer idBooking) {
        return bookingRepository.cancelBooking(idBooking);
    }

    @Override
    public void deleteBookingById(Integer idBooking) {
        bookingRepository.cancelBooking(idBooking);
    }
}
