package com.milekj.bookingdotmock.controller;

import com.milekj.bookingdotmock.entity.Hotel;
import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.service.HotelService;
import com.milekj.bookingdotmock.service.OwnerService;
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
import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    private HotelService hotelService;
    private RoomService roomService;

    @GetMapping("/add")
    public String showAddHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "add-hotel";
    }

    @PostMapping("/processAdd")
    public String processAddHotelForm(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @ModelAttribute @Valid Hotel hotel,
                                      BindingResult result,
                                      Model model) {
        if(result.hasErrors()) {
            model.addAttribute("hotel", hotel);
            return "add-hotel";
        }
        hotelService.addHotel(hotel, userDetails.getUsername());
        return "redirect:/hotels/owned";
    }

    @GetMapping("/owned")
    public String showHotelsForOwner(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        addOwnersHotelsToModel(userDetails.getUsername(), model);
        return "owned-hotels";
    }

    @GetMapping("/choose")
    public String chooseHotelToManage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        addOwnersHotelsToModel(userDetails.getUsername(), model);
        return "choose-hotel";
    }

    @PostMapping("/processChoose")
    public String processChooseHotelToManage(@RequestParam long hotelId,
                                             RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("hotelId", hotelId);
        return "redirect:/hotels/manage";
    }

    @GetMapping("/manage")
    public String manageHotel(@AuthenticationPrincipal UserDetailsImpl userDetails,
                              @RequestParam long hotelId,
                              Model model) {
        Hotel hotel = hotelService.getHotelByIdIfOwned(hotelId, userDetails.getUsername());
        model.addAttribute("hotel", hotel);
        return "manage-hotel";
    }

    @GetMapping("/addRoom")
    public String addRoomToHotel(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @RequestParam long hotelId,
                                 Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hotelId", hotelId);
        return "add-room";
    }

    @PostMapping("/processAddRoom")
    public String processAddRoom(@ModelAttribute @Valid Room room,
                                 @RequestParam long hotelId,
                                 RedirectAttributes redirectAttributes) {
        System.out.println(room);
        roomService.addRoomToHotel(room, hotelId);
        redirectAttributes.addAttribute("hotelId", hotelId);
        return "redirect:/hotels/manage";
    }

    private void addOwnersHotelsToModel(String username, Model model) {
        List<Hotel> hotels = hotelService.getHotelsForOwnerUsername(username);
        model.addAttribute("hotels", hotels);
    }

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
