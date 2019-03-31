package com.milekj.bookingdotmock.controller;

import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.service.RoomService;
import com.milekj.bookingdotmock.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        return "add-edit-room";
    }

    @GetMapping("/delete")
    public String deleteRoom(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @RequestParam long roomId,
                                          @RequestParam long hotelId,
                                          RedirectAttributes redirectAttributes) {
        roomService.delete(roomId, userDetails.getUsername());
        return redirectToHotelManagement(redirectAttributes, hotelId);
    }

    @PostMapping("/processAddOrEdit")
    public String processAddOrEditRoomForm(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @ModelAttribute @Valid Room room,
                                           BindingResult result,
                                           Model model,
                                           @RequestParam long hotelId,
                                           RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            model.addAttribute("room", room);
            model.addAttribute("hotelId", hotelId);
            return "add-edit-room";
        }
        String username = userDetails.getUsername();
        if (room.getId() == 0)
            roomService.save(room, hotelId, username);
        else
            roomService.edit(room, username);
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
