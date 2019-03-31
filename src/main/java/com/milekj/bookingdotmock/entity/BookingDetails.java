package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"room_id", "date"}
))
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private int guestsNumber;

    private LocalDate date;

    public BookingDetails() {

    }

    public BookingDetails(Room room, int guestsNumber, LocalDate date) {
        this.room = room;
        this.guestsNumber = guestsNumber;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(int guestsNumber) {
        this.guestsNumber = guestsNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
