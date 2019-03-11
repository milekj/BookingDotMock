package com.milekj.bookingdotmock.controller;

import com.milekj.bookingdotmock.entity.Hotel;
import com.milekj.bookingdotmock.service.HotelService;
import com.milekj.bookingdotmock.service.OwnerService;
import com.milekj.bookingdotmock.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    private HotelService hotelService;
    private OwnerService ownerService;

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
        return "my-hotels";
    }

    @GetMapping("/manage")
    public String chooseHotelToManage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        addOwnersHotelsToModel(userDetails.getUsername(), model);
        model.addAttribute("hotelID", new Integer(0));
        return "manage-hotels";
    }

    @GetMapping
            ("/processChoose")
    public String processChooseHotelToManage(@RequestParam String hotelID) {
        return "redirect:/hotels/" + hotelID + "/manage";
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
    public OwnerService getOwnerService() {
        return ownerService;
    }
}
