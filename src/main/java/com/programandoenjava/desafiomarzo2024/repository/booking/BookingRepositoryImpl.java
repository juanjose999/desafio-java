package com.programandoenjava.desafiomarzo2024.repository.booking;

import com.programandoenjava.desafiomarzo2024.entities.Booking;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class BookingRepositoryImpl implements BookingRepository{

    @Autowired
    private BookingRepositoryJpa bookingRepositoryJpa;

    @Override
    public List<Booking> allBookings() {
        return bookingRepositoryJpa.findAll();
    }
    @Override
    public Optional<Booking> findBookingById(Integer idBooking) {
        return bookingRepositoryJpa.findById(idBooking);
    }

    @Override
    public Booking saveNewBookingWithIdRoom(Booking booking) {
        for(Room room : booking.getRoomList()){
            room.setBooking(booking);
        }
        return bookingRepositoryJpa.save(booking);
    }

    @Override
    public Booking updateBooking(Integer idBooking, Booking booking) {
        Optional<Booking> findBooking = bookingRepositoryJpa.findById(idBooking);
        if(findBooking.isPresent()){
            Booking existingBooking = findBooking.get();
            existingBooking.setFullNameClient(booking.getFullNameClient());
            existingBooking.setDaysOfBooking(booking.getDaysOfBooking());
            existingBooking.setRoomList(booking.getRoomList());
            return bookingRepositoryJpa.save(existingBooking);
        }else {
            throw  new RuntimeException("The booking with id: " + idBooking + " not existing in bata base.");
        }
    }

    @Override
    public Booking cancelBooking(Integer idBooking) {
        Optional<Booking> findBooking = bookingRepositoryJpa.findById(idBooking);
        if(findBooking.isPresent()){
            Booking bookingCancell = findBooking.get();
            bookingCancell.setCancelled(true);
            return bookingRepositoryJpa.save(bookingCancell);
        }else {
            throw new RuntimeException("The booking with id:" + idBooking + " not existing in bata base.");
        }
    }

    @Override
    public Boolean deleteBookingById(Integer idBooking) {
        Optional<Booking> findBooking = bookingRepositoryJpa.findById(idBooking);
        if(findBooking.isPresent()){
            bookingRepositoryJpa.delete(findBooking.get());
            return true;
        }else {
            return false;
        }
    }
    @Override
    public List<Booking> findBookingsByRoomId(Integer idRoom) {
        return bookingRepositoryJpa.findBookingsByRoomId(idRoom);
    }


}
