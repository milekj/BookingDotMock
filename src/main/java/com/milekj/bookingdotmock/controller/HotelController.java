package com.milekj.bookingdotmock.controller;

import com.milekj.bookingdotmock.entity.Hotel;
import com.milekj.bookingdotmock.entity.Room;
import com.milekj.bookingdotmock.service.HotelService;
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

    @GetMapping("/add")
    public String showAddHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "add-edit-hotel";
    }

    @GetMapping("/edit")
    public String showEditHotelForm(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    Model model,
                                    @RequestParam long hotelId) {
        Hotel hotel = hotelService.findByIdIfOwned(hotelId, userDetails.getUsername());
        model.addAttribute("hotel", hotel);
        return "add-edit-hotel";
    }

    @GetMapping("/delete")
    public String deleteHotel(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @RequestParam long hotelId) {
        Hotel hotel = hotelService.findByIdIfOwned(hotelId, userDetails.getUsername());
        hotelService.deleteById(hotelId);
        return "redirect:/hotels/owned";
    }

    @PostMapping("/processAddOrEdit")
    public String processAddOrEditHotelForm(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @ModelAttribute @Valid Hotel hotel,
                                            BindingResult result,
                                            Model model) {
        if(result.hasErrors()) {
            model.addAttribute("hotel", hotel);
            return "add-edit-hotel";
        }
        hotelService.saveOrUpdate(hotel, userDetails.getUsername());
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
        Hotel hotel = hotelService.findByIdIfOwned(hotelId, userDetails.getUsername());
        model.addAttribute("hotel", hotel);
        return "manage-hotel";
    }

    private void addOwnersHotelsToModel(String username, Model model) {
        List<Hotel> hotels = hotelService.findByOwnerUsername(username);
        model.addAttribute("hotels", hotels);
    }

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }
}
