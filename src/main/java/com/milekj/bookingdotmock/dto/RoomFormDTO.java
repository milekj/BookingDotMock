package com.milekj.bookingdotmock.dto;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class RoomFormDTO {
    private long hotelId;

    private long roomNumber;

    @Min(0)
    private int maxGuestsNumber;

    @Min(0)
    private BigDecimal pricePerNight;

    public RoomFormDTO() {
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getMaxGuestsNumber() {
        return maxGuestsNumber;
    }

    public void setMaxGuestsNumber(int maxGuestsNumber) {
        this.maxGuestsNumber = maxGuestsNumber;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
