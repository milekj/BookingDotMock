package com.milekj.bookingdotmock.controller;

import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.service.RoomService;
import com.milekj.bookingdotmock.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private RoomService roomService;

    @GetMapping("/add")
    public String showAddRoomToHotelForm(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                         @RequestParam long hotelId,
                                         Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("targetUrl", "/rooms/processAdd");
        return "add-edit-room";
    }

    @GetMapping("/edit")
    public String showEditRoomToHotelForm(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @RequestParam long roomId,
                                          @RequestParam long hotelId,
                                          Model model) {
        Room room = roomService.findByIdIfOwned(roomId, userDetails.getUsername());
        model.addAttribute("room", room);
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("targetUrl", "/rooms/processEdit");
        return "add-edit-room";
    }

    @GetMapping("/delete")
    public String showEditRoomToHotelForm(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @RequestParam long roomId,
                                          @RequestParam long hotelId,
                                          RedirectAttributes redirectAttributes) {
        roomService.deleteById(roomId, userDetails.getUsername());
        return redirectToHotelManagement(redirectAttributes, hotelId);
    }

    @PostMapping("/processAdd")
    public String processAddRoomForm(@ModelAttribute @Valid Room room,
                                     @RequestParam long hotelId,
                                     RedirectAttributes redirectAttributes) {
        roomService.addRoomToHotel(room, hotelId);
        return redirectToHotelManagement(redirectAttributes, hotelId);
    }

    @PostMapping("/processEdit")
    public String processEditRoomForm(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @ModelAttribute @Valid Room room,
                                      @RequestParam long hotelId,
                                      RedirectAttributes redirectAttributes) {
        roomService.saveOrUpdate(room, userDetails.getUsername());
        return redirectToHotelManagement(redirectAttributes, hotelId);
    }

    private String redirectToHotelManagement(RedirectAttributes redirectAttributes, long hotelId) {
        redirectAttributes.addAttribute("hotelId", hotelId);
        return "redirect:/hotels/manage";
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
