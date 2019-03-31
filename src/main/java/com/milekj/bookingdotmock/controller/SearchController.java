package com.milekj.bookingdotmock.controller;

import com.milekj.bookingdotmock.dto.RoomSearchDTO;
import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SearchController {
    private RoomService roomService;

    @GetMapping("/search")
    public String searchForRooms(Model model) {
        model.addAttribute("roomSearchDto", new RoomSearchDTO());
        return "search-rooms";
    }

    @PostMapping("/processSearch")
    public String processSearchForRooms(@ModelAttribute("roomSearchDto") @Valid RoomSearchDTO roomSearchDto,
                                        BindingResult result,
                                        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roomSearchDto", roomSearchDto);
            System.out.println(result.getAllErrors());
            return "search-rooms";
        }
        List<Room> rooms = roomService.findSuitableRooms(roomSearchDto);
        model.addAttribute("roomSearchDto", roomSearchDto);
        model.addAttribute("rooms", rooms);
        return "found-rooms";
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
