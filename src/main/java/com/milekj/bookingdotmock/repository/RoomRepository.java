package com.milekj.bookingdotmock.repository;

import com.milekj.bookingdotmock.entity.Hotel;
import com.milekj.bookingdotmock.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.TemporalType;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndHotelOwnerUsername(long id, String username);

    @Query("select r from Room r " +
            "where r.hotel.city = ?1 " +
            "and r.maxGuestsNumber >= ?2 " +
            "and r.pricePerNight between ?3 and ?4 " +
            "and not exists (" +
            "   select b from BookingDetails b " +
            "   where b.room = r " +
            "   and b.date between ?5 and ?6)")
    List<Room> findSuitableRooms(String city,
                                 int guestsNumber,
                                 BigDecimal minPrice,
                                 BigDecimal maxPrice,
                                 LocalDate startDate,
                                 LocalDate endDate);
}
