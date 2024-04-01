package com.programandoenjava.desafiomarzo2024.service.room;

import com.programandoenjava.desafiomarzo2024.entities.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> allRooms();
    Optional<Room> findRoomById(Integer idRoom);
    Room saveNewRoom(Room room);
    Room isRoomAvalaible(Integer idRoom);
    Room updateRoom(Integer id, Room room);
    Boolean deleteRoomById(Integer id);
}
