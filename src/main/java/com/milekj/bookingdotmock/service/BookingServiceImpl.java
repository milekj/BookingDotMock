package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.dto.BookingDto;
import com.milekj.bookingdotmock.entity.Booking;
import com.milekj.bookingdotmock.entity.BookingDetails;
import com.milekj.bookingdotmock.entity.Customer;
import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.exception.ResourceRestrictedOrNotExistingException;
import com.milekj.bookingdotmock.repository.BookingRepository;
import com.milekj.bookingdotmock.repository.CustomerRepository;
import com.milekj.bookingdotmock.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private CustomerRepository customerRepository;
    private RoomRepository roomRepository;

    @Override
    public void save(String username, BookingDto bookingDto) {
        Customer customer = customerRepository
                .findById(username)
                .orElseThrow(() -> new ResourceRestrictedOrNotExistingException("Customer with given id does not exist."));
        Room room = roomRepository
                .findById(bookingDto.getRoomId())
                .orElseThrow(() -> new ResourceRestrictedOrNotExistingException("Room with given id does not exist"));
        Booking booking = new Booking(customer);
        int guestsNumber = bookingDto.getGuestsNumber();
        LocalDate startDate = bookingDto.getStartDate();
        LocalDate endDate = bookingDto.getEndDate();
        for(LocalDate date = startDate; date.isBefore(endDate) || date.equals(endDate); date = date.plusDays(1)) {
            BookingDetails bookingDetails = new BookingDetails(room, guestsNumber, date);
            booking.addDetails(bookingDetails);
        }
        bookingRepository.save(booking);
    }

    @Autowired
    public void setBookingRepository(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
}
