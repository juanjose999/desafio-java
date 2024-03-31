package com.programandoenjava.desafiomarzo2024.repository;

import com.programandoenjava.desafiomarzo2024.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepositoryJpa extends JpaRepository<Room, Integer> {
}
