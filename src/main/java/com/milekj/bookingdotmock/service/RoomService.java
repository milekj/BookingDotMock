package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.dto.RoomSearchDTO;
import com.milekj.bookingdotmock.entity.Room;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomService {
    void save(Room room, long hotelId, String username);
    void edit(Room room, String username);
    void delete(long roomId, String username);
    Room findByIdIfOwned(long roomId, String username);
    List<Room> findSuitableRooms(RoomSearchDTO searchDTO);
}
