package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.dto.BookingDto;

public interface BookingService {
    void save(String username, BookingDto bookingDto);
}
