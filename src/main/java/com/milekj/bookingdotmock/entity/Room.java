package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
public class Room {
    @EmbeddedId
    private RoomPK id;

    @ManyToOne
    @MapsId("hotelId")
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private Integer maxGuestsNumber;
    private BigDecimal pricePerNight;

    public Room() {
        System.out.println("nowy room");
    }

    public Room(RoomPK id, int maxGuestsNumber, BigDecimal pricePerNight) {
        this.id = id;
        this.maxGuestsNumber = maxGuestsNumber;
        this.pricePerNight = pricePerNight;
    }

    public void addHotel(Hotel hotel) {
        setHotel(hotel);
        hotel.addToRooms(this);
    }

    public RoomPK getId() {
        return id;
    }

    public void setId(RoomPK id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
