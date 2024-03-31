package com.programandoenjava.desafiomarzo2024.service;

import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository repository;
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
    public Room updateRoom(Integer id, Room room) {
        return repository.updateRoom(id, room);
    }

    @Override
    public Boolean deleteRoomById(Integer id) {
        return repository.deleteRoomById(id);
    }
}
