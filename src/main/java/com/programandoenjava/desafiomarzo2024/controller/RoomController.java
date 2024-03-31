package com.programandoenjava.desafiomarzo2024.controller;

import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> allRooms(){
        List<Room> roomList = new ArrayList<>();
        roomList.addAll(roomService.allRooms());
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findRomById(@PathVariable Integer id){
        try{
            Optional<Room> findRoomById = roomService.findRoomById(id);
            if (findRoomById.isPresent()){
                return new ResponseEntity(roomService.findRoomById(id),HttpStatus.OK);
            }else {
                throw new RuntimeException("The room not existing in data base.");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/save-room")
    public ResponseEntity<Room> saveRoom(@RequestBody Room room){
        try{
            return new ResponseEntity<>(roomService.saveNewRoom(room),HttpStatus.CREATED);
        }catch (Exception e){
            throw new RuntimeException("Error in save new room.");
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Integer id, @RequestBody Room room){
        try{
            return new ResponseEntity<>(roomService.updateRoom(id,room),HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Error in update Room new room.");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteRoomById(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(roomService.deleteRoomById(id),HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Error in delete Room new room.");
        }
    }

}
