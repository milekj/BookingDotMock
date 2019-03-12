package com.milekj.bookingdotmock.repository;

import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.entity.RoomPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, RoomPK> {
}
