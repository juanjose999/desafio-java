package com.programandoenjava.desafiomarzo2024.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    private Integer idRoom;
    @Column(name = "type_room")
    private String typeRoom;
    @Column(name = "price_room_nigth")
    private Double priceRoomForNigth;
    @ManyToOne(targetEntity = Booking.class)
    @JoinColumn(name = "id_booking")
    @JsonIgnore
    private Booking booking;
    private Boolean available = true;
}
