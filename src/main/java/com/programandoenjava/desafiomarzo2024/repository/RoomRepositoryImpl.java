package com.programandoenjava.desafiomarzo2024.repository;

import com.programandoenjava.desafiomarzo2024.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class RoomRepositoryImpl implements RoomRepository{

    @Autowired
    private RoomRepositoryJpa roomRepositoryJpa;

    @Override
    public List<Room> allRooms() {
        return roomRepositoryJpa.findAll();
    }

    @Override
    public Optional<Room> findRoomById(Integer idRoom) {
        return roomRepositoryJpa.findById(idRoom);
    }

    @Override
    public Room saveNewRoom(Room room) {
        return roomRepositoryJpa.save(room);
    }

    @Override
    public Room updateRoom(Integer id, Room room) {
        Optional<Room> findRoomById = roomRepositoryJpa.findById(id);
        if(findRoomById.isPresent()){
            Room oldRoom = findRoomById.get();
            oldRoom.setTypeRoom(room.getTypeRoom());
            oldRoom.setPriceRoom(room.getPriceRoom());
            return roomRepositoryJpa.save(oldRoom);
        }else {
            throw new RuntimeException("The room with id: " + id + " not exist in data base.");
        }
    }

    @Override
    public Boolean deleteRoomById(Integer id) {
        Optional<Room> findRoomById = roomRepositoryJpa.findById(id);
        if(findRoomById.isPresent()){
            roomRepositoryJpa.delete(findRoomById.get());
            return true;
        }else {
            return false;
        }
    }
}
