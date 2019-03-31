package com.milekj.bookingdotmock.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

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
    @Length(max = 5, message = "Number length cannot be greater than 5")
    @NotBlank(message = "Number cannot be empty")
    private String roomNumber;

    @Min(value = 1, message = "Guest number cannot be lower than 1")
    @NotNull(message = "Cannot be empty")
    private Integer maxGuestsNumber;

    @DecimalMin("0.0")
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
