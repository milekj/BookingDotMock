package com.milekj.bookingdotmock.controller;

import com.milekj.bookingdotmock.dto.BookingDto;
import com.milekj.bookingdotmock.service.BookingService;
import com.milekj.bookingdotmock.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private BookingService bookingService;

    @GetMapping("/add")
    public String add(@AuthenticationPrincipal UserDetailsImpl userDetails,
                      @RequestParam long roomId,
                      @RequestParam int guestsNumber,
                      @RequestParam LocalDate startDate,
                      @RequestParam LocalDate endDate) {
        BookingDto bookingDto = new BookingDto(roomId, guestsNumber, startDate, endDate);
        bookingService.save(userDetails.getUsername(), bookingDto);
        return "redirect:/";
    }

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
