package com.milekj.bookingdotmock.controller;

import com.milekj.bookingdotmock.dto.RegistrationFormDTO;
import com.milekj.bookingdotmock.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private RegistrationService registrationService;

    @GetMapping("/registerUser")
    public String showUserRegistrationForm(Model model) {
        return prepareRegistrationForm(model, "processUserRegistrationForm");
    }

    @GetMapping("/registerOwner")
    public String showOwnerRegistrationForm(Model model) {
        return prepareRegistrationForm(model, "processOwnerRegistrationForm");
    }

    @PostMapping("/processUserRegistrationForm")
    public String processUserRegisterForm(@ModelAttribute("formDTO") @Validated RegistrationFormDTO formDTO, BindingResult result, Model model) {
        if (result.hasErrors())
            return prepareAndGetErrorFormView(formDTO, model);
        registrationService.addNewUser(formDTO);
        return "redirect:/";
    }

    @PostMapping("/processOwnerRegistrationForm")
    public String processOwnerRegisterForm(@ModelAttribute("formDTO") @Validated RegistrationFormDTO formDTO, BindingResult result, Model model) {
        if (result.hasErrors())
            return prepareAndGetErrorFormView(formDTO, model);
        registrationService.addNewOwner(formDTO);
        return "redirect:/";
    }

    private String prepareRegistrationForm(Model model, String targetURL) {
        model.addAttribute("formDTO", new RegistrationFormDTO());
        model.addAttribute("targetURL", targetURL);
        return "register";
    }

    private String prepareAndGetErrorFormView(RegistrationFormDTO formDTO, Model model) {
        model.addAttribute("formDTO", formDTO);
        return "register";
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
}
