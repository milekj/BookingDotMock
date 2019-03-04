package com.milekj.bookingdotmock.controller;

import com.milekj.bookingdotmock.repository.UserDTO;
import com.milekj.bookingdotmock.service.UserDetailsImpl;
import com.milekj.bookingdotmock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {
    private UserService userService;

    @GetMapping("/info")
    public String home(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return "info";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegisterForm(@ModelAttribute @Validated UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "register";
        }
        userService.save(userDTO);
        return "redirect:/";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
