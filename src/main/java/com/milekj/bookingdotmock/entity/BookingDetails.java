package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private int guestsNumber;

    private Date date;

    public BookingDetails() {

    }

    public BookingDetails(Booking booking, Room room, int guestsNumber, Date date) {
        this.booking = booking;
        this.room = room;
        this.guestsNumber = guestsNumber;
        this.date = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
