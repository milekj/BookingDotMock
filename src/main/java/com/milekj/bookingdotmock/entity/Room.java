package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms", uniqueConstraints = @UniqueConstraint(
        columnNames = {"hotel_id", "roomNumber"}
))
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(length = 5)
    private String roomNumber;

    private Integer maxGuestsNumber;
    private BigDecimal pricePerNight;

    public Room() {
    }

    public Room(Hotel hotel, String roomNumber, Integer maxGuestsNumber, BigDecimal pricePerNight) {
        this.hotel = hotel;
        this.roomNumber = roomNumber;
        this.maxGuestsNumber = maxGuestsNumber;
        this.pricePerNight = pricePerNight;
    }

    public void addHotel(Hotel hotel) {
        setHotel(hotel);
        hotel.addToRooms(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getMaxGuestsNumber() {
        return maxGuestsNumber;
    }

    public void setMaxGuestsNumber(Integer maxGuestsNumber) {
        this.maxGuestsNumber = maxGuestsNumber;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
