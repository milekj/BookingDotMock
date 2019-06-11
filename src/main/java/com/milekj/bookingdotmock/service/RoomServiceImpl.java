package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.dto.RoomSearchDTO;
import com.milekj.bookingdotmock.entity.Hotel;
import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.exception.ResourceRestrictedOrNotExistingException;
import com.milekj.bookingdotmock.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private HotelService hotelService;
    private RoomRepository roomRepository;

    @Override
    @Transactional
    public void save(Room room, long hotelId, String username) {
        Hotel hotel = hotelService.findByIdIfOwned(hotelId, username);
        room.addHotel(hotel);
    }

    @Override
    @Transactional
    public void edit(Room room, String username) {
        Room dbRoom = findByIdIfOwned(room.getId(), username);
        room.setHotel(dbRoom.getHotel());
        roomRepository.save(room);
    }

    @Override
    @Transactional
    public void delete(long roomId, String username) {
        findByIdIfOwned(roomId, username);
        roomRepository.deleteById(roomId);
    }

    @Override
    @Transactional(readOnly = true)
    public Room findByIdIfOwned(long roomId, String username) {
        return roomRepository
                .findByIdAndHotelOwnerUsername(roomId, username)
                .orElseThrow(() -> new ResourceRestrictedOrNotExistingException("Room with given id does not exists or is not owned by user"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> findSuitableRooms(RoomSearchDTO searchDTO) {
        return roomRepository.findSuitableRooms(searchDTO.getCity(),
                searchDTO.getGuestsNumber(),
                searchDTO.getMinPrice(),
                searchDTO.getMaxPrice(),
                searchDTO.getStartDate(),
                searchDTO.getStartDate());
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
