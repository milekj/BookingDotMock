package com.milekj.bookingdotmock.repository;

import com.milekj.bookingdotmock.entity.Hotel;
import com.milekj.bookingdotmock.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndHotelOwnerUsername(long id, String username);
}
