package com.milekj.bookingdotmock.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoomPK implements Serializable {
    private Long hotelId;
    private Long roomNumber;

    public RoomPK() {
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof RoomPK)) return false;
        RoomPK that = (RoomPK) other;
        return hotelId == that.hotelId &&
                roomNumber == that.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, roomNumber);
    }
}
