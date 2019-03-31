package com.milekj.bookingdotmock.dto;

import java.time.LocalDate;

public class BookingDto {
    private long roomId;
    private int guestsNumber;
    private LocalDate startDate;
    private LocalDate endDate;

    public BookingDto(long roomId, int guestsNumber, LocalDate startDate, LocalDate endDate) {
        this.roomId = roomId;
        this.guestsNumber = guestsNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(int guestsNumber) {
        this.guestsNumber = guestsNumber;
    }
}
