package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.dto.RoomFormDTO;
import com.milekj.bookingdotmock.entity.Hotel;
import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.repository.HotelRepository;
import com.milekj.bookingdotmock.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomService {
    private HotelService hotelService;

    @Transactional
    public void addRoomToHotel(Room room, long hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        room.addHotel(hotel);
    }

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }
}
