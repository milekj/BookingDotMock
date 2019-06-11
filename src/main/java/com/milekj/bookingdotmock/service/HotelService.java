package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.Hotel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HotelService {
    void saveIfOwned(Hotel hotel, String username);
    void updateIfOwned(Hotel hotel, String username);
    void deleteById(long hotelId, String username);
    Hotel findByIdIfOwned(long id, String username);
    List<Hotel> findByOwnerUsername(String username);
}
