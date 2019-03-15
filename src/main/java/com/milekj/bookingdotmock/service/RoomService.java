package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.Hotel;
import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.exception.ResourceRestrictedOrNotExistingException;
import com.milekj.bookingdotmock.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomService {
    private HotelService hotelService;
    private RoomRepository roomRepository;

    @Transactional
    public void addRoomToHotel(Room room, long hotelId) {
        Hotel hotel = hotelService.findById(hotelId);
        room.addHotel(hotel);
    }

    @Transactional(readOnly = true)
    public Room findByIdIfOwned (long roomId, String username) {
        return roomRepository
                .findByIdAndHotelOwnerUsername(roomId, username)
                .orElseThrow(() -> new ResourceRestrictedOrNotExistingException("Room with given id does not exists or is not owned by user"));
    }

    @Transactional
    public void saveOrUpdate(Room room, String username) {
        Room dbRoom = findByIdIfOwned(room.getId(), username);
        room.setHotel(dbRoom.getHotel());
        roomRepository.save(room);
    }

    @Transactional
    public void deleteById(long roomId, String username) {
        Room dbRoom = findByIdIfOwned(roomId, username);
        roomRepository.deleteById(roomId);
    }

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
}
