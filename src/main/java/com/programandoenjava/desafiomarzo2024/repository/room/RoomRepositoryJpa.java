package com.programandoenjava.desafiomarzo2024.repository.room;

import com.programandoenjava.desafiomarzo2024.entities.Booking;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepositoryJpa extends JpaRepository<Room, Integer> {

}
